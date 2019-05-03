package org.tanglizi.oyster.front.exceptions;

public class PageNotFoundException extends Exception{

    public PageNotFoundException(){
        super();
    }

    public PageNotFoundException(String message){
        super(message);
    }
}
