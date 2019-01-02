package com.xxx.model.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xxx.common.framework.base.BaseDAO;
import com.xxx.model.base.entity.DdRebate;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * 积分管理持久层
 */
@Component
public interface DdRebateDAO extends BaseMapper<DdRebate> {

}
@Component
interface AutoDdRebateDAO extends BaseDAO<DdRebate>{

}