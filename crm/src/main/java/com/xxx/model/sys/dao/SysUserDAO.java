package com.xxx.model.sys.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xxx.common.framework.base.BaseDAO;
import com.xxx.model.sys.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 系统用户持久层接口
 */
@Component
public interface SysUserDAO extends BaseMapper<SysUser> {

    /** 查询用户 */
    @Select("SELECT u.id,u.name,u.phone,u.image,u.status,u.level,u.username,u.login_time FROM sys_user u " +
            "WHERE u.username = #{username} AND u.password = #{password}")
    SysUser selectOne(@Param("username")String username, @Param("password")String password);

    /** 判断账户存在 */
    @Select("SELECT u.id as user_id,u.name,u.phone,u.image,u.status,u.level,u.department_id,d.name AS department " +
            "FROM sys_user u JOIN sys_department d ON d.id = u.department_id WHERE 1=1 AND u.username = #{username} AND u.password = #{password}")
    Map<String,Object> exist(@Param("username") String username, @Param("password") String password);

    /** 用户登录成功 */
    @Update("update sys_user set login_time = NOW(),error_time = 0,error_date = null where id = #{id}")
    int userLogin(Long id);

    /** 上下架 */
    @Update("update sys_user set sys_user.status = #{status} where id = #{id}")
    void accredit(@Param("id")Long id, @Param("status")Integer status);

    @Select("SELECT id,name FROM sys_user WHERE department_id = #{department} AND status = 1 AND level = 3")
    List<Map<String,Object>> findListByDepartment(@Param("department") int department);

    @Select("SELECT id,name,level,phone,STATUS AS state FROM sys_user WHERE department_id = #{department} order by level")
    List<Map<String,Object>> findListByDepartmentAll(@Param("department") int department);

    @Select("SELECT id,name,phone,username FROM sys_user WHERE id = #{id}")
    Map<String,Object> findUserById(@Param("id") int id);

    @Select("SELECT u.id,u.name,u.level,u.phone,d.name as depart_name FROM sys_user u JOIN sys_department d ON d.id = " +
            "u.department_id WHERE u.status = #{state}")
    List<Map<String,Object>> findListByState(@Param("state") int state);

    @Select("SELECT department_id FROM sys_user WHERE id = #{id}")
    int findDepartmentById(@Param("id") int id);

    @Select("SELECT id FROM sys_user WHERE department_id = #{department} AND LEVEL = 2")
    int findIdByDepartment(@Param("department") int department);

    @Select("SELECT COUNT(id) FROM sys_user WHERE status = 0 ")
    int findNum();

}
@Component
interface AutoSysUserDAO extends BaseDAO<SysUser> {

}
