package com.xxx.model.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xxx.common.framework.base.BaseDAO;
import com.xxx.model.base.entity.Ddrankingmonitor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * vip服务价格持久层
 */
@Component
public interface DdrankingmonitorDAO extends BaseMapper<Ddrankingmonitor> {
    //查询信息
    public Ddrankingmonitor fandMoney(@Param("serviceCycle") Integer serviceCycle, @Param("type") Integer type);

}

@Component
interface AutoDdrankingmonitorDAO extends BaseDAO<Ddrankingmonitor> {

}