package mdd.logistics.service.order.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import mdd.logistics.controller.order.vo.OrderVo;
import mdd.logistics.dao.OrderDao;
import mdd.logistics.dao.ResourceInfoDao;
import mdd.logistics.domain.Order;
import mdd.logistics.domain.ResourceInfo;
import mdd.logistics.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;
    @Autowired
    ResourceInfoDao resourceInfoDao;

    @Override
    public Order getById(Long id) {
        return orderDao.getById(id);
    }

    @Override
    @Transactional
    public int addOrder(OrderVo ov, Long uid) {
        Order order = ov.getOrder();
        order.setUid(uid);
        List<ResourceInfo> ris = ov.getResourceInfoList();
        long count = 0;
        for (ResourceInfo ri : ris) {
            Long num = ri.getNum();
            count += num == null ? 0 : num;
        }
        order.setTotal(count);
        Long id = getOrderId();
        order.setId(id);
        orderDao.insert(order);
        resourceInfoDao.insertList(ris, id);
        return 1;
    }

    @Override
    public synchronized Long getOrderId() {
        return getRandomId();
    }

    @Override
    public Page<Order> listOrderByPage(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return orderDao.pageOrder(null);
    }

    @Override
    public int updateStatus(Long id, Integer status) {

        return orderDao.updateStatus(id, status);
    }

    private Long getRandomId() {
        long res = (long) (6010_0000_1 + Math.random() * 8999_9999_9);
        if (orderDao.getById(res) == null) {
            return res;
        } else {
            return getRandomId();
        }
    }

}
