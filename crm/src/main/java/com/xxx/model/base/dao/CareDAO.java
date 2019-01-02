package com.xxx.model.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xxx.common.framework.base.BaseDAO;
import com.xxx.model.base.entity.Care;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 客户关怀持久层
 */
@Component
public interface CareDAO extends BaseMapper<Care> {

    /** 关怀列表 */
    List<Map<String,Object>> findList(Page<Map<String,Object>> page, @Param("entity") Care c);

    Map<String,Object> getCareById(@Param("id") int id);

}
@Component
interface AutoCareDAO extends BaseDAO<Care>{

}