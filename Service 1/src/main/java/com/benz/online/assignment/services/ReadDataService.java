package com.benz.online.assignment.services;

import com.benz.online.assignment.utils.StoreDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadDataService {
    @Autowired
    StoreDataUtil storeDataUtil;
    public String readFile() throws  Exception{
        return storeDataUtil.readFile();
    }
}
