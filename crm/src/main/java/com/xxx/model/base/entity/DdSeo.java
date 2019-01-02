package com.xxx.model.base.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * seo管理实体类
 */
@TableName("ddSeo")
public class DdSeo extends Model<DdSeo> {

    @Override
    protected Serializable pkVal(){ return this.id; }

    private static final long serialVersionUID = 1L;

    /**  编号 */
    private Integer id;
    /**  标题副加字 */
    private String bt;
    /**  商城关键字 */
    private String sc;
    /**  关键词描述 */
    private String gjc;
    /**  其他页头信息 */
    private String qty;
    /**  电话 */
    private String phone;
    /**  网站备案号 */
    private String wzba;
    /**  公关备案 */
    private String ggba;
    /**  公关备案链接 */
    private String gglj;
    /**  第三方统计代码 */
    private String dsfSeo;

    public DdSeo(){
    }

    // -------------------- GET AND SET --------------------

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getBt(){
        return bt;
    }

    public void setBt(String bt){
        this.bt = bt;
    }

    public String getSc(){
        return sc;
    }

    public void setSc(String sc){
        this.sc = sc;
    }

    public String getGjc(){
        return gjc;
    }

    public void setGjc(String gjc){
        this.gjc = gjc;
    }

    public String getQty(){
        return qty;
    }

    public void setQty(String qty){
        this.qty = qty;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getWzba(){
        return wzba;
    }

    public void setWzba(String wzba){
        this.wzba = wzba;
    }

    public String getGgba(){
        return ggba;
    }

    public void setGgba(String ggba){
        this.ggba = ggba;
    }

    public String getGglj(){
        return gglj;
    }

    public void setGglj(String gglj){
        this.gglj = gglj;
    }

    public String getDsfSeo(){
        return dsfSeo;
    }

    public void setDsfSeo(String dsfSeo){
        this.dsfSeo = dsfSeo;
    }
}
