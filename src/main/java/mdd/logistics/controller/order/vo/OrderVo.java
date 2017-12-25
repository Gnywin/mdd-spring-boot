package mdd.logistics.controller.order.vo;

import mdd.logistics.domain.Order;
import mdd.logistics.domain.ResourceInfo;

import java.io.Serializable;
import java.util.List;

public class OrderVo implements Serializable{
    private static final long serialVersionUID = -6564544445010809129L;
    private Order order;
    private List<ResourceInfo> resourceInfoList;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<ResourceInfo> getResourceInfoList() {
        return resourceInfoList;
    }

    public void setResourceInfoList(List<ResourceInfo> resourceInfoList) {
        this.resourceInfoList = resourceInfoList;
    }
}
