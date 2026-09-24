package com.ryan.url_shortener.exceptions;

public class InvalidUrlException extends RuntimeException{

    public InvalidUrlException(){
        super();
    }

    public InvalidUrlException(String message){
        super(message);
    }

    public InvalidUrlException(String message, Throwable cause){
        super(message, cause);
    }
}
