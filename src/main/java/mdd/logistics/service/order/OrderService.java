package mdd.logistics.service.order;

import com.github.pagehelper.Page;
import mdd.logistics.controller.order.vo.OrderVo;
import mdd.logistics.domain.Order;


public interface OrderService {
    Order getById(Long id);

    int addOrder(OrderVo ov,Long uid);

    Long getOrderId();

    Page<Order> listOrderByPage(int num, int pageSize);

    int updateStatus(Long id,Integer status);

}
