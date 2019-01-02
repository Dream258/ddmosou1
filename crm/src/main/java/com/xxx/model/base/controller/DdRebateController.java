package com.xxx.model.base.controller;

import com.xxx.common.bean.Pager;
import com.xxx.common.bean.ResultData;
import com.xxx.common.framework.base.BaseController;
import com.xxx.common.interfaces.Permission;
import com.xxx.model.base.entity.DdRebate;
import com.xxx.model.base.service.DdRebateService;
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
 * 积分管理视图层
 */
@Controller
@RequestMapping("ddRebate")
public class DdRebateController extends BaseController {

    @Resource
    private DdRebateService ddRebateService;

    @ModelAttribute
    public DdRebate get(@RequestParam(required=false) Long id) {
        if (id != null){
            return ddRebateService.selectById(id);
        }else{
            return new DdRebate();
        }
    }

    //分页列表
    @Permission("base:ddRebate:view")
    @RequestMapping("page")
    public String page(DdRebate ddRebate, Pager<DdRebate> pager, Model model){
        Pager<DdRebate> page = ddRebateService.selectPage(ddRebate, pager);
        model.addAttribute("pager",page);
        model.addAttribute("ddRebate",ddRebate);
        return "base/ddRebate/ddRebatePage";
    }

    //跳转添加编辑页面
    @Permission("base:ddRebate:view")
    @GetMapping("saveFrom")
    public String saveFrom(DdRebate ddRebate,Model model){
        model.addAttribute("ddRebate",ddRebate);
        return "base/ddRebate/ddRebateSave";
    }

    //添加编辑操作
    @Permission("base:ddRebate:edit")
    @ResponseBody
    @PostMapping("save")
    public ResultData<Object> save(DdRebate ddRebate){
        try {
            ddRebateService.save(ddRebate);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }


}