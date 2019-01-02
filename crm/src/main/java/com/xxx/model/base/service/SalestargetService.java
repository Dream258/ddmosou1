package com.xxx.model.base.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.xxx.common.framework.base.BaseService;
import com.xxx.model.base.dao.SalestargetDAO;
import com.xxx.model.base.entity.Salestarget;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * 销售目标业务逻辑类
 */
@Service
public class SalestargetService extends BaseService<Salestarget> {

    @Resource
    private SalestargetDAO salestargetDAO;

    /** 保存 */
    @Transactional
    public void save(Salestarget salestarget) {
        if(salestarget.getId() == null){
            this.insert(salestarget);
        }else{
            this.updateParams(salestarget);
        }
    }

    public void add(Salestarget salestarget){
        salestarget.setRate(0.0);
        salestarget.setCreatetime(new Date());
        salestargetDAO.insert(salestarget);
    }

    public Page<Map<String,Object>> findList(Page<Map<String,Object>> page,int department){
        return page.setRecords(salestargetDAO.findList(page,department));
    }

}