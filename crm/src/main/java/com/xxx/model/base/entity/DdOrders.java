package com.xxx.model.base.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 订单管理实体类
 */
@TableName("ddOrders")
public class DdOrders extends Model<DdOrders> {

    @Override
    protected Serializable pkVal(){ return this.id; }

    private static final long serialVersionUID = 1L;

    /**  ID */
    private Integer id;
    /**  用户手机 */
    private String userTelephone;
    /**  支付宝交易号 */
    private String alipayNumber;
    /**  商户订单号 */
    private String orderNumber;
    /**  商品类型 */
    private String goodType;
    /**  会员时长 */
    private Integer vipTime;
    /**  付款金额 */
    private Double orderMoney;
    /**  创建时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date orderCurrentDate;

    public DdOrders(){
    }

    // -------------------- GET AND SET --------------------

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getUserTelephone(){
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone){
        this.userTelephone = userTelephone;
    }

    public String getAlipayNumber(){
        return alipayNumber;
    }

    public void setAlipayNumber(String alipayNumber){
        this.alipayNumber = alipayNumber;
    }

    public String getOrderNumber(){
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber){
        this.orderNumber = orderNumber;
    }

    public String getGoodType(){
        return goodType;
    }

    public void setGoodType(String goodType){
        this.goodType = goodType;
    }

    public Integer getVipTime(){
        return vipTime;
    }

    public void setVipTime(Integer vipTime){
        this.vipTime = vipTime;
    }

    public Double getOrderMoney(){
        return orderMoney;
    }

    public void setOrderMoney(Double orderMoney){
        this.orderMoney = orderMoney;
    }

    public java.util.Date getOrderCurrentDate(){
        return orderCurrentDate;
    }

    public void setOrderCurrentDate(java.util.Date orderCurrentDate){
        this.orderCurrentDate = orderCurrentDate;
    }
}
