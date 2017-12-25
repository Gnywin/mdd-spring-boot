package mdd.logistics.dao;

import com.github.pagehelper.Page;
import mdd.logistics.domain.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {
    Order getById(Long id);

    int insert(Order order);

    Page<Order> pageOrder(Long uid);

    int updateStatus(@Param("id") Long id,@Param("status") Integer status);
}
