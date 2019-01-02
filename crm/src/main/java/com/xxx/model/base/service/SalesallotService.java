package com.xxx.model.base.service;

import com.alibaba.fastjson.JSON;
import com.xxx.common.framework.base.BaseService;
import com.xxx.model.base.dao.SalesallotDAO;
import com.xxx.model.base.entity.Salesallot;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 销售分配业务逻辑类
 */
@Service
public class SalesallotService extends BaseService<Salesallot> {

    @Resource
    private SalesallotDAO salesallotDAO;

    /** 保存 */
    @Transactional
    public void save(Salesallot salesallot) {
        if(salesallot.getId() == null){
            this.insert(salesallot);
        }else{
            this.updateParams(salesallot);
        }
    }

    public void add(List<Map<String,Object>> list,int target,String level){
        for(Map<String,Object> po:list){
            int id = Integer.parseInt(po.get("id").toString());
            Salesallot allot = salesallotDAO.findAllot(target,id,level);
            if(allot==null){
                Salesallot salesallot = new Salesallot();
                salesallot.setTarget(target);
                salesallot.setDepartment(id);
                salesallot.setSum(0.0);
                salesallot.setRate(0.0);
                salesallot.setLevel(level);
                salesallotDAO.insert(salesallot);
            }
        }
    }

    public void update(String json){
        List<Salesallot> list = JSON.parseArray(json,Salesallot.class);
        for (Salesallot salesallot:list){
            salesallotDAO.updateById(salesallot);
        }
    }

    public List<Map<String,Object>> findList(int target,String level){
        return salesallotDAO.findList(target,level);
    }

    public List<Map<String,Object>> findList2(int target,String level){
        return salesallotDAO.findList2(target,level);
    }

}