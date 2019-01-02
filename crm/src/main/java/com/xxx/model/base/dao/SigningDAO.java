package com.xxx.model.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xxx.common.framework.base.BaseDAO;
import com.xxx.model.base.entity.Signing;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 客户签约持久层
 */
@Component
public interface SigningDAO extends BaseMapper<Signing> {

    @Select("SELECT product FROM signing WHERE number = #{number}")
    int findProductByNumber(@Param("number") String number);

    @Select("SELECT id FROM signing WHERE number = #{number}")
    int findIdByNumber(@Param("number") String number);

    @Select("SELECT customer FROM signing WHERE id = #{id}")
    int findCustomerById(@Param("id") int id);

    @Select("SELECT COUNT(id) FROM signing WHERE state = 0 ")
    int findNum();

    @Select("SELECT number FROM signing WHERE customer = #{customer} AND type = 2")
    List<Map<String,Object>> findNumberList(@Param("customer") int customer);

    /** 客户签约列表 */
    List<Map<String,Object>> findList(Page<Map<String,Object>> page,@Param("entity") Signing signing);

    /** 合同过期列表 */
    List<Map<String,Object>> findExpiredList(Page<Map<String,Object>> page,@Param("department") int department,
                                             @Param("customerName") String customerName,@Param("type") String type);

    /** 客户签约列表 */
    @Select("SELECT a.id,p.name as name,a.profit FROM signing a JOIN product p ON p.id = a.product " +
            "WHERE a.customer = #{customer} AND a.type = 2")
    List<Map<String,Object>> findListByCustomer(@Param("customer") int customer);

    /** 客户签约详情 */
    Map<String,Object> getSigningById(@Param("id") int id);

    /** 客户签约详情 */
    Map<String,Object> getSigningById2(@Param("id") int id);

    String sumByDepartment(@Param("department") int department,@Param("agent") int agent);

    String sumMonthByDepartment(@Param("department") int department,@Param("agent") int agent);

    List<Map<String,Object>> sumGroup(@Param("department") int department,@Param("start") String start,@Param("end") String end);

    List<Map<String,Object>> sumGroupByYear(@Param("agent") int agent,@Param("department") int department,@Param("year") String year);

    List<Map<String,Object>> sumGroupByMonth(@Param("department") int department);

    List<Map<String,Object>> sumGroupByMonth2(@Param("agent") int agent,@Param("department") int department);

}
@Component
interface AutoSigningDAO extends BaseDAO<Signing>{

}