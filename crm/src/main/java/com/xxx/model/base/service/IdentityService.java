package com.xxx.model.base.service;

import com.xxx.common.framework.base.BaseService;
import com.xxx.model.base.dao.IdentityDAO;
import com.xxx.model.base.entity.Identity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 身份信息业务逻辑类
 */
@Service
public class IdentityService extends BaseService<Identity> {

    @Resource
    private IdentityDAO identityDAO;

    /** 保存 */
    @Transactional
    public void save(Identity identity) {
        if(identity.getId() == null){
            this.insert(identity);
        }else{
            this.updateParams(identity);
        }
    }
}