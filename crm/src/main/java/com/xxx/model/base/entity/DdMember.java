package com.xxx.model.base.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 会员管理实体类
 */
@TableName("ddMember")
public class DdMember extends Model<DdMember> {

    @Override
    protected Serializable pkVal(){ return this.id; }

    private static final long serialVersionUID = 1L;

    /**  用户标识 */
    private Integer id;
    /**  用户账号 */
    private String userTelephone;
    /**  用户密码 */
    private String userPassword;
    /**  用户头像 */
    private String userPhoto;
    /**  用户类型: 1 普通会员  2 VIP会员 */
    private Integer userType;
    /**  用户推广码(ddms+id) */
    private String userCode;
    /**  用户的上级 */
    private Integer userHigherId;
    /**  创建时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date userCreatetime;
    /**  用户金币 */
    private Integer userGold;

    public DdMember(){
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

    public String getUserPassword(){
        return userPassword;
    }

    public void setUserPassword(String userPassword){
        this.userPassword = userPassword;
    }

    public String getUserPhoto(){
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto){
        this.userPhoto = userPhoto;
    }

    public Integer getUserType(){
        return userType;
    }

    public void setUserType(Integer userType){
        this.userType = userType;
    }

    public String getUserCode(){
        return userCode;
    }

    public void setUserCode(String userCode){
        this.userCode = userCode;
    }

    public Integer getUserHigherId(){
        return userHigherId;
    }

    public void setUserHigherId(Integer userHigherId){
        this.userHigherId = userHigherId;
    }

    public java.util.Date getUserCreatetime(){
        return userCreatetime;
    }

    public void setUserCreatetime(java.util.Date userCreatetime){
        this.userCreatetime = userCreatetime;
    }

    public Integer getUserGold(){
        return userGold;
    }

    public void setUserGold(Integer userGold){
        this.userGold = userGold;
    }
}
