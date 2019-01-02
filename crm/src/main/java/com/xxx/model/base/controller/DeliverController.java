package com.xxx.model.base.controller;

import com.xxx.common.bean.Pager;
import com.xxx.common.bean.ResultData;
import com.xxx.common.framework.base.BaseController;
import com.xxx.common.interfaces.Permission;
import com.xxx.model.base.entity.Deliver;
import com.xxx.model.base.service.DeliverService;
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
 * 客户转交视图层
 */
@Controller
@RequestMapping("deliver")
public class DeliverController extends BaseController {

    @Resource
    private DeliverService deliverService;

    @ModelAttribute
    public Deliver get(@RequestParam(required=false) Long id) {
        if (id != null){
            return deliverService.selectById(id);
        }else{
            return new Deliver();
        }
    }

    //分页列表
    @Permission("base:deliver:view")
    @RequestMapping("page")
    public String page(Deliver deliver, Pager<Deliver> pager, Model model){
        Pager<Deliver> page = deliverService.selectPage(deliver, pager);
        model.addAttribute("pager",page);
        model.addAttribute("deliver",deliver);
        return "base/deliver/deliverPage";
    }

    //跳转添加编辑页面
    @Permission("base:deliver:view")
    @GetMapping("saveFrom")
    public String saveFrom(Deliver deliver,Model model){
        model.addAttribute("deliver",deliver);
        return "base/deliver/deliverSave";
    }

    //添加编辑操作
    @Permission("base:deliver:edit")
    @ResponseBody
    @PostMapping("save")
    public ResultData<Object> save(Deliver deliver){
        try {
            deliverService.save(deliver);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }


}