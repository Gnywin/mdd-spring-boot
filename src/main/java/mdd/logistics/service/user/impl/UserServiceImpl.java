package mdd.logistics.service.user.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import mdd.logistics.controller.user.vo.LoginVo;
import mdd.logistics.service.jwt.JwtCreator;
import mdd.logistics.service.user.UserService;
import mdd.logistics.system.constant.Constants;
import mdd.logistics.system.util.SecretUtils;
import mdd.logistics.dao.UserDao;
import mdd.logistics.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    JwtCreator jwtCreator;

    @Override
    public User getUsrById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public User register(User user) {
        if (user.getMobile() != null) {
            user.setPassword(Constants.DEFAULT_PASSWORD_SECRET);
            user.setAccount(user.getMobile());
            int res = userDao.insert(user);
            if (res == Constants.SUCCESS_CODE) {
                return user;
            }
        }
        return null;

    }

    @Override
    public LoginVo login(String account, String password) {
        User user = userDao.getByAccount(account);
        if (user != null) {
            if (user.getStatus() == 1) {
                if (user.getPassword().equals(SecretUtils.encrypt(password))) {
                    String token = jwtCreator.createJwt(user.getId());
                    LoginVo lv = new LoginVo();
                    lv.setToken(token);
                    lv.setUser(user);
                    return lv;
                }
            }
        }
        return null;
    }

    @Override
    public LoginVo adminLogin(String account, String password) {
        User user = userDao.getByAccount(account);
        if (user != null) {
            if (user.getType() == Constants.USER_TYPE_SUPER || user.getType() == Constants.USER_TYPE_ADMIN)
                user.getPassword().equals(SecretUtils.encrypt(password));
            String token = jwtCreator.createJwt(user.getId());
            LoginVo lv = new LoginVo();
            lv.setToken(token);
            lv.setUser(user);
            return lv;
        }
        return null;
    }

    @Override
    public int checkUser(User user) {
        return userDao.update(user);
    }

    @Override
    public Page<User> listByPage(int num, int pageSize) {
        PageHelper.startPage(num, pageSize);
        return userDao.pageUser();
    }
}
