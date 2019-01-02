package com.xxx.model.base.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 客户转交实体类
 */
@TableName("deliver")
public class Deliver extends Model<Deliver> {

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    private static final long serialVersionUID = 1L;

    /**  客户转交 */
    private Integer id;
    /**  所属部门 */
    private Integer department;
    /**  所属客户 */
    private Integer customer;
    /**  转交人 */
    private Integer transfer;
    /**  接收人 */
    private Integer receive;
    /**  转交/分配 */
    private String type;
    /**  时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date createtime;
    /**  部门名称 */
    private String departName;
    /**  客户名称 */
    private String customerName;
    /**  转交人 */
    private String transferName;
    /**  接收人 */
    private String receiveName;

    public Deliver(){
    }

    // -------------------- GET AND SET --------------------

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getDepartment() { return department; }

    public void setDepartment(Integer department) { this.department = department; }

    public Integer getCustomer(){
        return customer;
    }

    public void setCustomer(Integer customer){
        this.customer = customer;
    }

    public Integer getTransfer(){
        return transfer;
    }

    public void setTransfer(Integer transfer){
        this.transfer = transfer;
    }

    public Integer getReceive(){
        return receive;
    }

    public void setReceive(Integer receive){
        this.receive = receive;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public void setCreatetime(java.util.Date createtime){
        this.createtime = createtime;
    }

    public String getDepartName() { return departName; }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getCustomerName() { return customerName; }

    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getReceiveName() { return receiveName; }

    public void setReceiveName(String receiveName) { this.receiveName = receiveName; }

    public String getTransferName() { return transferName; }

    public void setTransferName(String transferName) { this.transferName = transferName; }

}
