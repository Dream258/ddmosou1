package com.xxx.model.base.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.xxx.common.framework.base.BaseService;
import com.xxx.common.util.SmsUtil;
import com.xxx.common.util.StringUtil;
import com.xxx.model.base.dao.CustomerDAO;
import com.xxx.model.base.entity.Customer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 客户信息业务逻辑类
 */
@Service
public class CustomerService extends BaseService<Customer> {

    @Resource
    private CustomerDAO customerDAO;

    @Resource
    private InformationService informationService;

    /** 保存 */
    @Transactional
    public void save(Customer customer) {
        if(customer.getId() == null){
            this.insert(customer);
        }else{
            this.updateParams(customer);
        }
    }

    public void add(Customer customer){
        customer.setDeal("0");
        customer.setCash("0");
        customer.setState("0");
        customer.setSeas("1");
        customer.setCreatetime(new Date());
        customerDAO.insert(customer);
    }

    public void update(Customer customer){
        customer.setState("0");
        customerDAO.updateById(customer);
    }

    public void agent(int id,int agent,int department){
        Customer customer = customerDAO.selectById(id);
        customer.setAgent(agent);
        customer.setDepartment(department);
        customerDAO.updateById(customer);
    }

    public void examine(int id,String state,String remarks){
        Customer customer = customerDAO.selectById(id);
        customer.setState(state);
        customerDAO.updateById(customer);
        if("2".equals(state)){
            informationService.add(customer.getAgent(),remarks,"1");
            String[] phones = { customer.getPhone() };
            SmsUtil.sendSMS(phones, remarks,5);
        }
    }

    public void seas(int id,String seas){
        Customer customer = new Customer();
        customer.setId(id);
        customer.setSeas(seas);
        customerDAO.updateById(customer);
    }

    public void deal(int id,String deal,String seas){
        Customer customer = new Customer();
        customer.setId(id);
        customer.setDeal(deal);
        customer.setSeas(seas);
        customerDAO.updateById(customer);
    }

    public void cash(int id,String cash,String deal,String seas){
        Customer customer = new Customer();
        customer.setId(id);
        customer.setCash(cash);
        customer.setDeal(deal);
        customer.setSeas(seas);
        customerDAO.updateById(customer);
    }

    public int findNum(){
        return customerDAO.findNum();
    }

    public Map<String,Object> getCustomerById(int id){
        return customerDAO.getCustomerById(id);
    }

    public Page<Map<String,Object>> findList(Page<Map<String,Object>> page, Customer customer){
        return page.setRecords(customerDAO.findList(page,customer));
    }

    public List<Map<String,Object>> findListByDepartment(int department){
        return customerDAO.findListByDepartment(department);
    }

    public List<Map<String,Object>> findListByAgent(int agent){
        return customerDAO.findListByAgent(agent);
    }

    public int getAgentById(int id){
        return customerDAO.getAgentById(id);
    }

    public int getIdByZMXG(String zmxg){
        return customerDAO.getIdByZMXG(zmxg);
    }

    public int getStateByZMXG(String zmxg){
        return customerDAO.getStateByZMXG(zmxg);
    }

    public int countByDepartment(int department,int agent){
        return customerDAO.countByDepartment(department,agent);
    }

    public int countTodayByDepartment(int department,int agent){
        return customerDAO.countTodayByDepartment(department,agent);
    }

    public List<Map<String,Object>> countGroup(int department,String start,String end){
        return customerDAO.countGroup(department,start,end);
    }

    public List<Map<String,Object>> countGroupByYear(int agent,int department,String year){
        return customerDAO.countGroupByYear(agent,department,year);
    }

}