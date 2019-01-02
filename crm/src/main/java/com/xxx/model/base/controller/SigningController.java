package com.xxx.model.base.controller;

import com.xxx.common.bean.Pager;
import com.xxx.common.bean.ResultData;
import com.xxx.common.framework.base.BaseController;
import com.xxx.common.interfaces.Permission;
import com.xxx.model.base.entity.Signing;
import com.xxx.model.base.service.SigningService;
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
 * 客户签约视图层
 */
@Controller
@RequestMapping("signing")
public class SigningController extends BaseController {

    @Resource
    private SigningService signingService;

    @ModelAttribute
    public Signing get(@RequestParam(required=false) Long id) {
        if (id != null){
            return signingService.selectById(id);
        }else{
            return new Signing();
        }
    }

    //分页列表
    @Permission("base:signing:view")
    @RequestMapping("page")
    public String page(Signing signing, Pager<Signing> pager, Model model){
        Pager<Signing> page = signingService.selectPage(signing, pager);
        model.addAttribute("pager",page);
        model.addAttribute("signing",signing);
        return "base/signing/signingPage";
    }

    //跳转添加编辑页面
    @Permission("base:signing:view")
    @GetMapping("saveFrom")
    public String saveFrom(Signing signing,Model model){
        model.addAttribute("signing",signing);
        return "base/signing/signingSave";
    }

    //添加编辑操作
    @Permission("base:signing:edit")
    @ResponseBody
    @PostMapping("save")
    public ResultData<Object> save(Signing signing){
        try {
            signingService.save(signing);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }


}