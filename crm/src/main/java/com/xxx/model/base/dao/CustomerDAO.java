package com.xxx.model.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xxx.common.framework.base.BaseDAO;
import com.xxx.model.base.entity.Customer;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 客户信息持久层
 */
@Component
public interface CustomerDAO extends BaseMapper<Customer> {

    /** 客户信息列表 */
    List<Map<String,Object>> findList(Page<Map<String,Object>> page,@Param("entity") Customer c);

    /** 客户信息详情 */
    @Select("SELECT c.*,u.name AS agentName FROM customer c JOIN sys_user u ON u.id = c.agent WHERE c.id = #{id}")
    Map<String,Object> getCustomerById(@Param("id") int id);

    @Select("SELECT id,name FROM customer WHERE department = #{department}")
    List<Map<String,Object>> findListByDepartment(@Param("department") int department);

    @Select("SELECT id,name FROM customer WHERE agent = #{agent}")
    List<Map<String,Object>> findListByAgent(@Param("agent") int agent);

    @Select("SELECT agent FROM customer WHERE id = #{id}")
    int getAgentById(@Param("id") int id);

    @Select("SELECT IFNULL(MAX(id),0) AS id FROM customer WHERE zmxg = #{zmxg}")
    int getIdByZMXG(@Param("zmxg") String zmxg);

    @Select("SELECT IFNULL(MAX(state),3) AS id FROM customer WHERE zmxg = #{zmxg}")
    int getStateByZMXG(@Param("zmxg") String zmxg);

    @Select("SELECT COUNT(id) FROM customer WHERE state = 0 ")
    int findNum();

    int countByDepartment(@Param("department") int department,@Param("agent") int agent);

    int countTodayByDepartment(@Param("department") int department,@Param("agent") int agent);

    List<Map<String,Object>> countGroup(@Param("department") int department,@Param("start") String start,@Param("end") String end);

    List<Map<String,Object>> countGroupByYear(@Param("agent") int agent,@Param("department") int department,@Param("year") String year);
}
@Component
interface AutoCustomerDAO extends BaseDAO<Customer>{

}