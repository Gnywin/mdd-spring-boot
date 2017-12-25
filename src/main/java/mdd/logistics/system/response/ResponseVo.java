package mdd.logistics.system.response;


import mdd.logistics.system.constant.CodeEnums;
import mdd.logistics.system.constant.Constants;

import java.io.Serializable;

/**
 * Created by 猫大东 on 2017/4/18.
 */
public class ResponseVo<T> implements Serializable {

    private static final long serialVersionUID = 3519387158091874315L;

    private int status = Constants.SUCCESS_CODE;

    private String msg = "ok";

    private long time = System.currentTimeMillis();

    private T data;

    public ResponseVo() {
    }

    public ResponseVo(T data) {
        this.data = data;
    }

    public ResponseVo(CodeEnums codeEnums) {
        this.status = codeEnums.getCode();
        this.msg = codeEnums.getMsg();;
    }

    public ResponseVo(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int code) {
        this.status = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
