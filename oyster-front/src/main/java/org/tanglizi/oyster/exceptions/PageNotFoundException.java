package org.tanglizi.oyster.exceptions;

public class PageNotFoundException extends Exception{

    public PageNotFoundException(){
        super();
    }

    public PageNotFoundException(String message){
        super(message);
    }
}
