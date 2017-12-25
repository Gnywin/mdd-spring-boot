package mdd.logistics.system.exception;

import mdd.logistics.system.constant.CodeEnums;

import java.io.Serializable;

/**
 * Created by 猫大东 on 2017/4/19.
 */
public class MddException extends Exception implements Serializable {
    private static final long serialVersionUID = -5170926415602280547L;
    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public Throwable getCause() {
        return null;
    }

    public MddException() {
        super();
    }

    public MddException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public MddException(CodeEnums codeEnums) {
        super(codeEnums.getMsg());
        this.code = codeEnums.getCode();
        this.msg = codeEnums.getMsg();
    }
}
