package com.benz.online.assignment.utils;

import com.benz.online.assignment.constants.DataFormat;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
class StoreDataUtilTest {
     @Mock
     private DataEncrypter dataEncrypter;
     @Mock
     private DataProtos dataProtos;
     @Mock
     RabbitMQSender rabbitMQSender;
     @Mock
    RestTemplate restTemplate  ;
     @InjectMocks
     StoreDataUtil storeDataUtil= new StoreDataUtil();
     @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

     @BeforeEach
     public void init(){
         MockitoAnnotations.initMocks(this);
     }

    /**Store data as CSV
     * @throws Exception
     */
    @Test
    void storeDataCSV() throws Exception {
        ReflectionTestUtils.setField(storeDataUtil, "secretCode", "secretcode!!!");
        Mockito.doNothing().when(rabbitMQSender).send(Mockito.any());
        Mockito.when(dataEncrypter.encrypt(Mockito.anyString(),Mockito.anyString())).thenReturn("!!!!%%%%");
        boolean store=storeDataUtil.storeData("abcd", DataFormat.CSV);
        Assert.assertTrue(store);
    }

    /**Store data as XML
     * @throws Exception
     */
    @Test
    void storeDataXML() throws Exception {
        ReflectionTestUtils.setField(storeDataUtil, "secretCode", "secretcode!!!");
        Mockito.doNothing().when(rabbitMQSender).send(Mockito.any());
        Mockito.when(dataEncrypter.encrypt(Mockito.anyString(),Mockito.anyString())).thenReturn("!!!!%%%%");
        boolean store=storeDataUtil.storeData("abcd", DataFormat.XML);
        Assert.assertTrue(store);
    }

    /** Update data as CSV
     * @throws Exception
     */
    @Test
    void updateDataCSV() throws Exception {
        ReflectionTestUtils.setField(storeDataUtil, "secretCode", "secretcode!!!");
        Mockito.doNothing().when(rabbitMQSender).send(Mockito.any());
        Mockito.when(dataEncrypter.encrypt(Mockito.anyString(),Mockito.anyString())).thenReturn("!!!!%%%%");
        boolean store=storeDataUtil.updateData("abcd", DataFormat.CSV);
        Assert.assertTrue(store);
    }

    /** Update data in XML
     * @throws Exception
     */
    @Test
    void updateDataXML() throws Exception {
        ReflectionTestUtils.setField(storeDataUtil, "secretCode", "secretcode!!!");
        Mockito.doNothing().when(rabbitMQSender).send(Mockito.any());
        Mockito.when(dataEncrypter.encrypt(Mockito.anyString(),Mockito.anyString())).thenReturn("!!!!%%%%");
        boolean store=storeDataUtil.updateData("abcd", DataFormat.XML);
        Assert.assertTrue(store);
    }

    /** Read File testing
     * @throws Exception
     */
    @Test
    void readFile() throws Exception {
        ReflectionTestUtils.setField(storeDataUtil, "readServiceURL", "http://localhost:9090/service2/read-data");
         byte[] x= new byte[]{1,23,34,56};
         String decode=null;
         Mockito.when(restTemplate.getForEntity("http://localhost:9090/service2/read-data",byte[].class))
                 .thenReturn(new ResponseEntity<>(x,HttpStatus.OK));
         Mockito.when(dataEncrypter.encrypt(Mockito.anyString(),Mockito.anyString())).thenReturn(decode);
         String readData=storeDataUtil.readFile();
         Assert.assertEquals(readData,decode);
    }

    /**
     * Decode the data from byte[]
     */
    @Test
    void decodeReadDataFromBinary() {
        String outputExp="!!!!!!22222@@@";
        byte[] input= new byte[]{10, 14, 33, 33, 33, 33, 33, 33, 50, 50, 50, 50, 50, 64, 64, 64};
         String output=storeDataUtil.decodeReadDataFromBinary(input);
         Assert.assertEquals(outputExp,output);
    }
}