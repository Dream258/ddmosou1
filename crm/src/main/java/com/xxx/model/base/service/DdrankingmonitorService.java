package com.xxx.model.base.service;

import com.xxx.common.framework.base.BaseService;
import com.xxx.model.base.dao.DdrankingmonitorDAO;
import com.xxx.model.base.entity.Ddrankingmonitor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DdrankingmonitorService extends BaseService<Ddrankingmonitor> {
    @Resource
    private DdrankingmonitorDAO ddrankingmonitorDAO;

    // 查询信息
    public Ddrankingmonitor fandMoney(Integer serviceCycle, Integer type) {
        return ddrankingmonitorDAO.fandMoney(serviceCycle, type);
    }

}
