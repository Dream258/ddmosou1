package com.xxx.model.base.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 我的消息实体类
 */
@TableName("information")
public class Information extends Model<Information> {

    @Override
    protected Serializable pkVal(){ return this.id; }

    private static final long serialVersionUID = 1L;

    /**  消息列表 */
    private Integer id;
    /**  所属目标 */
    private Integer target;
    /**  打回原因 */
    private String remarks;
    /**  所属类型 */
    private String type;
    /**  已读标记 */
    private String read;
    /**  创建时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date createtime;

    public Information(){
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

    public String getRemarks(){
        return remarks;
    }

    public void setRemarks(String remarks){
        this.remarks = remarks;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getRead() { return read; }

    public void setRead(String read) { this.read = read; }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public void setCreatetime(java.util.Date createtime){
        this.createtime = createtime;
    }
}
