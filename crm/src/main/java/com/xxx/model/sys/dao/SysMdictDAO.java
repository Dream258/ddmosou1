package com.xxx.model.sys.dao;


import com.xxx.common.framework.base.BaseDAO;
import com.xxx.model.sys.entity.SysMdict;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 数据字典持久层接口
 */
@Component
public interface SysMdictDAO {

    @Select("select sys_mdict.value,sys_mdict.info from sys_mdict where sys_mdict.title = #{title} order by order_no")
    List<SysMdict> selectByTitle(@Param("title")String title);

    @Select("select value as id,info as name from sys_mdict where title = #{title} order by order_no")
    List<Map<String,Object>> findByTitle(@Param("title") String title);
    
}
@Component
interface AutoSysMdictDAO extends BaseDAO<SysMdict> {

}
