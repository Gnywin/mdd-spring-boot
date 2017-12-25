package mdd.logistics.dao;

import com.github.pagehelper.Page;
import mdd.logistics.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户 DAO 接口类
 *
 * Created by bysocket on 07/02/2017.
 */
@Repository
public interface UserDao {
   User getById(@Param("id")Long id);

   User getByAccount(@Param("account")String account);

   int insert(User user);

   int update(User user);

   Page<User> pageUser();
}
