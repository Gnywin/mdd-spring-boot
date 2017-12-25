package mdd.logistics.controller.sms;

import com.aliyuncs.exceptions.ClientException;
import mdd.logistics.controller.sms.vo.SmsVo;
import mdd.logistics.service.sms.MessageService;
import mdd.logistics.system.annotation.NonLogin;
import mdd.logistics.system.cache.MddCache;
import mdd.logistics.system.constant.CodeEnums;
import mdd.logistics.system.response.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageRest {
    @Autowired
    MessageService messageService;
    @NonLogin
    @RequestMapping(value = "/api/sms/code/{mobile}", method = RequestMethod.POST)
    public ResponseVo sendCodeMessage(@PathVariable("mobile") String mobile) throws ClientException {
        String code = messageService.getCode();
        MddCache.MCC.put(mobile, new SmsVo(code, System.currentTimeMillis() + 60 * 1000 * 15));
        messageService.sendCode(mobile, code);
        return new ResponseVo();
    }
    @NonLogin
    @RequestMapping(value = "/api/sms/success/{mobile}", method = RequestMethod.GET)
    public ResponseVo successCodeMessage(@PathVariable("mobile") String mobile) throws ClientException {
        messageService.sendSuccess(mobile);
        return new ResponseVo();
    }

    @RequestMapping(value = "/api/sms/check/{mobile}", method = RequestMethod.GET)
    public ResponseVo checkCodeMessage(@PathVariable("mobile") String mobile,
                                       @RequestParam("code") String code) {
        SmsVo smsVo = MddCache.MCC.get(mobile);
        if (smsVo != null) {
            if (smsVo.getCode().equalsIgnoreCase(code)) {
                return new ResponseVo();
            }
        }
        return new ResponseVo(CodeEnums.VERIFY_IS_ERROR);
    }
}
