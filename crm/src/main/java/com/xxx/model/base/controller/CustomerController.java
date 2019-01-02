package com.xxx.model.base.controller;

import com.xxx.common.bean.Pager;
import com.xxx.common.bean.ResultData;
import com.xxx.common.framework.base.BaseController;
import com.xxx.common.interfaces.Permission;
import com.xxx.model.base.entity.Customer;
import com.xxx.model.base.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * 客户信息视图层
 */
@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController {

    @Resource
    private CustomerService customerService;

    @ModelAttribute
    public Customer get(@RequestParam(required=false) Long id) {
        if (id != null){
            return customerService.selectById(id);
        }else{
            return new Customer();
        }
    }

    //分页列表
    @Permission("base:customer:view")
    @RequestMapping("page")
    public String page(Customer customer, Pager<Customer> pager, Model model){
        Pager<Customer> page = customerService.selectPage(customer, pager);
        model.addAttribute("pager",page);
        model.addAttribute("customer",customer);
        return "base/customer/customerPage";
    }

    //跳转添加编辑页面
    @Permission("base:customer:view")
    @GetMapping("saveFrom")
    public String saveFrom(Customer customer,Model model){
        model.addAttribute("customer",customer);
        return "base/customer/customerSave";
    }

    //添加编辑操作
    @Permission("base:customer:edit")
    @ResponseBody
    @PostMapping("save")
    public ResultData<Object> save(Customer customer){
        try {
            customerService.save(customer);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }


}