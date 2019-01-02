package com.xxx.model.base.service;

import com.xxx.common.framework.base.BaseService;
import com.xxx.model.base.dao.DdEmailDAO;
import com.xxx.model.base.entity.DdEmail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 邮件通知业务逻辑类
 */
@Service
public class DdEmailService extends BaseService<DdEmail> {

    @Resource
    private DdEmailDAO ddEmailDAO;

    /** 保存 */
    @Transactional
    public void save(DdEmail ddEmail) {
        if(ddEmail.getId() == null){
            this.insert(ddEmail);
        }else{
            this.updateParams(ddEmail);
        }
    }
}