package com.xxx.model.base.service;

import com.xxx.common.framework.base.BaseService;
import com.xxx.model.base.dao.ProfitDAO;
import com.xxx.model.base.entity.Profit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 签约收益业务逻辑类
 */
@Service
public class ProfitService extends BaseService<Profit> {

    @Resource
    private ProfitDAO profitDAO;

    /** 保存 */
    @Transactional
    public void save(Profit profit) {
        if(profit.getId() == null){
            this.insert(profit);
        }else{
            this.updateParams(profit);
        }
    }

    public void add(Profit profit){
        profitDAO.insert(profit);
    }

    public List<Map<String,Object>> findListBySigning(int signing){
        return profitDAO.findListBySigning(signing);
    }

    public List<Map<String,Object>> findListByCustomer(int customer){
        return profitDAO.findListByCustomer(customer);
    }
}