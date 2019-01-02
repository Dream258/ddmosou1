package com.xxx.model.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xxx.common.framework.base.BaseDAO;
import com.xxx.model.base.entity.Salesplan;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 销售计划持久层
 */
@Component
public interface SalesplanDAO extends BaseMapper<Salesplan> {

    /** 计划列表 */
    List<Map<String,Object>> findList(Page<Map<String,Object>> page, @Param("entity") Salesplan s);

}
@Component
interface AutoSalesplanDAO extends BaseDAO<Salesplan>{

}