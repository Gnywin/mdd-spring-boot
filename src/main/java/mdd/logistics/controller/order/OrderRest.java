package mdd.logistics.controller.order;

import com.google.gson.Gson;
import mdd.logistics.controller.order.vo.OrderVo;
import mdd.logistics.domain.Order;
import mdd.logistics.domain.User;
import mdd.logistics.service.http.HttpService;
import mdd.logistics.service.order.OrderService;
import mdd.logistics.system.annotation.CurrentUser;
import mdd.logistics.system.constant.Constants;
import mdd.logistics.system.response.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class OrderRest {
    @Autowired
    OrderService orderService;
    @Autowired
    HttpService httpService;

    @RequestMapping(value = "/api/order/add", method = RequestMethod.POST)
    public ResponseVo addOrder(@CurrentUser User user, @RequestBody OrderVo orderVo) {
        orderService.addOrder(orderVo, user.getId());
        return new ResponseVo();
    }

    @RequestMapping(value = "/api/order/page", method = RequestMethod.GET)
    public ResponseVo pageOrder(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
        return new ResponseVo(orderService.listOrderByPage(pageNo, pageSize));
    }

    @RequestMapping(value = "/api/order/status/update/{orderId}", method = RequestMethod.PUT)
    public ResponseVo updateOrderStatus(@PathVariable("orderId") Long orderId) {
        Gson gson = new Gson();
        Order order = orderService.getById(orderId);
        if (order != null) {
            String result = httpService.sendGet(Constants.INFO_URL + orderId);
            if (result != null) {
                Map<String, Object> map = gson.fromJson(result, Map.class);
                if ((double) map.get("ReturnValue") > 0) {
                    List list = (List) map.get("RecList");
                    Map<String, Object> item = (Map<String, Object>) list.get(0);
                    Integer status = ((Double) item.get("status")).intValue();
                    orderService.updateStatus(orderId,status);
                }
                return new ResponseVo(map);
            }
        }
        return new ResponseVo(Constants.ERROR_CODE,"操作失败");
    }

}
