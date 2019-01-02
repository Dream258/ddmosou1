package com.xxx.model.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xxx.common.framework.base.BaseDAO;
import com.xxx.model.base.entity.Salestarget;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 销售目标持久层
 */
@Component
public interface SalestargetDAO extends BaseMapper<Salestarget> {

    /** 销售目标列表 */
    List<Map<String,Object>> findList(Page<Map<String,Object>> page, @Param("department") int department);

}
@Component
interface AutoSalestargetDAO extends BaseDAO<Salestarget>{

}