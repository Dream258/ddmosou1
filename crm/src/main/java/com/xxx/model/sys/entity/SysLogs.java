package com.xxx.model.sys.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 日志实体类
 */
public class SysLogs implements Serializable {

    private static final long serialVersionUID = 1L;

    /**  日志ID */
    private Integer id;
    /**  操作人ID */
    private Integer operId;
    /**  操作人 */
    private String operator;
    /**  操作时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createtime;
    /**  操作备注 */
    private String remarks;
    /**  操作类型 */
    private String type;

    public SysLogs(){
    }

    // -------------------- GET AND SET --------------------

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getOperId(){
        return operId;
    }

    public void setOperId(Integer operId){
        this.operId = operId;
    }

    public String getOperator(){
        return operator;
    }

    public void setOperator(String operator){
        this.operator = operator;
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public void setCreatetime(java.util.Date createtime){
        this.createtime = createtime;
    }

    public String getRemarks(){
        return remarks;
    }

    public void setRemarks(String remarks){
        this.remarks = remarks;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }
}
