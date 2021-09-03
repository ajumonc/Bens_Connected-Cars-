package com.benz.online.assignment.services;

import com.benz.online.assignment.utils.DataEncrypter;
import com.benz.online.assignment.utils.DataProtos;
import com.benz.online.assignment.utils.RabbitMQSender;
import com.benz.online.assignment.utils.StoreDataUtil;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
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

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
class StoreDataServiceTest {
    @Mock
    StoreDataUtil storeDataUtil;
    @InjectMocks
    StoreDataService storeDataService= new StoreDataService();
    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void storeData() throws Exception {
        Mockito.when(storeDataService.storeData(Mockito.anyString(),Mockito.any())).thenReturn(true) ;
        boolean isSuccess=storeDataService.storeData(Mockito.anyString(),Mockito.any());
        Assert.assertTrue(isSuccess);
    }
}