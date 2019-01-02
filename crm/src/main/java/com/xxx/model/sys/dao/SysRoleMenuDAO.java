package com.xxx.model.sys.dao;


import com.xxx.common.framework.base.BaseDAO;
import com.xxx.model.sys.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 系统角色菜单关联持久层接口
 */
@Component
public interface SysRoleMenuDAO {

    /** 通过角色ID删除关联信息 */
    public void deleteByRoleId(@Param("roleId")Long roleId);
	
}
@Component
interface AutoSysRoleMenuDAO extends BaseDAO<SysRoleMenu> {

}