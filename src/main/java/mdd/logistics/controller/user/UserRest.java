package mdd.logistics.controller.user;

import mdd.logistics.controller.sms.vo.SmsVo;
import mdd.logistics.controller.user.vo.LoginVo;
import mdd.logistics.domain.User;
import mdd.logistics.service.sms.MessageService;
import mdd.logistics.service.user.UserService;
import mdd.logistics.system.annotation.CurrentUser;
import mdd.logistics.system.annotation.NonLogin;
import mdd.logistics.system.cache.MddCache;
import mdd.logistics.system.constant.CodeEnums;
import mdd.logistics.system.constant.Constants;
import mdd.logistics.system.response.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 猫大东 on 12/02/2017.
 */
@RestController
public class UserRest {

    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;


    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.GET)
    public ResponseVo getUser(@PathVariable("id") Long id) {

        return new ResponseVo(userService.getUsrById(id));
    }

    @NonLogin
    @RequestMapping(value = "/api/user/register", method = RequestMethod.POST)
    public ResponseVo addUser(@RequestBody User user) {
        SmsVo smsVo = MddCache.MCC.get(user.getMobile());
        if (smsVo != null) {
            if (smsVo.getCode().equalsIgnoreCase(user.getCode())) {
                User user1 = userService.register(user);
                if (user1 == null) {
                    return new ResponseVo(CodeEnums.REGISTER_WAS_FAILED);
                }
                return new ResponseVo();
            }
        }
        return new ResponseVo(CodeEnums.VERIFY_IS_ERROR);
    }

    @NonLogin
    @RequestMapping(value = "/api/user/login", method = RequestMethod.GET)
    public ResponseVo loginUser(@RequestParam("account") String account,
                                @RequestParam("password") String password) {
        LoginVo loginVo = userService.login(account, password);
        if (loginVo != null) {
            return new ResponseVo(loginVo);
        }
        return new ResponseVo(CodeEnums.LOGIN_FAIL);
    }

    @NonLogin
    @RequestMapping(value = "/api/admin/login", method = RequestMethod.GET)
    public ResponseVo loginAdmin(@RequestParam("account") String account,
                                 @RequestParam("password") String password) {
        LoginVo loginVo = userService.adminLogin(account, password);
        if (loginVo != null) {
            return new ResponseVo(loginVo);
        }
        return new ResponseVo(CodeEnums.LOGIN_FAIL);
    }

    @RequestMapping(value = "/api/user/check/{uid}/{status}", method = RequestMethod.PUT)
    public ResponseVo checkUser(@CurrentUser User admin, @PathVariable("uid") Long uid, @PathVariable("status") Integer status) {
        if (admin.getType() == 0) {
            return new ResponseVo(CodeEnums.INVALID_REQUEST);
        }
        User user = userService.getUsrById(uid);
        if (user != null && user.getStatus() == 0) {
            user.setStatus(status);
            if (status != 1) {
                user.setIsDeleted(1);
            }
            int res = userService.checkUser(user);
            if (res == 1) {
                messageService.sendSuccess(user.getMobile());
            }
            return new ResponseVo();
        }
        return new ResponseVo(Constants.ERROR_CODE, "操作失败");
    }


    @RequestMapping(value = "/api/user/list", method = RequestMethod.GET)
    public ResponseVo listUser(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {

        return new ResponseVo(userService.listByPage(pageNo, pageSize));
    }
}
