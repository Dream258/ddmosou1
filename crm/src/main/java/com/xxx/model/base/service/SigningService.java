package com.xxx.model.base.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.xxx.common.framework.base.BaseService;
import com.xxx.model.base.dao.SigningDAO;
import com.xxx.model.base.entity.Signing;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 客户签约业务逻辑类
 */
@Service
public class SigningService extends BaseService<Signing> {

    @Resource
    private SigningDAO signingDAO;

    @Resource
    private InformationService informationService;

    /** 保存 */
    @Transactional
    public void save(Signing signing) {
        if(signing.getId() == null){
            this.insert(signing);
        }else{
            this.updateParams(signing);
        }
    }

    public void add(Signing signing){
        Date time = new Date();
        signing.setType("1");
        signing.setState("0");
        signing.setCash("0");
        signing.setImgB("");
        signing.setImgD("");
        signing.setImgE("");
        signing.setProfit(0.0);
        signing.setCashSum(0.0);
        signing.setCashTime(time);
        signing.setCreatetime(time);
        signingDAO.insert(signing);
    }

    public void update(Signing signing){
        signingDAO.updateById(signing);
    }

    public int getCustomerAndUpdate(Signing signing){
        signing.setType("2");
        signingDAO.updateById(signing);
        Signing s = signingDAO.selectById(signing.getId());
        return s.getCustomer();
    }

    public void examine(int id,String state,String remarks){
        Signing signing = signingDAO.selectById(id);
        signing.setState(state);
        signingDAO.updateById(signing);
        if("2".equals(state)){
            informationService.add(signing.getAgent(),remarks,"2");
        }
    }

    public int cash(Signing signing){
        signing.setCash("1");
        signingDAO.updateById(signing);
        Signing s = signingDAO.selectById(signing.getId());
        return s.getCustomer();
    }

    public void profit(int id,double sum){
        Signing signing = signingDAO.selectById(id);
        signing.setProfit(signing.getProfit()+sum);
        signingDAO.updateById(signing);
    }

    public int findNum(){
        return signingDAO.findNum();
    }

    public int findProductByNumber(String number){
        return signingDAO.findProductByNumber(number);
    }

    public int findIdByNumber(String number){
        return signingDAO.findIdByNumber(number);
    }

    public int findCustomerById(int id){ return signingDAO.findCustomerById(id); }

    public List<Map<String,Object>> findNumberList(int customer){
        return signingDAO.findNumberList(customer);
    }

    public Page<Map<String,Object>> findExpiredList(Page<Map<String,Object>> page,
                                                    int department,String customerName,String type){
        return page.setRecords(signingDAO.findExpiredList(page,department,customerName,type));
    }

    public Page<Map<String,Object>> findList(Page<Map<String,Object>> page,Signing signing){
        return page.setRecords(signingDAO.findList(page,signing));
    }

    public List<Map<String,Object>> findListByCustomer(int customer){
        return signingDAO.findListByCustomer(customer);
    }

    public Map<String,Object> getSigningById(int id){
        return signingDAO.getSigningById(id);
    }

    public Map<String,Object> getSigningById2(int id){
        return signingDAO.getSigningById2(id);
    }

    public String sumByDepartment(int department,int agent){
        return signingDAO.sumByDepartment(department,agent);
    }

    public String sumMonthByDepartment(int department,int agent){
        return signingDAO.sumMonthByDepartment(department,agent);
    }

    public List<Map<String,Object>> sumGroup(int department,String start,String end){
        return signingDAO.sumGroup(department,start,end);
    }

    public List<Map<String,Object>> sumGroupByYear(int agent,int department,String year){
        return signingDAO.sumGroupByYear(agent,department,year);
    }

    public List<Map<String,Object>> sumGroupByMonth(int department){
        return signingDAO.sumGroupByMonth(department);
    }

    public List<Map<String,Object>> sumGroupByMonth2(int agent,int department){
        return signingDAO.sumGroupByMonth2(agent,department);
    }
}