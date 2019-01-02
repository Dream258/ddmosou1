package com.xxx.model.base.controller;

import com.xxx.common.bean.Pager;
import com.xxx.common.bean.ResultData;
import com.xxx.common.framework.base.BaseController;
import com.xxx.common.interfaces.Permission;
import com.xxx.model.base.entity.Identity;
import com.xxx.model.base.service.IdentityService;
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
 * 身份信息视图层
 */
@Controller
@RequestMapping("identity")
public class IdentityController extends BaseController {

    @Resource
    private IdentityService identityService;

    @ModelAttribute
    public Identity get(@RequestParam(required=false) Long id) {
        if (id != null){
            return identityService.selectById(id);
        }else{
            return new Identity();
        }
    }

    //分页列表
    @Permission("base:identity:view")
    @RequestMapping("page")
    public String page(Identity identity, Pager<Identity> pager, Model model){
        Pager<Identity> page = identityService.selectPage(identity, pager);
        model.addAttribute("pager",page);
        model.addAttribute("identity",identity);
        return "base/identity/identityPage";
    }

    //跳转添加编辑页面
    @Permission("base:identity:view")
    @GetMapping("saveFrom")
    public String saveFrom(Identity identity,Model model){
        model.addAttribute("identity",identity);
        return "base/identity/identitySave";
    }

    //添加编辑操作
    @Permission("base:identity:edit")
    @ResponseBody
    @PostMapping("save")
    public ResultData<Object> save(Identity identity){
        try {
            identityService.save(identity);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }


}