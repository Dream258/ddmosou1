package com.xxx.model.base.controller;

import com.xxx.common.bean.Pager;
import com.xxx.common.bean.ResultData;
import com.xxx.common.framework.base.BaseController;
import com.xxx.common.interfaces.Permission;
import com.xxx.model.base.entity.Information;
import com.xxx.model.base.service.InformationService;
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
 * 我的消息视图层
 */
@Controller
@RequestMapping("information")
public class InformationController extends BaseController {

    @Resource
    private InformationService informationService;

    @ModelAttribute
    public Information get(@RequestParam(required=false) Long id) {
        if (id != null){
            return informationService.selectById(id);
        }else{
            return new Information();
        }
    }

    //分页列表
    @Permission("base:information:view")
    @RequestMapping("page")
    public String page(Information information, Pager<Information> pager, Model model){
        Pager<Information> page = informationService.selectPage(information, pager);
        model.addAttribute("pager",page);
        model.addAttribute("information",information);
        return "base/information/informationPage";
    }

    //跳转添加编辑页面
    @Permission("base:information:view")
    @GetMapping("saveFrom")
    public String saveFrom(Information information,Model model){
        model.addAttribute("information",information);
        return "base/information/informationSave";
    }

    //添加编辑操作
    @Permission("base:information:edit")
    @ResponseBody
    @PostMapping("save")
    public ResultData<Object> save(Information information){
        try {
            informationService.save(information);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }


}