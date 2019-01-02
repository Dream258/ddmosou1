package com.xxx.model.base.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * vip服务价格实体类
 */
@TableName("dd_ranking_monitor")
public class Ddrankingmonitor extends Model<Ddrankingmonitor> {
    @Override
    protected Serializable pkVal(){ return this.id; }

    private static final long serialVersionUID = 1L;

    //id
    private Integer id;

    private String orderFunction;

    private BigDecimal originalPrice;

    private BigDecimal nowPrice;

    private Integer monitorBabyNum;

    private Integer serviceCycle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderFunction() {
        return orderFunction;
    }

    public void setOrderFunction(String orderFunction) {
        this.orderFunction = orderFunction;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(BigDecimal nowPrice) {
        this.nowPrice = nowPrice;
    }

    public Integer getMonitorBabyNum() {
        return monitorBabyNum;
    }

    public void setMonitorBabyNum(Integer monitorBabyNum) {
        this.monitorBabyNum = monitorBabyNum;
    }

    public Integer getServiceCycle() {
        return serviceCycle;
    }

    public void setServiceCycle(Integer serviceCycle) {
        this.serviceCycle = serviceCycle;
    }
}
