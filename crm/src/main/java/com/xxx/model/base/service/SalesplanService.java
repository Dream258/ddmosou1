package com.xxx.model.base.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.xxx.common.framework.base.BaseService;
import com.xxx.model.base.dao.SalesplanDAO;
import com.xxx.model.base.entity.Salesplan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * 销售计划业务逻辑类
 */
@Service
public class SalesplanService extends BaseService<Salesplan> {

    @Resource
    private SalesplanDAO salesplanDAO;

    /** 保存 */
    @Transactional
    public void save(Salesplan salesplan) {
        if(salesplan.getId() == null){
            this.insert(salesplan);
        }else{
            this.updateParams(salesplan);
        }
    }

    public void add(Salesplan salesplan){
        salesplan.setCreatetime(new Date());
        salesplanDAO.insert(salesplan);
    }

    public Page<Map<String,Object>> findList(Page<Map<String,Object>> page, Salesplan salesplan){
        return page.setRecords(salesplanDAO.findList(page, salesplan));
    }

}