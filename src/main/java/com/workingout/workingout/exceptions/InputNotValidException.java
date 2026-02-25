package com.workingout.workingout.exceptions;

public class InputNotValidException extends RuntimeException{

    public InputNotValidException(){
        super("Data that was provided isn't valid");
    }
    public InputNotValidException(String message){
        super(message);
    }
}
