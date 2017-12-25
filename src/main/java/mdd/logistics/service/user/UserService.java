package mdd.logistics.service.user;

import com.github.pagehelper.Page;
import mdd.logistics.controller.user.vo.LoginVo;
import mdd.logistics.domain.User;

/**
 * 用户务逻辑接口类
 *
 * Created by 猫大东 on 12/02/2017.
 */
public interface UserService {
    /**
     * 根据城市 ID,查询城市信息
     *
     * @param id
     * @return
     */
    User getUsrById(Long id);

    User register(User user);

    LoginVo login(String account,String password);

    LoginVo adminLogin(String account,String password);

    int checkUser(User user);

    Page<User> listByPage(int num, int pageSize);

}
