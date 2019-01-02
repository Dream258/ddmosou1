package com.xxx.model.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xxx.common.framework.base.BaseDAO;
import com.xxx.model.base.entity.Salesallot;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 销售分配持久层
 */
@Component
public interface SalesallotDAO extends BaseMapper<Salesallot> {

    @Select("select * from salesallot where target = #{target} and department = #{department} and level = #{level}")
    Salesallot findAllot(@Param("target") int target,@Param("department") int department,@Param("level") String level);

    @Select("SELECT s.id,s.sum,d.name AS departName FROM salesallot s JOIN sys_department d ON d.id = s.department " +
            "WHERE s.target = #{target} AND s.level = #{level}")
    List<Map<String,Object>> findList(@Param("target") int target,@Param("level") String level);

    @Select("SELECT s.id,s.sum,u.name AS departName FROM salesallot s JOIN sys_user u ON u.id = s.department " +
            "WHERE s.target = #{target} AND s.level = #{level}")
    List<Map<String,Object>> findList2(@Param("target") int target,@Param("level") String level);

}
@Component
interface AutoSalesallotDAO extends BaseDAO<Salesallot>{

}