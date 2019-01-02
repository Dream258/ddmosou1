package com.xxx.model.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xxx.common.framework.base.BaseDAO;
import com.xxx.model.base.entity.SeoBack;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * 配置测试持久层
 */
@Component
public interface SeoBackDAO extends BaseMapper<SeoBack> {

}
@Component
interface AutoSeoBackDAO extends BaseDAO<SeoBack>{

}