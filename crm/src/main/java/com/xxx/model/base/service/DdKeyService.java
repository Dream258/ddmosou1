package com.xxx.model.base.service;

import com.xxx.common.framework.base.BaseService;
import com.xxx.model.base.dao.DdKeyDAO;
import com.xxx.model.base.entity.DdKey;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 关键词业务逻辑类
 */
@Service
public class DdKeyService extends BaseService<DdKey> {

    @Resource
    private DdKeyDAO ddKeyDAO;

    /** 保存 */
    @Transactional
    public void save(DdKey ddKey) {
        if(ddKey.getId() == null){
            this.insert(ddKey);
        }else{
            this.updateParams(ddKey);
        }
    }

    public int getListSize(int userId){
        return ddKeyDAO.getListSize(userId);
    }

    public Map<String,Object> getList(int userId){
        return ddKeyDAO.getList(userId);
    }

}