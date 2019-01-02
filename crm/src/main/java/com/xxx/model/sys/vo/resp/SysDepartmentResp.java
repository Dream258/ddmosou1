package com.xxx.model.sys.vo.resp;

import java.io.Serializable;
import java.util.List;

public class SysDepartmentResp implements Serializable {

    private static final long serialVersionUID = 919969705546307406L;

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
    /** 子部门 */
    private List<SysDepartmentResp> sysDepartmentResps;

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

    public List<SysDepartmentResp> getSysDepartmentResps() {
        return sysDepartmentResps;
    }

    public void setSysDepartmentResps(List<SysDepartmentResp> sysDepartmentResps) { this.sysDepartmentResps = sysDepartmentResps; }
}
