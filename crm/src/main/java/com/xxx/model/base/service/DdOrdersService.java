package com.xxx.model.base.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.xxx.common.framework.base.BaseService;
import com.xxx.model.base.dao.DdOrdersDAO;
import com.xxx.model.base.entity.DdOrders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 订单管理业务逻辑类
 */
@Service
public class DdOrdersService extends BaseService<DdOrders> {

    @Resource
    private DdOrdersDAO ddOrdersDAO;

    /** 保存 */
    @Transactional
    public void save(DdOrders ddOrders) {
        if(ddOrders.getId() == null){
            this.insert(ddOrders);
        }else{
            this.updateParams(ddOrders);
        }
    }

    public List<DdOrders> getOrdersByTelephone(String telephone) {
        return ddOrdersDAO.getOrdersByTelephone(telephone);
    }

    /*public Integer getOrdersCountByPhone(String telephone) {
        return ddOrdersDAO.getOrdersCountByPhone(telephone);
    }*/

    // 按分页查询
    public Page<DdOrders> getOrdersByTelephoneForPage(Page<DdOrders> page, Map map) {
        return page.setRecords(ddOrdersDAO.getOrdersByTelephoneForPage(page,map));
    }

}