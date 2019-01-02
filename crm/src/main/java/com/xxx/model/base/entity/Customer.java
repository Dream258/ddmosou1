package com.xxx.model.base.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 客户信息实体类
 */
@TableName("customer")
public class Customer extends Model<Customer> {

    @Override
    protected Serializable pkVal() { return this.id; }

    private static final long serialVersionUID = 1L;

    /**  客户信息 */
    private Integer id;
    /**  客户名称 */
    private String name;
    /**  联系电话 */
    private String phone;
    /**  电子邮箱 */
    private String email;
    /**  微信QQ */
    private String wechat;
    /**  身份证正面 */
    private String imgA;
    /**  身份证反面 */
    private String imgB;
    /**  企业类型 */
    private String company;
    /**  所属部门 */
    private Integer department;
    /**  成交状态 */
    private String deal;
    /**  对付状态 */
    private String cash;
    /**  经办人 */
    private Integer agent;
    /**  芝麻西瓜 */
    private String zmxg;
    /**  类型个人1机构2 */
    private String type;
    /**  0未审核1审核通过2未通过 */
    private String state;
    /**  是否公海 */
    private String seas;
    /**  创建时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date createtime;

    public Customer(){
    }

    // -------------------- GET AND SET --------------------

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
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

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getWechat(){
        return wechat;
    }

    public void setWechat(String wechat){
        this.wechat = wechat;
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

    public String getCompany(){
        return company;
    }

    public void setCompany(String company){
        this.company = company;
    }

    public Integer getDepartment(){
        return department;
    }

    public void setDepartment(Integer department){
        this.department = department;
    }

    public String getDeal(){
        return deal;
    }

    public void setDeal(String deal){
        this.deal = deal;
    }

    public String getCash(){
        return cash;
    }

    public void setCash(String cash){
        this.cash = cash;
    }

    public Integer getAgent(){
        return agent;
    }

    public void setAgent(Integer agent){
        this.agent = agent;
    }

    public String getZmxg(){
        return zmxg;
    }

    public void setZmxg(String zmxg){
        this.zmxg = zmxg;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getState(){
        return state;
    }

    public void setState(String state){
        this.state = state;
    }

    public String getSeas(){
        return seas;
    }

    public void setSeas(String seas){
        this.seas = seas;
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public void setCreatetime(java.util.Date createtime){
        this.createtime = createtime;
    }

}
