package com.xxx.model.base.controller;

import com.xxx.common.bean.Pager;
import com.xxx.common.bean.ResultData;
import com.xxx.common.framework.base.BaseController;
import com.xxx.common.interfaces.Permission;
import com.xxx.model.base.entity.Salesplan;
import com.xxx.model.base.service.SalesplanService;
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
 * 销售计划视图层
 */
@Controller
@RequestMapping("salesplan")
public class SalesplanController extends BaseController {

    @Resource
    private SalesplanService salesplanService;

    @ModelAttribute
    public Salesplan get(@RequestParam(required=false) Long id) {
        if (id != null){
            return salesplanService.selectById(id);
        }else{
            return new Salesplan();
        }
    }

    //分页列表
    @Permission("base:salesplan:view")
    @RequestMapping("page")
    public String page(Salesplan salesplan, Pager<Salesplan> pager, Model model){
        Pager<Salesplan> page = salesplanService.selectPage(salesplan, pager);
        model.addAttribute("pager",page);
        model.addAttribute("salesplan",salesplan);
        return "base/salesplan/salesplanPage";
    }

    //跳转添加编辑页面
    @Permission("base:salesplan:view")
    @GetMapping("saveFrom")
    public String saveFrom(Salesplan salesplan,Model model){
        model.addAttribute("salesplan",salesplan);
        return "base/salesplan/salesplanSave";
    }

    //添加编辑操作
    @Permission("base:salesplan:edit")
    @ResponseBody
    @PostMapping("save")
    public ResultData<Object> save(Salesplan salesplan){
        try {
            salesplanService.save(salesplan);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }


}