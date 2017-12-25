package mdd.logistics.domain;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private static final long serialVersionUID = -7086679622154189065L;
    private Long id;
    private Integer serviceType;
    private Integer status;
    private Integer isDeleted;
    private Date createTime;
    private String mark;
    private Long total;
    private Long uid;
    private String exporterCoName;
    private String exporterAddress;
    private String exporterName;
    private String exporterPhone;
    private String exporterMark;
    private String recipientCoName;
    private String recipientAddress;
    private String recipientName;
    private String recipientPhone;
    private String recipientMark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getExporterCoName() {
        return exporterCoName;
    }

    public void setExporterCoName(String exporterCoName) {
        this.exporterCoName = exporterCoName;
    }

    public String getExporterAddress() {
        return exporterAddress;
    }

    public void setExporterAddress(String exporterAddress) {
        this.exporterAddress = exporterAddress;
    }

    public String getExporterName() {
        return exporterName;
    }

    public void setExporterName(String exporterName) {
        this.exporterName = exporterName;
    }

    public String getExporterPhone() {
        return exporterPhone;
    }

    public void setExporterPhone(String exporterPhone) {
        this.exporterPhone = exporterPhone;
    }

    public String getExporterMark() {
        return exporterMark;
    }

    public void setExporterMark(String exporterMark) {
        this.exporterMark = exporterMark;
    }

    public String getRecipientCoName() {
        return recipientCoName;
    }

    public void setRecipientCoName(String recipientCoName) {
        this.recipientCoName = recipientCoName;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public String getRecipienMark() {
        return recipientMark;
    }

    public void setRecipienMark(String recipienMark) {
        this.recipientMark = recipienMark;
    }
}
