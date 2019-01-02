package com.xxx.model.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xxx.common.framework.base.BaseDAO;
import com.xxx.model.base.entity.DdSeo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * seo管理持久层
 */
@Component
public interface DdSeoDAO extends BaseMapper<DdSeo> {

}
@Component
interface AutoDdSeoDAO extends BaseDAO<DdSeo>{

}