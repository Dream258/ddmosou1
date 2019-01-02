package com.xxx.model.base.controller;

import com.xxx.common.bean.Pager;
import com.xxx.common.bean.ResultData;
import com.xxx.common.framework.base.BaseController;
import com.xxx.common.interfaces.Permission;
import com.xxx.model.base.entity.DdViptime;
import com.xxx.model.base.service.DdViptimeService;
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
 * 会员日期视图层
 */
@Controller
@RequestMapping("ddViptime")
public class DdViptimeController extends BaseController {

    @Resource
    private DdViptimeService ddViptimeService;

    @ModelAttribute
    public DdViptime get(@RequestParam(required=false) Long id) {
        if (id != null){
            return ddViptimeService.selectById(id);
        }else{
            return new DdViptime();
        }
    }

    //分页列表
    @Permission("base:ddViptime:view")
    @RequestMapping("page")
    public String page(DdViptime ddViptime, Pager<DdViptime> pager, Model model){
        Pager<DdViptime> page = ddViptimeService.selectPage(ddViptime, pager);
        model.addAttribute("pager",page);
        model.addAttribute("ddViptime",ddViptime);
        return "base/ddViptime/ddViptimePage";
    }

    //跳转添加编辑页面
    @Permission("base:ddViptime:view")
    @GetMapping("saveFrom")
    public String saveFrom(DdViptime ddViptime,Model model){
        model.addAttribute("ddViptime",ddViptime);
        return "base/ddViptime/ddViptimeSave";
    }

    //添加编辑操作
    @Permission("base:ddViptime:edit")
    @ResponseBody
    @PostMapping("save")
    public ResultData<Object> save(DdViptime ddViptime){
        try {
            ddViptimeService.save(ddViptime);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }

    //添加编辑操作
    @Permission("base:ddViptime:edit")
    @ResponseBody
    @PostMapping("delById")
    public ResultData<Object> delById(Integer id){
        try {
            ddViptimeService.delById(id);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }


}