package com.benz.online.assignment.services;

import com.benz.online.assignment.constants.DataFormat;
import com.benz.online.assignment.utils.StoreDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateDataService {
    @Autowired
    private StoreDataUtil storeDataUtil;
    public boolean updateData(String inputData, DataFormat format) throws Exception{
        return storeDataUtil.updateData(inputData,format);
    }
}
