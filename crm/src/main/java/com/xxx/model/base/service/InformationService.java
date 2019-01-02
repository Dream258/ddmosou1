package com.xxx.model.base.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.xxx.common.framework.base.BaseService;
import com.xxx.model.base.dao.InformationDAO;
import com.xxx.model.base.entity.Information;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 我的消息业务逻辑类
 */
@Service
public class InformationService extends BaseService<Information> {

    @Resource
    private InformationDAO informationDAO;

    /** 保存 */
    @Transactional
    public void save(Information information) {
        if(information.getId() == null){
            this.insert(information);
        }else{
            this.updateParams(information);
        }
    }

    public void  add(int target,String remarks,String type){
        Information information = new Information();
        information.setType(type);
        information.setTarget(target);
        information.setRemarks(remarks);
        information.setCreatetime(new Date());
        informationDAO.insert(information);
    }

    public Page<Map<String,Object>> findList(Page<Map<String,Object>> page, Information information){
        List<Map<String,Object>> list = informationDAO.findList(page, information);
        for (Map<String,Object> po:list){
            if("0".equals(po.get("read").toString())){
                informationDAO.updateRead(Integer.parseInt(po.get("id").toString()));
            }
        }
        return page.setRecords(list);
    }

    public int findNum(int target){
        return informationDAO.findNum(target);
    }
}