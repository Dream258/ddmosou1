package com.xxx.model.base.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 销售分配实体类
 */
@TableName("salesallot")
public class Salesallot extends Model<Salesallot> {

    @Override
    protected Serializable pkVal(){ return this.id; }

    private static final long serialVersionUID = 1L;

    /**  销售分配 */
    private Integer id;
    /**  所属目标 */
    private Integer target;
    /**  所属用户 */
    private Integer department;
    /**  分配金额 */
    private Double sum;
    /**  达成率 */
    private Double rate;
    /**  级别 */
    private String level;

    public Salesallot(){
    }

    // -------------------- GET AND SET --------------------

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getTarget(){
        return target;
    }

    public void setTarget(Integer target){
        this.target = target;
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

    public Double getRate(){
        return rate;
    }

    public void setRate(Double rate){
        this.rate = rate;
    }

    public String getLevel() { return level; }

    public void setLevel(String level) { this.level = level; }
}
