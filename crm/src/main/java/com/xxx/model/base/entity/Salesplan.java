package com.xxx.model.base.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 销售计划实体类
 */
@TableName("salesplan")
public class Salesplan extends Model<Salesplan> {

    @Override
    protected Serializable pkVal(){ return this.id; }

    private static final long serialVersionUID = 1L;

    /**  销售计划 */
    private Integer id;
    /**  所属部门 */
    private Integer department;
    /**  进款金额 */
    private Double sum;
    /**  进款时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date time;
    /**  创建时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date createtime;

    public Salesplan(){
    }

    // -------------------- GET AND SET --------------------

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getDepartment(){
        return department;
    }

    public void setDepartment(Integer department){
        this.department = department;
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

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public void setCreatetime(java.util.Date createtime){
        this.createtime = createtime;
    }
}
