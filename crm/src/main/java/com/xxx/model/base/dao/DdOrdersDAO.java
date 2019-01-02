package com.xxx.model.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xxx.common.framework.base.BaseDAO;
import com.xxx.model.base.entity.DdOrders;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 订单管理持久层
 */
@Component
public interface DdOrdersDAO extends BaseMapper<DdOrders> {
    // 根据手机查询订单
    List<DdOrders> getOrdersByTelephone(@Param("userTelephone") String userTelephone);

    // 按手机和分页查询
    List<DdOrders> getOrdersByTelephoneForPage(Page<DdOrders> page, Map map);

    // 按手机查询总条数
    //Integer getOrdersCountByPhone(@Param("telephone") String telephone);
}
@Component
interface AutoDdOrdersDAO extends BaseDAO<DdOrders>{

}