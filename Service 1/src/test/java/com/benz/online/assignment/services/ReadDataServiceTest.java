package com.benz.online.assignment.services;

import com.benz.online.assignment.utils.DataEncrypter;
import com.benz.online.assignment.utils.DataProtos;
import com.benz.online.assignment.utils.RabbitMQSender;
import com.benz.online.assignment.utils.StoreDataUtil;
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
import org.springframework.web.client.RestTemplate;
@RunWith(MockitoJUnitRunner.class)
class ReadDataServiceTest {
    @Mock
    private DataEncrypter dataEncrypter;
    @Mock
    private DataProtos dataProtos;
    @Mock
    RabbitMQSender rabbitMQSender;
    @Mock
    RestTemplate restTemplate  ;
    @Mock
    StoreDataUtil storeDataUtil;
    @InjectMocks
    ReadDataService readDataService= new ReadDataService();
    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void read() throws Exception {
        String data="";
        Mockito.when(storeDataUtil.readFile()).thenReturn("myString");
        data=readDataService.readFile() ;
        Assert.assertEquals(data,"myString");
    }

}