package com.xxx.model.base.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 身份信息实体类
 */
@TableName("identity")
public class Identity extends Model<Identity> {

    @Override
    protected Serializable pkVal(){ return this.id; }

    private static final long serialVersionUID = 1L;

    /**  身份信息 */
    private Integer id;
    /**  所属用户 */
    private Integer user;
    /**  姓名 */
    private String name;
    /**  手机号 */
    private String phone;
    /**  身份证正面 */
    private String imgA;
    /**  身份证反面 */
    private String imgB;
    /**  创建时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date createtime;

    public Identity(){
    }

    // -------------------- GET AND SET --------------------

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getUser(){
        return user;
    }

    public void setUser(Integer user){
        this.user = user;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getImgA(){
        return imgA;
    }

    public void setImgA(String imgA){
        this.imgA = imgA;
    }

    public String getImgB(){
        return imgB;
    }

    public void setImgB(String imgB){
        this.imgB = imgB;
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public void setCreatetime(java.util.Date createtime){
        this.createtime = createtime;
    }
}
