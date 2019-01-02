package com.xxx.model.base.service;

import com.xxx.common.framework.base.BaseService;
import com.xxx.model.base.dao.DdSeoDAO;
import com.xxx.model.base.entity.DdSeo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * seo管理业务逻辑类
 */
@Service
public class DdSeoService extends BaseService<DdSeo> {

    @Resource
    private DdSeoDAO ddSeoDAO;

    /** 保存 */
    @Transactional
    public void save(DdSeo ddSeo) {
        if(ddSeo.getId() == null){
            this.insert(ddSeo);
        }else{
            this.updateParams(ddSeo);
        }
    }
}