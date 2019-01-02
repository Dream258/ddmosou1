package com.xxx.model.base.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 积分管理实体类
 */
@TableName("ddRebate")
public class DdRebate extends Model<DdRebate> {

    @Override
    protected Serializable pkVal(){ return this.id; }

    private static final long serialVersionUID = 1L;

    /**  用户标识 */
    private Integer id;
    /**  用户账号(外键) */
    private String userTelephone;
    /**  返利金币 */
    private Double userRebate;

    public DdRebate(){
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

    public Double getUserRebate(){
        return userRebate;
    }

    public void setUserRebate(Double userRebate){
        this.userRebate = userRebate;
    }
}
