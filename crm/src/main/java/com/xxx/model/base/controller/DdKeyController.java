package com.xxx.model.base.controller;

import com.xxx.common.bean.Pager;
import com.xxx.common.bean.ResultData;
import com.xxx.common.framework.base.BaseController;
import com.xxx.common.interfaces.Permission;
import com.xxx.model.base.entity.DdKey;
import com.xxx.model.base.service.DdKeyService;
import org.apache.commons.lang3.StringUtils;
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
 * 关键词视图层
 */
@Controller
@RequestMapping("ddKey")
public class DdKeyController extends BaseController {

    @Resource
    private DdKeyService ddKeyService;

    @ModelAttribute
    public DdKey get(@RequestParam(required=false) Long id) {
        if (id != null){
            return ddKeyService.selectById(id);
        }else{
            return new DdKey();
        }
    }

    //分页列表
    @Permission("base:ddKey:view")
    @RequestMapping("page")
    public String page(DdKey ddKey, Pager<DdKey> pager, Model model){
        Pager<DdKey> page = ddKeyService.selectPage(ddKey, pager);
        model.addAttribute("pager",page);
        model.addAttribute("ddKey",ddKey);
        return "base/ddKey/ddKeyPage";
    }

    //跳转添加编辑页面
    @Permission("base:ddKey:view")
    @GetMapping("saveFrom")
    public String saveFrom(DdKey ddKey,Model model){
        model.addAttribute("ddKey",ddKey);
        return "base/ddKey/ddKeySave";
    }

    //添加编辑操作
    @Permission("base:ddKey:edit")
    @ResponseBody
    @PostMapping("save")
    public ResultData<Object> save(DdKey ddKey){
        try {
            ddKeyService.save(ddKey);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }

    //删除
    @Permission("base:ddKey:edit")
    @GetMapping("delById")
    public String delById(Long id){
        if(id != null){
            ddKeyService.delById(id);
        }
        return "redirect:/ddKey/page";
    }

    //批量删除
    @Permission("base:ddKey:edit")
    @GetMapping("delByIds")
    public String delByIds(String ids){
        if(StringUtils.isBlank(ids)){
            ddKeyService.deleteByIds(ids.split(","));
        }
        return "redirect:/ddKey/page";
    }


}