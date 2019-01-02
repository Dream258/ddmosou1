package com.xxx.model.base.controller;

import com.xxx.common.bean.Pager;
import com.xxx.common.bean.ResultData;
import com.xxx.common.framework.base.BaseController;
import com.xxx.common.interfaces.Permission;
import com.xxx.model.base.entity.Salestarget;
import com.xxx.model.base.service.SalestargetService;
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
 * 销售目标视图层
 */
@Controller
@RequestMapping("salestarget")
public class SalestargetController extends BaseController {

    @Resource
    private SalestargetService salestargetService;

    @ModelAttribute
    public Salestarget get(@RequestParam(required=false) Long id) {
        if (id != null){
            return salestargetService.selectById(id);
        }else{
            return new Salestarget();
        }
    }

    //分页列表
    @Permission("base:salestarget:view")
    @RequestMapping("page")
    public String page(Salestarget salestarget, Pager<Salestarget> pager, Model model){
        Pager<Salestarget> page = salestargetService.selectPage(salestarget, pager);
        model.addAttribute("pager",page);
        model.addAttribute("salestarget",salestarget);
        return "base/salestarget/salestargetPage";
    }

    //跳转添加编辑页面
    @Permission("base:salestarget:view")
    @GetMapping("saveFrom")
    public String saveFrom(Salestarget salestarget,Model model){
        model.addAttribute("salestarget",salestarget);
        return "base/salestarget/salestargetSave";
    }

    //添加编辑操作
    @Permission("base:salestarget:edit")
    @ResponseBody
    @PostMapping("save")
    public ResultData<Object> save(Salestarget salestarget){
        try {
            salestargetService.save(salestarget);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }


}