package mdd.logistics.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class ResourceInfo implements Serializable{
    private static final long serialVersionUID = 8058248774568490040L;
    private Long id;
    private Long orderId;
    private Integer type;
    private Long num;
    private BigDecimal weight;
    private BigDecimal size;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHsNum() {
        return hsNum;
    }

    public void setHsNum(String hsNum) {
        this.hsNum = hsNum;
    }

    private String hsNum;
}
