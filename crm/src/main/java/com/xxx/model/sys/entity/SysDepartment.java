package com.xxx.model.sys.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.List;

/**
 * 部门管理实体类
 */
@TableName("sys_department")
public class SysDepartment extends Model<SysDepartment> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**  部门ID */
    private Long id;
    /**  部门名称 */
    private String name;
    /**  父类ID */
    private Long fid;
    /**  排序 */
    private Integer sort;
    /**  备注 */
    private String remarks;
    /**  状态 */
    private Integer status;
    /**  级别 */
    private Integer level;

    @TableField(exist = false)
    private List<SysUser> users;

    public SysDepartment(){
    }

    // -------------------- GET AND SET --------------------

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Long getFid(){
        return fid;
    }

    public void setFid(Long fid){
        this.fid = fid;
    }

    public Integer getSort(){
        return sort;
    }

    public void setSort(Integer sort){
        this.sort = sort;
    }

    public String getRemarks(){
        return remarks;
    }

    public void setRemarks(String remarks){
        this.remarks = remarks;
    }

    public Integer getStatus(){
        return status;
    }

    public void setStatus(Integer status){
        this.status = status;
    }

    public Integer getLevel(){ return level; }

    public void setLevel(Integer level){
        this.level = level;
    }

    public List<SysUser> getUsers() { return users; }

    public void setUsers(List<SysUser> users) { this.users = users; }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
