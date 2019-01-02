package com.xxx.model.base.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户签约实体类
 */
@TableName("signing")
public class Signing extends Model<Signing> {

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    private static final long serialVersionUID = 1L;

    /**  客户签约 */
    private Integer id;
    /**  所属客户 */
    private Integer customer;
    /**  所属部门 */
    private Integer department;
    /**  所属产品 */
    private Integer product;
    /**  合同编号 */
    private String number;
    /**  投资时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date time;
    /**  投资金额 */
    private Double sum;
    /**  兑付状态 */
    private String cash;
    /**  回访状态 */
    private String visit;
    /**  合同状态 */
    private String contract;
    /**  开始时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date start;
    /**  结束时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date end;
    /**  封闭期限 */
    private String closed;
    /**  收益金额 */
    private Double profit;
    /**  经办人 */
    private Integer agent;
    /**  审核状态 */
    private String state;
    /**  认定状态 */
    private String type;
    /**  创建时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date createtime;
    /**  兑付金额 */
    private Double cashSum;
    /**  兑付时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date cashTime;
    /**  基础信息表 */
    private String imgA;
    /**  风险匹配告知书及确认函 */
    private String imgB;
    /**  风险调查问卷 */
    private String imgC;
    /**  打款凭证 */
    private String imgD;
    /**  银行卡 */
    private String imgE;

    public Signing(){
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

    public Integer getProduct(){
        return product;
    }

    public void setProduct(Integer product){
        this.product = product;
    }

    public String getNumber(){
        return number;
    }

    public void setNumber(String number){
        this.number = number;
    }

    public java.util.Date getTime(){
        return time;
    }

    public void setTime(java.util.Date time){
        this.time = time;
    }

    public Double getSum(){
        return sum;
    }

    public void setSum(Double sum){
        this.sum = sum;
    }

    public String getCash() { return cash; }

    public void setCash(String cash) { this.cash = cash; }

    public String getVisit(){
        return visit;
    }

    public void setVisit(String visit){
        this.visit = visit;
    }

    public String getContract(){
        return contract;
    }

    public void setContract(String contract){
        this.contract = contract;
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

    public String getClosed(){
        return closed;
    }

    public void setClosed(String closed){
        this.closed = closed;
    }

    public Double getProfit(){
        return profit;
    }

    public void setProfit(Double profit){
        this.profit = profit;
    }

    public Integer getAgent(){
        return agent;
    }

    public void setAgent(Integer agent){
        this.agent = agent;
    }

    public String getState(){
        return state;
    }

    public void setState(String state){
        this.state = state;
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public void setCreatetime(java.util.Date createtime){
        this.createtime = createtime;
    }

    public Double getCashSum() { return cashSum; }

    public void setCashSum(Double cashSum) { this.cashSum = cashSum; }

    public Date getCashTime() { return cashTime; }

    public void setCashTime(Date cashTime) { this.cashTime = cashTime; }

    public String getImgA() { return imgA; }

    public void setImgA(String imgA) { this.imgA = imgA; }

    public String getImgB() { return imgB; }

    public void setImgB(String imgB) { this.imgB = imgB; }

    public String getImgC() { return imgC; }

    public void setImgC(String imgC) { this.imgC = imgC; }

    public String getImgD() { return imgD; }

    public void setImgD(String imgD) { this.imgD = imgD; }

    public String getImgE() { return imgE; }

    public void setImgE(String imgE) { this.imgE = imgE; }
}
