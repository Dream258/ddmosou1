package com.xxx.model.base.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 客户关怀实体类
 */
@TableName("care")
public class Care extends Model<Care> {

    @Override
    protected Serializable pkVal(){ return this.id; }

    private static final long serialVersionUID = 1L;

    /**  客户关怀 */
    private Integer id;
    /**  所属客户 */
    private Integer customer;
    /**  所属部门 */
    private Integer department;
    /**  关怀类型 */
    private String care;
    /**  手机号 */
    private String phone;
    /**  短信内容 */
    private String content;
    /**  创建时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date createtime;

    public Care(){
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

    public Integer getDepartment() { return department; }

    public void setDepartment(Integer department) { this.department = department; }

    public String getCare(){
        return care;
    }

    public void setCare(String care){
        this.care = care;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public void setCreatetime(java.util.Date createtime){
        this.createtime = createtime;
    }
}
