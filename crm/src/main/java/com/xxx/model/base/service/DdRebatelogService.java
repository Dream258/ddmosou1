package com.xxx.model.base.service;

import com.xxx.common.framework.base.BaseService;
import com.xxx.model.base.dao.DdRebatelogDAO;
import com.xxx.model.base.entity.DdRebatelog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 积分日志业务逻辑类
 */
@Service
public class DdRebatelogService extends BaseService<DdRebatelog> {

    @Resource
    private DdRebatelogDAO ddRebatelogDAO;

    /** 保存 */
    @Transactional
    public void save(DdRebatelog ddRebatelog) {
        if(ddRebatelog.getId() == null){
            this.insert(ddRebatelog);
        }else{
            this.updateParams(ddRebatelog);
        }
    }

    // 根据被邀请人查询记录
    public Integer selectByTelephoneS(String telephone) {
        return ddRebatelogDAO.selectByTelephoneS(telephone);
    }
}