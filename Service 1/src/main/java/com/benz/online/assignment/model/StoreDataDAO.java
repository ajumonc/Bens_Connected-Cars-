package com.benz.online.assignment.model;

import com.benz.online.assignment.constants.DataFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = StoreDataDAO.class)
public class StoreDataDAO {
    private String data;
    private DataFormat fileType;
    private boolean storeData;

    public boolean isStoreData() {
        return storeData;
    }

    public void setStoreData(boolean storeData) {
        this.storeData = storeData;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public DataFormat getFileType() {
        return fileType;
    }

    public void setFileType(DataFormat fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "StoreDataDAO [data=" + data + ", fileType=" + fileType + ", storeData=" + storeData + "]";
    }
}
