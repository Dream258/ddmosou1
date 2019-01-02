package com.xxx.model.base.service;

import com.xxx.common.framework.base.BaseService;
import com.xxx.model.base.dao.SeoBackDAO;
import com.xxx.model.base.entity.SeoBack;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 配置测试业务逻辑类
 */
@Service
public class SeoBackService extends BaseService<SeoBack> {

    @Resource
    private SeoBackDAO seoBackDAO;

    /** 保存 */
    @Transactional
    public void save(SeoBack seoBack) {
        if(seoBack.getIdSeo() == null){
            this.insert(seoBack);
        }else{
            this.updateParams(seoBack);
        }
    }
}