package com.benz.online.assignment.services;

import com.benz.online.assignment.util.StoreDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Read from file
 */
@Service
public class ReadDataService {
    @Autowired
    StoreDataUtil storeDataUtil;
    public byte[] readFile() throws  Exception{
        return storeDataUtil.readFile();
    }
}
