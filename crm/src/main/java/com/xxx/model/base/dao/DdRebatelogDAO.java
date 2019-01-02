package com.xxx.model.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xxx.common.framework.base.BaseDAO;
import com.xxx.model.base.entity.DdRebatelog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * 积分日志持久层
 */
@Component
public interface DdRebatelogDAO extends BaseMapper<DdRebatelog> {

    // 根据被邀请人查询记录
    public Integer selectByTelephoneS(@Param("telephone") String telephone);

}
@Component
interface AutoDdRebatelogDAO extends BaseDAO<DdRebatelog>{

}