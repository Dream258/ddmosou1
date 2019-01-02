package com.xxx.model.base.controller;

import com.xxx.common.bean.Pager;
import com.xxx.common.bean.ResultData;
import com.xxx.common.framework.base.BaseController;
import com.xxx.common.interfaces.Permission;
import com.xxx.model.base.entity.DdEmail;
import com.xxx.model.base.service.DdEmailService;
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
 * 邮件通知视图层
 */
@Controller
@RequestMapping("ddEmail")
public class DdEmailController extends BaseController {

    @Resource
    private DdEmailService ddEmailService;

    @ModelAttribute
    public DdEmail get(@RequestParam(required=false) Long id) {
        if (id != null){
            return ddEmailService.selectById(id);
        }else{
            return new DdEmail();
        }
    }

    //分页列表
    @Permission("base:ddEmail:view")
    @RequestMapping("page")
    public String page(DdEmail ddEmail, Pager<DdEmail> pager, Model model){
        Pager<DdEmail> page = ddEmailService.selectPage(ddEmail, pager);
        model.addAttribute("pager",page);
        model.addAttribute("ddEmail",ddEmail);
        return "base/ddEmail/ddEmailPage";
    }

    //跳转添加编辑页面
    @Permission("base:ddEmail:view")
    @GetMapping("saveFrom")
    public String saveFrom(DdEmail ddEmail,Model model){
        model.addAttribute("ddEmail",ddEmail);
        return "base/ddEmail/ddEmailSave";
    }

    //添加编辑操作
    @Permission("base:ddEmail:edit")
    @ResponseBody
    @PostMapping("save")
    public ResultData<Object> save(DdEmail ddEmail){
        try {
            ddEmailService.save(ddEmail);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }

    //删除
    @Permission("base:ddEmail:edit")
    @ResponseBody
    @PostMapping("delById")
    public ResultData<Object> delById(Integer id){
        try {
            ddEmailService.delById(id);
            ResultData<Object> success = success();
            return success;
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }


}