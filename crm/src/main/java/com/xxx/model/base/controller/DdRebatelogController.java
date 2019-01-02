package com.xxx.model.base.controller;

import com.xxx.common.bean.Pager;
import com.xxx.common.bean.ResultData;
import com.xxx.common.framework.base.BaseController;
import com.xxx.common.interfaces.Permission;
import com.xxx.model.base.entity.DdRebatelog;
import com.xxx.model.base.service.DdRebatelogService;
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
 * 积分日志视图层
 */
@Controller
@RequestMapping("ddRebatelog")
public class DdRebatelogController extends BaseController {

    @Resource
    private DdRebatelogService ddRebatelogService;

    @ModelAttribute
    public DdRebatelog get(@RequestParam(required=false) Long id) {
        if (id != null){
            return ddRebatelogService.selectById(id);
        }else{
            return new DdRebatelog();
        }
    }

    //分页列表
    @Permission("base:ddRebatelog:view")
    @RequestMapping("page")
    public String page(DdRebatelog ddRebatelog, Pager<DdRebatelog> pager, Model model){
        Pager<DdRebatelog> page = ddRebatelogService.selectPage(ddRebatelog, pager);
        model.addAttribute("pager",page);
        model.addAttribute("ddRebatelog",ddRebatelog);
        return "base/ddRebatelog/ddRebatelogPage";
    }

    //跳转添加编辑页面
    @Permission("base:ddRebatelog:view")
    @GetMapping("saveFrom")
    public String saveFrom(DdRebatelog ddRebatelog,Model model){
        model.addAttribute("ddRebatelog",ddRebatelog);
        return "base/ddRebatelog/ddRebatelogSave";
    }

    //添加编辑操作
    @Permission("base:ddRebatelog:edit")
    @ResponseBody
    @PostMapping("save")
    public ResultData<Object> save(DdRebatelog ddRebatelog){
        try {
            ddRebatelogService.save(ddRebatelog);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }


}