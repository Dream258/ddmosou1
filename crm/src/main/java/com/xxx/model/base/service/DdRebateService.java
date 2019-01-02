package com.xxx.model.base.service;

import com.xxx.common.framework.base.BaseService;
import com.xxx.model.base.dao.DdRebateDAO;
import com.xxx.model.base.entity.DdRebate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 积分管理业务逻辑类
 */
@Service
public class DdRebateService extends BaseService<DdRebate> {

    @Resource
    private DdRebateDAO ddRebateDAO;

    /** 保存 */
    @Transactional
    public void save(DdRebate ddRebate) {
        if(ddRebate.getId() == null){
            this.insert(ddRebate);
        }else{
            this.updateParams(ddRebate);
        }
    }
}