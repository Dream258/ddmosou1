package com.xxx.model.base.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 模块管理实体类
 */
@TableName("ddManager")
public class DdManager extends Model<DdManager> {

    @Override
    protected Serializable pkVal(){ return this.id; }

    private static final long serialVersionUID = 1L;

    /**   */
    private Integer id;
    /**  用户名 */
    private String managerTelephone;
    /**  密码 */
    private String managerPassword;
    /**  外键 */
    private Integer managerLimit;
    /**  1.正常 2.锁定 */
    private Integer managerCondition;

    public DdManager(){
    }

    // -------------------- GET AND SET --------------------

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getManagerTelephone(){
        return managerTelephone;
    }

    public void setManagerTelephone(String managerTelephone){
        this.managerTelephone = managerTelephone;
    }

    public String getManagerPassword(){
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword){
        this.managerPassword = managerPassword;
    }

    public Integer getManagerLimit(){
        return managerLimit;
    }

    public void setManagerLimit(Integer managerLimit){
        this.managerLimit = managerLimit;
    }

    public Integer getManagerCondition(){
        return managerCondition;
    }

    public void setManagerCondition(Integer managerCondition){
        this.managerCondition = managerCondition;
    }
}
