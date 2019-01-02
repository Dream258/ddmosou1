package com.xxx.model.base.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 积分日志实体类
 */
@TableName("ddRebatelog")
public class DdRebatelog extends Model<DdRebatelog> {

    @Override
    protected Serializable pkVal(){ return this.id; }

    private static final long serialVersionUID = 1L;

    /**  ID */
    private Integer id;
    /**  邀请人 */
    private String userTelephoneF;
    /**  被邀请人 */
    private String userTelephoneS;
    /**  记录创建日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createTime;

    public DdRebatelog(){
    }

    // -------------------- GET AND SET --------------------

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getUserTelephoneF(){
        return userTelephoneF;
    }

    public void setUserTelephoneF(String userTelephoneF){
        this.userTelephoneF = userTelephoneF;
    }

    public String getUserTelephoneS(){
        return userTelephoneS;
    }

    public void setUserTelephoneS(String userTelephoneS){
        this.userTelephoneS = userTelephoneS;
    }

    public java.util.Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime;
    }
}
