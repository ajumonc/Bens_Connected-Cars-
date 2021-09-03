package com.benz.online.assignment.services;

import com.benz.online.assignment.domain.StoreDataDAO;
import com.benz.online.assignment.util.StoreDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreOrUpdateDataService {
    @Autowired
    private StoreDataUtil storeDataUtil;
    public void storeOrUpdateData(byte[]  storeDataDAO) throws Exception{
             storeDataUtil.storeOrUpdateData(storeDataDAO);
    }
}
