package com.benz.online.assignment.util;

import com.benz.online.assignment.constants.DataFormat;
import com.benz.online.assignment.domain.StoreDataDAO;
import com.benz.online.assignment.services.RabbitMQConsumer;
import com.google.protobuf.InvalidProtocolBufferException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.*;

import static java.lang.System.out;

@Service
public class StoreDataUtil {
    private static FileWriter fileWriter;
    private static FileReader fileReader;
    private static String filePathCSV="src/main/resources/dataLibrary.csv";
    private static String filePathXML="src/main/resources/dataLibrary.xml";
     @Autowired
     DataAES dataAES;
     @Autowired
     RabbitMQConsumer rabbitMQConsumer;
     @Value("${store.data.secret.key}")
     String secretCode;

    /** if action is Store- The data would be appended to the File
     * Update- data will be got replaced
     * if isStore=true -Data is stored  else data updated in file
     *  if file type is CSV = saved in CSV if it is XML store it in XML format
     * @return
     */
    public boolean storeOrUpdateData(byte[]  storeDataDAOByte){
         StoreDataDAO storeData=storeDataDAOFromBinary(storeDataDAOByte);
         String jsonString=storeData.getData();
         DataFormat fileType=storeData.getFileType();
         boolean isStore=storeData.isStoreData();
         return  storeUpdateData(jsonString,fileType,isStore);
    }

    /** Convert byte array to storedata DAO
     * @param binaryStoreData
     * @return
     */
    private StoreDataDAO storeDataDAOFromBinary(final byte[] binaryStoreData) {
        StoreDataDAO storeDataDAO =new StoreDataDAO();
        try {
            final DataProtos.Data decodedData=DataProtos.Data.parseFrom(binaryStoreData);
            final String dataString=decodedData.getDataString();
            final boolean isStore= decodedData.getIsStore();
            storeDataDAO.setData(dataString);
            storeDataDAO.setStoreData(isStore);
            storeDataDAO.setFileType(decodedData.getFileType().getNumber()==0?DataFormat.CSV:DataFormat.XML);
        } catch (InvalidProtocolBufferException ipbe) {
            out.println("ERROR: Unable to instantiate DataProtos.StoreDataDAO instance from provided binary data - " +
                    ipbe);
        }
        return storeDataDAO;
    }

    /**
     * Store/Update data in XML/CSV file
     * @param jsonString
     * @param format
     * @param appendIndicator
     * @return
     */
    private boolean storeUpdateData(String jsonString, DataFormat format ,boolean appendIndicator){
        boolean isStored=false;
        try {
            String file="";
            if(format.name().equalsIgnoreCase(DataFormat.XML.getStr()))
                 file = filePathXML;
            else if(format.name().equalsIgnoreCase(DataFormat.CSV.getStr()))
                 file =filePathCSV;
            fileWriter = new FileWriter(file,appendIndicator);
            fileWriter.write(dataAES.decrypt(jsonString,secretCode));
            isStored= true;
            out.println("Stored data Successfully");
        } catch ( IOException e) {
            e.printStackTrace();
            //logger.info(e.toString());
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isStored;
    }

    /** read data from file and convert to byte[]
     * @return
     * @throws Exception
     */
    public byte[] readFile() throws Exception{
        StringBuffer sb= new StringBuffer();
        try {
            sb.append(readFileData(DataFormat.CSV.getStr()));
            sb.append(readFileData(DataFormat.XML.getStr()));
            out.println("Read successfully :"+sb.toString());
        } catch ( Exception e) {
            //logger.info("Read data error");
            e.printStackTrace();
        }
        return generateByteProtcoData(dataAES.encrypt(sb.toString(),secretCode));
    }
    private byte[] generateByteProtcoData(String readFile){
        final DataProtos.Data dataMessge= DataProtos.Data.newBuilder().setDataString(readFile).build();
        final byte[] binaryAlbum = dataMessge.toByteArray();
        return binaryAlbum;
    }

    /**Read data from files xml/csv
     * @param fileType
     * @return
     */
    private String readFileData(String fileType){
        StringBuffer sb= new StringBuffer();
        String line="";
        try {
            String file="";
            if(fileType.equalsIgnoreCase(DataFormat.XML.getStr()))
                file =filePathXML;
            else if(fileType.equalsIgnoreCase(DataFormat.CSV.getStr()))
                file=filePathCSV;
            fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                sb.append(line);
            }
            //logger.info("Read "+fileType+" data successfully");
        } catch ( IOException e) {
            //logger.info("Error reading "+fileType+" data.");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
