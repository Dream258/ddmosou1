package com.xxx.model.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xxx.common.framework.base.BaseDAO;
import com.xxx.model.sys.entity.SysDepartment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 部门管理持久层
 */
@Component
public interface SysDepartmentDAO extends BaseMapper<SysDepartment> {

    @Update("update sys_department set sys_department.status = #{status} where id = #{id}")
    void accredit(@Param("id")Long id,@Param("status")Integer status);

    List<SysDepartment> findListById(@Param("id") int id);

    @Select("select id,name from sys_department where level = 2 order by sort asc")
    List<Map<String,Object>> findList();
}
@Component
interface AutoSysDepartmentDAO extends BaseDAO<SysDepartment>{

}