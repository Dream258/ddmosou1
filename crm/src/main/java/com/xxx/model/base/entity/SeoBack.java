package com.xxx.model.base.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 配置测试实体类
 */
@TableName("seoBack")
public class SeoBack extends Model<SeoBack> {

    @Override
    protected Serializable pkVal(){ return this.idSeo; }

    private static final long serialVersionUID = 1L;

    /**  编号 */
    private Integer idSeo;
    /**  标题副加字 */
    private String btSeo;
    /**  商城关键字 */
    private String scSeo;
    /**  关键词描述 */
    private String gjcSeo;
    /**  其他页头信息 */
    private String qtySeo;
    /**  电话 */
    private String phoneSeo;
    /**  网站备案号 */
    private String wzbaSeo;
    /**  公关备案 */
    private String ggbaSeo;
    /**  公关备案链接 */
    private String ggljSeo;
    /**  第三方统计代码 */
    private String dsfSeo;

    public SeoBack(){
    }

    // -------------------- GET AND SET --------------------

    public Integer getIdSeo(){
        return idSeo;
    }

    public void setIdSeo(Integer idSeo){
        this.idSeo = idSeo;
    }

    public String getBtSeo(){
        return btSeo;
    }

    public void setBtSeo(String btSeo){
        this.btSeo = btSeo;
    }

    public String getScSeo(){
        return scSeo;
    }

    public void setScSeo(String scSeo){
        this.scSeo = scSeo;
    }

    public String getGjcSeo(){
        return gjcSeo;
    }

    public void setGjcSeo(String gjcSeo){
        this.gjcSeo = gjcSeo;
    }

    public String getQtySeo(){
        return qtySeo;
    }

    public void setQtySeo(String qtySeo){
        this.qtySeo = qtySeo;
    }

    public String getPhoneSeo(){
        return phoneSeo;
    }

    public void setPhoneSeo(String phoneSeo){
        this.phoneSeo = phoneSeo;
    }

    public String getWzbaSeo(){
        return wzbaSeo;
    }

    public void setWzbaSeo(String wzbaSeo){
        this.wzbaSeo = wzbaSeo;
    }

    public String getGgbaSeo(){
        return ggbaSeo;
    }

    public void setGgbaSeo(String ggbaSeo){
        this.ggbaSeo = ggbaSeo;
    }

    public String getGgljSeo(){
        return ggljSeo;
    }

    public void setGgljSeo(String ggljSeo){
        this.ggljSeo = ggljSeo;
    }

    public String getDsfSeo(){
        return dsfSeo;
    }

    public void setDsfSeo(String dsfSeo){
        this.dsfSeo = dsfSeo;
    }
}
