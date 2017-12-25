package mdd.logistics.controller.sms.vo;

import java.io.Serializable;

public class SmsVo implements Serializable{
    private static final long serialVersionUID = -472484616107949953L;

    String code;
    Long expireTime;

    public SmsVo(String code, Long expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }
}
