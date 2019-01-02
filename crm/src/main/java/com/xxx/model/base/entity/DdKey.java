package com.xxx.model.base.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 关键词实体类
 */
@TableName("ddKey")
public class DdKey extends Model<DdKey> {

    @Override
    protected Serializable pkVal(){ return this.id; }

    private static final long serialVersionUID = 1L;

    /**  关键词表 */
    private Integer id;
    /**  用户ID */
    private Integer userId;
    /**  商品关键词 */
    private String goodsKey;
    /**  商品ID */
    private String goodsId;
    /**  商品名称 */
    private String goodsName;
    /**  商品销量 */
    private String goodsSold;
    /**  商品图片 */
    private String goodsThumbnail;
    /**  商品团购价 */
    private String groupPrice;
    /**  商品单价 */
    private String normalPrice;
    /**  商品排名 */
    private String goodsTop;
    /**  商品时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date goodsTime;
    /**  类型 */
    private String type;

    public DdKey(){
    }

    // -------------------- GET AND SET --------------------

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this.userId = userId;
    }

    public String getGoodsKey(){
        return goodsKey;
    }

    public void setGoodsKey(String goodsKey){
        this.goodsKey = goodsKey;
    }

    public String getGoodsId(){
        return goodsId;
    }

    public void setGoodsId(String goodsId){
        this.goodsId = goodsId;
    }

    public String getGoodsName(){
        return goodsName;
    }

    public void setGoodsName(String goodsName){
        this.goodsName = goodsName;
    }

    public String getGoodsSold(){
        return goodsSold;
    }

    public void setGoodsSold(String goodsSold){
        this.goodsSold = goodsSold;
    }

    public String getGoodsThumbnail(){
        return goodsThumbnail;
    }

    public void setGoodsThumbnail(String goodsThumbnail){
        this.goodsThumbnail = goodsThumbnail;
    }

    public String getGroupPrice(){
        return groupPrice;
    }

    public void setGroupPrice(String groupPrice){
        this.groupPrice = groupPrice;
    }

    public String getNormalPrice(){
        return normalPrice;
    }

    public void setNormalPrice(String normalPrice){
        this.normalPrice = normalPrice;
    }

    public String getGoodsTop(){
        return goodsTop;
    }

    public void setGoodsTop(String goodsTop){
        this.goodsTop = goodsTop;
    }

    public java.util.Date getGoodsTime(){
        return goodsTime;
    }

    public void setGoodsTime(java.util.Date goodsTime){
        this.goodsTime = goodsTime;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }
}
