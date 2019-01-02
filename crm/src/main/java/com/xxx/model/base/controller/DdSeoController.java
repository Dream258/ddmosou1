package com.xxx.model.base.controller;

import com.xxx.common.bean.Pager;
import com.xxx.common.bean.ResultData;
import com.xxx.common.framework.base.BaseController;
import com.xxx.common.interfaces.Permission;
import com.xxx.model.base.entity.DdSeo;
import com.xxx.model.base.service.DdSeoService;
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
 * seo管理视图层
 */
@Controller
@RequestMapping("ddSeo")
public class DdSeoController extends BaseController {

    @Resource
    private DdSeoService ddSeoService;

    @ModelAttribute
    public DdSeo get(@RequestParam(required=false) Long id) {
        if (id != null){
            return ddSeoService.selectById(id);
        }else{
            return new DdSeo();
        }
    }

    //分页列表
    @Permission("base:ddSeo:view")
    @RequestMapping("page")
    public String page(DdSeo ddSeo, Pager<DdSeo> pager, Model model){
        Pager<DdSeo> page = ddSeoService.selectPage(ddSeo, pager);
        model.addAttribute("pager",page);
        model.addAttribute("ddSeo",ddSeo);
        return "base/ddSeo/ddSeoPage";
    }

    //跳转添加编辑页面
    @Permission("base:ddSeo:view")
    @GetMapping("saveFrom")
    public String saveFrom(DdSeo ddSeo,Model model){
        model.addAttribute("ddSeo",ddSeo);
        return "base/ddSeo/ddSeoSave";
    }

    //添加编辑操作
    @Permission("base:ddSeo:edit")
    @ResponseBody
    @PostMapping("save")
    public ResultData<Object> save(DdSeo ddSeo){
        try {
            ddSeoService.save(ddSeo);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }

    //删除
    @Permission("base:ddSeo:edit")
    @ResponseBody
    @PostMapping("delById")
    public ResultData<Object> delById(Integer id){
        try {
            ddSeoService.delById(id);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }


}