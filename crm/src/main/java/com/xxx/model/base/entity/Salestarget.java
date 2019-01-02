package com.xxx.model.base.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 销售目标实体类
 */
@TableName("salestarget")
public class Salestarget extends Model<Salestarget> {

    @Override
    protected Serializable pkVal(){ return this.id; }

    private static final long serialVersionUID = 1L;

    /**  销售目标 */
    private Integer id;
    /**  所属产品 */
    private Integer product;
    /**  分配金额 */
    private Double sum;
    /**  开始时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date start;
    /**  结束时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date end;
    /**  完成率 */
    private Double rate;
    /**  创建时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date createtime;

    public Salestarget(){
    }

    // -------------------- GET AND SET --------------------

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getProduct(){
        return product;
    }

    public void setProduct(Integer product){
        this.product = product;
    }

    public Double getSum(){
        return sum;
    }

    public void setSum(Double sum){
        this.sum = sum;
    }

    public java.util.Date getStart(){
        return start;
    }

    public void setStart(java.util.Date start){
        this.start = start;
    }

    public java.util.Date getEnd(){
        return end;
    }

    public void setEnd(java.util.Date end){
        this.end = end;
    }

    public Double getRate(){
        return rate;
    }

    public void setRate(Double rate){
        this.rate = rate;
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public void setCreatetime(java.util.Date createtime){
        this.createtime = createtime;
    }
}
