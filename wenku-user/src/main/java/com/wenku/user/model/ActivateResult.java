package com.wenku.user.model;

/**
 * 用户激活信息
 * Created by sindtom on 4/8/16.
 */
public class ActivateResult {

    private boolean resendMail;
    private boolean success = false;
    private String message;

    public ActivateResult(boolean success, boolean resendMail, String message){
        this.success = success;
        this.resendMail = resendMail;
        this.message = message;

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

    public boolean isResendMail() {
        return resendMail;
    }

    public void setResendMail(boolean resendMail) {
        this.resendMail = resendMail;
    }
}
