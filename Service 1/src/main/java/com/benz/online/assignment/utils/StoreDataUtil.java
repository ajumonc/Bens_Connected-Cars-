package com.benz.online.assignment.utils;

import com.benz.online.assignment.constants.DataFormat;
import com.benz.online.assignment.model.StoreDataDAO;
import com.google.protobuf.InvalidProtocolBufferException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static java.lang.System.out;

@Service
public class StoreDataUtil {
    @Autowired
    RabbitMQSender rabbitMQSender;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    DataEncrypter dataEncrypter;
    @Value("${service2.localhost.port}")
    String readServiceURL;
    @Value("${store.data.secret.key}")
    String secretCode;

    /** Send store data to MQ
     * @param jsonString
     * @param format
     * @return
     */
    public boolean storeData(String jsonString, DataFormat format) throws Exception {
        StoreDataDAO storeDataDAO=new StoreDataDAO();
        storeDataDAO.setData(dataEncrypter.encrypt(jsonString,secretCode));
        storeDataDAO.setStoreData(true);
        if(format.getStr().equalsIgnoreCase(DataFormat.CSV.getStr()))
            return sendRabbitMQ(generateProtcoDataCSV(storeDataDAO));
        else
            return sendRabbitMQ(generateProtcoDataXML(storeDataDAO));
    }

    /** Send update data to MQ
     * @param jsonString
     * @param fileTye
     * @return
     */
    public boolean updateData(String jsonString, DataFormat fileTye) throws Exception {
        StoreDataDAO storeDataDAO=new StoreDataDAO();
        storeDataDAO.setData(dataEncrypter.encrypt(jsonString,secretCode));
        storeDataDAO.setStoreData(false);
        storeDataDAO.setFileType(fileTye);
        if(fileTye.getStr().equalsIgnoreCase(DataFormat.CSV.getStr()))
            return sendRabbitMQ(generateProtcoDataCSV(storeDataDAO));
        else
            return sendRabbitMQ(generateProtcoDataXML(storeDataDAO));
    }

    /** Send byte[] to MQ
     * @param storeDataDAO
     * @return
     * @throws Exception
     */
    private boolean sendRabbitMQ(byte[] storeDataDAO) throws Exception{
        boolean isSuccess=false;
         try{
            rabbitMQSender.send(storeDataDAO);
            isSuccess=true;
         }catch (Exception e){
             e.printStackTrace();
         }
        return isSuccess;
    }

    /** Read data from service 2
     * @return
     * @throws Exception
     */
    public String readFile()throws Exception{
        ResponseEntity<byte[]> entity= restTemplate.getForEntity(readServiceURL,byte[].class);
        byte[] readData= entity.getBody();
        out.println("Read data successfully from service 2");
        return dataEncrypter.decrypt(decodeReadDataFromBinary(readData),secretCode);
    }

    /** generate buffer data using Protco3
     * @param dataDAO
     * @return
     */
    private byte[] generateProtcoDataCSV(StoreDataDAO dataDAO){
        final DataProtos.Data dataMessge= DataProtos.Data.newBuilder().setDataString(dataDAO.getData())
                .setFileType(DataProtos.Data.FileType.CSV).setIsStore(dataDAO.isStoreData()).build();
        final byte[] binaryAlbum = dataMessge.toByteArray();
        return binaryAlbum;
    }
    private byte[] generateProtcoDataXML(StoreDataDAO dataDAO){
        final DataProtos.Data dataMessge= DataProtos.Data.newBuilder().setDataString(dataDAO.getData())
                .setFileType(DataProtos.Data.FileType.XML).setIsStore(dataDAO.isStoreData()).build();
        final byte[] binaryAlbum = dataMessge.toByteArray();
        return binaryAlbum;
    }

    /** Decode the byte[] array to string
     * @param binaryStoreData
     * @return
     */
    public String decodeReadDataFromBinary(final byte[] binaryStoreData) {
        String readData = "";
        try {
            final DataProtos.Data decodedData=DataProtos.Data.parseFrom(binaryStoreData);
            readData=decodedData.getDataString();
        } catch (InvalidProtocolBufferException ipbe) {
            out.println("ERROR: Unable to instantiate Data Protos.StoreDataDAO instance from provided binary data - " +
                    ipbe);
        }
        return readData;
    }
}
