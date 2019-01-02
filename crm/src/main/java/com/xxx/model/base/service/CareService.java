package com.xxx.model.base.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.xxx.common.framework.base.BaseService;
import com.xxx.common.util.SmsUtil;
import com.xxx.model.base.dao.CareDAO;
import com.xxx.model.base.entity.Care;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 客户关怀业务逻辑类
 */
@Service
public class CareService extends BaseService<Care> {

    @Resource
    private CareDAO careDAO;

    /** 保存 */
    @Transactional
    public void save(Care care) {
        if(care.getId() == null){
            this.insert(care);
        }else{
            this.updateParams(care);
        }
    }

    public void add(Care care){
        careDAO.insert(care);
        String[] phones = { care.getPhone() };
        SmsUtil.sendSMS(phones, care.getContent(),5);
    }

    public Page<Map<String,Object>> findList(Page<Map<String,Object>> page, Care care){
        return page.setRecords(careDAO.findList(page, care));
    }

    public Map<String,Object> getCareById(int id){
        return careDAO.getCareById(id);
    }

}