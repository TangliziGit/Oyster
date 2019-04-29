package org.tanglizi.blog.exceptions;

public class PageNotFoundException extends Exception{

    public PageNotFoundException(){
        super();
    }

    public PageNotFoundException(String message){
        super(message);
    }
}
