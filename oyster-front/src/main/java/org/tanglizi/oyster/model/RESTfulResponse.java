package org.tanglizi.oyster.model;

public class RESTfulResponse<T> {
    private String message;
    private boolean success;
    private T payload;
    private long timestamp;

    public static RESTfulResponse ok(){
        return new RESTfulResponse(true);
    }

    public static RESTfulResponse ok(String message){
        return new RESTfulResponse(true, message);
    }

    public static RESTfulResponse fail(){
        return new RESTfulResponse(false);
    }

    public static RESTfulResponse fail(String message){
        return new RESTfulResponse(false, message);
    }

    public RESTfulResponse(boolean success) {
        this.success = success;
        this.timestamp=System.currentTimeMillis()/1000;
    }

    public RESTfulResponse(boolean success, String message) {
        this.message=message;
        this.success = success;
        this.timestamp=System.currentTimeMillis()/1000;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
