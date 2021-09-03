package com.benz.online.assignment.services;

import com.benz.online.assignment.constants.DataFormat;
import com.benz.online.assignment.utils.StoreDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreDataService {
    @Autowired
    private StoreDataUtil storeDataUtil;
    public boolean storeData(String inputData, DataFormat format) throws Exception{
        return storeDataUtil.storeData(inputData,format);
    }
}
