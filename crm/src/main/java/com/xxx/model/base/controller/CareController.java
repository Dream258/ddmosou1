package com.xxx.model.base.controller;

import com.xxx.common.bean.Pager;
import com.xxx.common.bean.ResultData;
import com.xxx.common.framework.base.BaseController;
import com.xxx.common.interfaces.Permission;
import com.xxx.model.base.entity.Care;
import com.xxx.model.base.service.CareService;
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
 * 客户关怀视图层
 */
@Controller
@RequestMapping("care")
public class CareController extends BaseController {

    @Resource
    private CareService careService;

    @ModelAttribute
    public Care get(@RequestParam(required=false) Long id) {
        if (id != null){
            return careService.selectById(id);
        }else{
            return new Care();
        }
    }

    //分页列表
    @Permission("base:care:view")
    @RequestMapping("page")
    public String page(Care care, Pager<Care> pager, Model model){
        Pager<Care> page = careService.selectPage(care, pager);
        model.addAttribute("pager",page);
        model.addAttribute("care",care);
        return "base/care/carePage";
    }

    //跳转添加编辑页面
    @Permission("base:care:view")
    @GetMapping("saveFrom")
    public String saveFrom(Care care,Model model){
        model.addAttribute("care",care);
        return "base/care/careSave";
    }

    //添加编辑操作
    @Permission("base:care:edit")
    @ResponseBody
    @PostMapping("save")
    public ResultData<Object> save(Care care){
        try {
            careService.save(care);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }


}