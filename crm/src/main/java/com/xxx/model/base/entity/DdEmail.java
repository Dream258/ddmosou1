package com.xxx.model.base.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 邮件通知实体类
 */
@TableName("ddEmail")
public class DdEmail extends Model<DdEmail> {

    @Override
    protected Serializable pkVal(){ return this.id; }

    private static final long serialVersionUID = 1L;

    /**  编号 */
    private Integer id;
    /**  1.启动2.禁用 */
    private Integer emailOpen;
    /**  SMTP服务器 */
    private String emailSm;
    /**  SMTP端口 */
    private String emailTp;
    /**  发信人邮件地址 */
    private String emailDz;
    /**  SMTP身份验证用户名 */
    private String emailSf;
    /**  SMTP身份验证码 */
    private String emailYzm;

    public DdEmail(){
    }

    // -------------------- GET AND SET --------------------

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getEmailOpen(){
        return emailOpen;
    }

    public void setEmailOpen(Integer emailOpen){
        this.emailOpen = emailOpen;
    }

    public String getEmailSm(){
        return emailSm;
    }

    public void setEmailSm(String emailSm){
        this.emailSm = emailSm;
    }

    public String getEmailTp(){
        return emailTp;
    }

    public void setEmailTp(String emailTp){
        this.emailTp = emailTp;
    }

    public String getEmailDz(){
        return emailDz;
    }

    public void setEmailDz(String emailDz){
        this.emailDz = emailDz;
    }

    public String getEmailSf(){
        return emailSf;
    }

    public void setEmailSf(String emailSf){
        this.emailSf = emailSf;
    }

    public String getEmailYzm(){
        return emailYzm;
    }

    public void setEmailYzm(String emailYzm){
        this.emailYzm = emailYzm;
    }
}
