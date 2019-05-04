package org.tanglizi.oyster.api.model;

public class RESTfulResponse<T> {
    private String message;
    private boolean success;
    private T data;
    private long timestamp;

    public static RESTfulResponse ok(){
        return new RESTfulResponse(true, "success");
    }

    public static RESTfulResponse ok(String message){
        return new RESTfulResponse(true, message);
    }

    public static RESTfulResponse fail(){
        return new RESTfulResponse(false, "failure");
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
