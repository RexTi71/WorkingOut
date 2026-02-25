package com.workingout.workingout.exceptions;

import java.util.Date;

public class ErrorObject {
    private Integer statusCode;
    private String message;
    private Date timestamp;

    public Integer getStatusCode(){
        return this.statusCode;
    }
    public void setStatusCode(Integer statusCode){
        this.statusCode = statusCode;
    }
    public String getMessage(){
        return this.message;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public Date getTimestamp(){
        return this.timestamp;
    }
    public void setTimestamp(Date timestamp){
        this.timestamp = timestamp;
    }
}
