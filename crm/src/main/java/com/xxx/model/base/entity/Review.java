package com.xxx.model.base.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 客户回访实体类
 */
@TableName("review")
public class Review extends Model<Review> {

    @Override
    protected Serializable pkVal() { return this.id; }

    private static final long serialVersionUID = 1L;

    /**  客户回访 */
    private Integer id;
    /**  所属客户 */
    private Integer customer;
    /**  所属部门 */
    private Integer department;
    /**  所属产品 */
    private Integer product;
    /**  合同编号 */
    private String number;
    /**  回访状态 */
    private String visit;
    /**  回访结果 */
    private String result;
    /**  创建时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date createtime;

    public Review(){
    }

    // -------------------- GET AND SET --------------------

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getCustomer(){
        return customer;
    }

    public void setCustomer(Integer customer){
        this.customer = customer;
    }

    public Integer getDepartment(){
        return department;
    }

    public void setDepartment(Integer department){
        this.department = department;
    }

    public Integer getProduct(){
        return product;
    }

    public void setProduct(Integer product){
        this.product = product;
    }

    public String getNumber(){
        return number;
    }

    public void setNumber(String number){
        this.number = number;
    }

    public String getVisit(){
        return visit;
    }

    public void setVisit(String visit){
        this.visit = visit;
    }

    public String getResult(){
        return result;
    }

    public void setResult(String result){
        this.result = result;
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public void setCreatetime(java.util.Date createtime){
        this.createtime = createtime;
    }
}
