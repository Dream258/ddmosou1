package com.xxx.model.base.controller;

import com.xxx.common.bean.Pager;
import com.xxx.common.bean.ResultData;
import com.xxx.common.framework.base.BaseController;
import com.xxx.common.interfaces.Permission;
import com.xxx.model.base.entity.SeoBack;
import com.xxx.model.base.service.SeoBackService;
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
 * 配置测试视图层
 */
@Controller
@RequestMapping("seoBack")
public class SeoBackController extends BaseController {

    @Resource
    private SeoBackService seoBackService;

    @ModelAttribute
    public SeoBack get(@RequestParam(required=false) Long id) {
        if (id != null){
            return seoBackService.selectById(id);
        }else{
            return new SeoBack();
        }
    }

    //分页列表
    @Permission("base:seoBack:view")
    @RequestMapping("page")
    public String page(SeoBack seoBack, Pager<SeoBack> pager, Model model){
        Pager<SeoBack> page = seoBackService.selectPage(seoBack, pager);
        model.addAttribute("pager",page);
        model.addAttribute("seoBack",seoBack);
        return "base/seoBack/seoBackPage";
    }

    //跳转添加编辑页面
    @Permission("base:seoBack:view")
    @GetMapping("saveFrom")
    public String saveFrom(SeoBack seoBack,Model model){
        model.addAttribute("seoBack",seoBack);
        return "base/seoBack/seoBackSave";
    }

    //添加编辑操作
    @Permission("base:seoBack:edit")
    @ResponseBody
    @PostMapping("save")
    public ResultData<Object> save(SeoBack seoBack){
        try {
            seoBackService.save(seoBack);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }


}