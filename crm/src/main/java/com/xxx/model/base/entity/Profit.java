package com.xxx.model.base.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 签约收益实体类
 */
@TableName("profit")
public class Profit extends Model<Profit> {

    @Override
    protected Serializable pkVal(){ return this.id; }

    private static final long serialVersionUID = 1L;

    /**  签约收益 */
    private Integer id;
    /**  收益金额 */
    private Double sum;
    /**  收益时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date time;
    /**  收益类型 */
    private String type;
    /**  所属签约 */
    private Integer signing;
    /**  所属客户 */
    private Integer customer;
    /**  计息区间 */
    private String interval;
    public Profit(){
    }

    // -------------------- GET AND SET --------------------

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Double getSum(){
        return sum;
    }

    public void setSum(Double sum){
        this.sum = sum;
    }

    public java.util.Date getTime(){
        return time;
    }

    public void setTime(java.util.Date time){
        this.time = time;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public Integer getSigning(){
        return signing;
    }

    public void setSigning(Integer signing){
        this.signing = signing;
    }

    public Integer getCustomer() { return customer; }

    public void setCustomer(Integer customer) { this.customer = customer; }

    public String getInterval() { return interval; }

    public void setInterval(String interval) { this.interval = interval; }
}
