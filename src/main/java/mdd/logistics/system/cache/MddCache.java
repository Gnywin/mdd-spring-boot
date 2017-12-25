package mdd.logistics.system.cache;

import mdd.logistics.controller.sms.vo.SmsVo;

import java.util.Map;

public interface MddCache {
    Map<String,SmsVo> MCC =  new MddCodeCache();
}
