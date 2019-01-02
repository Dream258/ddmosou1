package com.xxx.model.sys.service;

import com.xxx.common.framework.base.BaseService;
import com.xxx.model.sys.dao.SysDepartmentDAO;
import com.xxx.model.sys.entity.SysDepartment;
import com.xxx.model.sys.vo.resp.SysDepartmentResp;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 部门管理业务逻辑类
 */
@Service
public class SysDepartmentService extends BaseService<SysDepartment> {

    @Resource
    private SysDepartmentDAO sysDepartmentDAO;

    /** 保存 */
    @Transactional
    public void save(SysDepartment sysDepartment) {
        if(sysDepartment.getId() == null){
            this.insert(sysDepartment);
        }else{
            this.updateParams(sysDepartment);
        }
    }

    public void add(SysDepartment sysDepartment){
        sysDepartment.setFid(1L);
        sysDepartment.setStatus(1);
        sysDepartment.setLevel(2);
        sysDepartment.setRemarks("");
        sysDepartmentDAO.insert(sysDepartment);
    }

    public List<Map<String,Object>> findList(){
        return sysDepartmentDAO.findList();
    }

    public List<SysDepartment> findListById(int id){
        return sysDepartmentDAO.findListById(id);
    }

    /** 状态 */
    public void accredit(Long id, Integer status) {
        sysDepartmentDAO.accredit(id,status);
    }

    /** 获取所有的菜单信息 */
    public List<SysDepartmentResp> getAllTreeList(){
        List<SysDepartment> sysDepartments = this.selectAll(new SysDepartment());
        return install(sysDepartments);
    }

    //拼装
    public List<SysDepartmentResp> install(List<SysDepartment> sysDepartments){
        List<SysDepartment> firstArray = sysDepartments.stream().filter(sysDepartment -> (sysDepartment.getLevel() == 1)).collect(Collectors.toList());
        List<SysDepartmentResp> sysDepartmentResps = sortSysMenuResp(firstArray);
        sysDepartmentResps.forEach(sysDepartResp -> {
            forEach(sysDepartResp,sysDepartments);
        });
        return sysDepartmentResps;
    }

    //迭代循环
    public void forEach(SysDepartmentResp sysDepartmentResp,List<SysDepartment> sysDepartments){
        List<SysDepartment> departments = sysDepartments.stream().filter(Department -> sysDepartmentResp.getId().equals(Department.getFid())).collect(Collectors.toList());
        if(departments != null && departments.size() > 0){
            List<SysDepartmentResp> childs = this.sortSysMenuResp(departments);
            sysDepartmentResp.setSysDepartmentResps(childs);
            childs.forEach(departmentResp -> {
                forEach(departmentResp,sysDepartments);
            });
        }
    }

    //排序并且转换实体类
    private List<SysDepartmentResp> sortSysMenuResp(List<SysDepartment> sysDepartments){
        //排序
        sysDepartments.sort(new Comparator<SysDepartment>() {
            @Override
            public int compare(SysDepartment o1, SysDepartment o2) {
                return o1.getSort().compareTo(o2.getSort());
            }
        });
        return sysDepartments.stream().map( sysDepartment -> toSysDepartmentResp(sysDepartment)).collect(Collectors.toList());
    }

    //转换实体类
    private SysDepartmentResp toSysDepartmentResp(SysDepartment sysDepartment){
        SysDepartmentResp sysDepartmentResp = new SysDepartmentResp();
        BeanUtils.copyProperties(sysDepartment, sysDepartmentResp);
        return sysDepartmentResp;
    }
}