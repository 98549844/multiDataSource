package com.ace.multidatasource.local.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * @Classname: ResultObject
 * @Date: 5/4/2022 2:22 AM
 * @Author: garlam
 * @Description:
 */


public class ResultObject {
    private static final Logger log = LogManager.getLogger(ResultObject.class.getName());

    private int errorCode;
    private String errorMessage;
    private Object data;

    public ResultObject(int errorCode) {
        this.errorCode = errorCode;
    }

    public ResultObject(int errorCode, String errorMessage, Object data) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

