package com.xxx.model.base.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 产品管理实体类
 */
@TableName("product")
public class Product extends Model<Product> {

    @Override
    protected Serializable pkVal(){ return this.id; }

    private static final long serialVersionUID = 1L;

    /**  产品管理 */
    private Integer id;
    /**  产品名称 */
    private String name;
    /**  产品管理人 */
    private String custodian;
    /**  产品类型 */
    private String type;
    /**  募集规模 */
    private String scale;
    /**  年华收益率 */
    private String rate;
    /**  开始时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date start;
    /**  结束时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date end;
    /**  合伙企业 */
    private String partnership;
    /**  认购起点 */
    private String point;
    /**  项目详情 */
    private String details;
    /**  信息披露 */
    private String disclosure;
    /**  停售状态 */
    private String halt;
    /**  产品图片 */
    private String image;
    /**  创建时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createtime;

    public Product(){
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

    public String getCustodian(){
        return custodian;
    }

    public void setCustodian(String custodian){
        this.custodian = custodian;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getScale(){
        return scale;
    }

    public void setScale(String scale){
        this.scale = scale;
    }

    public String getRate(){
        return rate;
    }

    public void setRate(String rate){
        this.rate = rate;
    }

    public java.util.Date getStart(){
        return start;
    }

    public void setStart(java.util.Date start){
        this.start = start;
    }

    public java.util.Date getEnd(){
        return end;
    }

    public void setEnd(java.util.Date end){
        this.end = end;
    }

    public String getPartnership(){
        return partnership;
    }

    public void setPartnership(String partnership){
        this.partnership = partnership;
    }

    public String getPoint(){
        return point;
    }

    public void setPoint(String point){
        this.point = point;
    }

    public String getDetails(){
        return details;
    }

    public void setDetails(String details){
        this.details = details;
    }

    public String getDisclosure(){
        return disclosure;
    }

    public void setDisclosure(String disclosure){
        this.disclosure = disclosure;
    }

    public String getHalt(){
        return halt;
    }

    public void setHalt(String halt){
        this.halt = halt;
    }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public void setCreatetime(java.util.Date createtime){
        this.createtime = createtime;
    }
}
