package com.xxx.model.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xxx.common.framework.base.BaseDAO;
import com.xxx.model.base.entity.DdManager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * 模块管理持久层
 */
@Component
public interface DdManagerDAO extends BaseMapper<DdManager> {

}
@Component
interface AutoDdManagerDAO extends BaseDAO<DdManager>{

}