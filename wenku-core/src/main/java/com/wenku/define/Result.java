package com.wenku.define;

/**
 * Created by sindtom on 2/27/17.
 */
public class Result {

    private String message;
    private boolean success;
    private String code = "0";
    private Object data;

    public static Result success(){
        return new Result(true);
    }

    public static Result success(String msg){
        return new Result(true,msg);
    }

    public static Result success(String msg,Object data){
        return new Result(true,msg,data);
    }

    public static Result fail(String msg){
        return new Result(false,msg);
    }

    public static Result fail(String msg,Object data){
        return new Result(false,msg,data);
    }

    public Result(){
        super();
    }

    public Result(boolean success){
        this.setSuccess(true);
        this.setMessage("");
    }

    public Result(boolean success,String message){
        this.setMessage(message);
        this.setSuccess(success);
        if (!success){
            this.setCode("500");
        }
    }
    public Result(boolean success,String message,Object data){
        this.setMessage(message);
        this.setSuccess(success);
        if (!success){
            this.setCode("500");
        }
        this.setData(data);
    }

    public Result(boolean success,String code,String message){
        this.setMessage(message);
        this.setSuccess(success);
        this.setCode(code);
    }

    public Result(boolean success,String code,String message,Object data){
        this.setMessage(message);
        this.setSuccess(success);
        this.setCode(code);
        this.setData(data);
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
