package com.xxx.model.base.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 客户反馈实体类
 */
@TableName("feedback")
public class Feedback extends Model<Feedback> {

    @Override
    protected Serializable pkVal() { return this.id; }

    private static final long serialVersionUID = 1L;

    /**  客户反馈 */
    private Integer id;
    /**  所属客户 */
    private Integer customer;
    /**  所属部门 */
    private Integer department;
    /**  机构/客户 */
    private String type;
    /**  创建时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date createtime;
    /**  回复时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date replytime;
    /**  提出内容 */
    private String content;
    /**  回复内容 */
    private String answer;
    /**  是否回复 */
    private String reply;

    public Feedback(){
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

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public void setCreatetime(java.util.Date createtime){
        this.createtime = createtime;
    }

    public java.util.Date getReplytime(){
        return replytime;
    }

    public void setReplytime(java.util.Date replytime){
        this.replytime = replytime;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getAnswer(){
        return answer;
    }

    public void setAnswer(String answer){
        this.answer = answer;
    }

    public String getReply() { return reply; }

    public void setReply(String reply) { this.reply = reply; }
}
