package com.wenku.define;

/**
 * Created by sindtom on 2/27/17.
 */
public class Result {

    private String message;
    private boolean success;
    private String code = "0";

    private static final String SERVER_ERROR_CODE = "500";

    public Result(){
        super();
    }

    public static Result success(){
        return new Result(true);
    }

    public static Result success(String msg){
        return new Result(true,msg);
    }

    public static Result fail(String msg){
        return new Result(false,msg);
    }

    public Result(boolean success){
        this.setSuccess(true);
        this.setMessage("");
    }

    public Result(boolean success, String message){
        this.setMessage(message);
        this.setSuccess(success);
        if (!success){
            this.setCode(SERVER_ERROR_CODE);
        }
    }

    public Result(boolean success, String code, String message){
        this.setMessage(message);
        this.setSuccess(success);
        this.setCode(code);
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
}
