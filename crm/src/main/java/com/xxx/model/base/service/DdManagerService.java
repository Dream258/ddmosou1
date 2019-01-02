package com.xxx.model.base.service;

import com.xxx.common.framework.base.BaseService;
import com.xxx.model.base.dao.DdManagerDAO;
import com.xxx.model.base.entity.DdManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 模块管理业务逻辑类
 */
@Service
public class DdManagerService extends BaseService<DdManager> {

    @Resource
    private DdManagerDAO ddManagerDAO;

    /** 保存 */
    @Transactional
    public void save(DdManager ddManager) {
        if(ddManager.getId() == null){
            this.insert(ddManager);
        }else{
            this.updateParams(ddManager);
        }
    }
}