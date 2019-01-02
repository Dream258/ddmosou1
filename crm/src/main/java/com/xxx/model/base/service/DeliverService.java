package com.xxx.model.base.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.xxx.common.framework.base.BaseService;
import com.xxx.model.base.dao.DeliverDAO;
import com.xxx.model.base.entity.Deliver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 客户转交业务逻辑类
 */
@Service
public class DeliverService extends BaseService<Deliver> {

    @Resource
    private DeliverDAO deliverDAO;

    /** 保存 */
    @Transactional
    public void save(Deliver deliver) {
        if(deliver.getId() == null){
            this.insert(deliver);
        }else{
            this.updateParams(deliver);
        }
    }

    public void add(int customer,int transfer,int receive,String type,int department){
        Deliver deliver = new Deliver();
        deliver.setDepartment(department);
        deliver.setCustomer(customer);
        deliver.setReceive(receive);
        deliver.setTransfer(transfer);
        deliver.setType(type);
        deliver.setCreatetime(new Date());
        deliverDAO.insert(deliver);
    }

    public Page<Deliver> findList(Page<Deliver> page,Deliver deliver){
        return page.setRecords(deliverDAO.findList(page,deliver));
    }
}