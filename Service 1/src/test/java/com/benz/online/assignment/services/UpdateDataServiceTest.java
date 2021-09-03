package com.benz.online.assignment.services;

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

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
class UpdateDataServiceTest {
    @Mock
    StoreDataUtil storeDataUtil;
    @InjectMocks
    UpdateDataService updateDataService= new UpdateDataService();
    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void updateData() throws Exception {
        Mockito.when(updateDataService.updateData(Mockito.anyString(),Mockito.any())).thenReturn(true) ;
        boolean isSuccess=updateDataService.updateData(Mockito.anyString(),Mockito.any());
        Assert.assertTrue(isSuccess);
    }
}