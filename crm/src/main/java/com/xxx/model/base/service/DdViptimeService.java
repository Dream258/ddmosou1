package com.xxx.model.base.service;

import com.xxx.common.framework.base.BaseService;
import com.xxx.model.base.dao.DdViptimeDAO;
import com.xxx.model.base.entity.DdViptime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 会员日期业务逻辑类
 */
@Service
public class DdViptimeService extends BaseService<DdViptime> {

    @Resource
    private DdViptimeDAO ddViptimeDAO;

    /** 保存 */
    @Transactional
    public void save(DdViptime ddViptime) {
        if(ddViptime.getId() == null){
            this.insert(ddViptime);
        }else{
            this.updateParams(ddViptime);
        }
    }

    //查询到期天数
    public Integer selectDays(String telephone) {
        return ddViptimeDAO.selectDays(telephone);
    }
}