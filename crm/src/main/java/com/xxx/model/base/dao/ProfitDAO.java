package com.xxx.model.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xxx.common.framework.base.BaseDAO;
import com.xxx.model.base.entity.Profit;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 签约收益持久层
 */
@Component
public interface ProfitDAO extends BaseMapper<Profit> {

    @Select("SELECT * FROM profit WHERE signing = #{signing} order by time desc")
    List<Map<String,Object>> findListBySigning(@Param("signing") int signing);

    @Select("SELECT sum FROM profit WHERE customer = #{customer} order by time desc")
    List<Map<String,Object>> findListByCustomer(@Param("customer") int customer);

}
@Component
interface AutoProfitDAO extends BaseDAO<Profit>{

}