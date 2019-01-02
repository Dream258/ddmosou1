package com.xxx.model.base.controller;

import com.xxx.common.bean.Pager;
import com.xxx.common.bean.ResultData;
import com.xxx.common.framework.base.BaseController;
import com.xxx.common.interfaces.Permission;
import com.xxx.model.base.entity.Salesallot;
import com.xxx.model.base.service.SalesallotService;
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
 * 销售分配视图层
 */
@Controller
@RequestMapping("salesallot")
public class SalesallotController extends BaseController {

    @Resource
    private SalesallotService salesallotService;

    @ModelAttribute
    public Salesallot get(@RequestParam(required=false) Long id) {
        if (id != null){
            return salesallotService.selectById(id);
        }else{
            return new Salesallot();
        }
    }

    //分页列表
    @Permission("base:salesallot:view")
    @RequestMapping("page")
    public String page(Salesallot salesallot, Pager<Salesallot> pager, Model model){
        Pager<Salesallot> page = salesallotService.selectPage(salesallot, pager);
        model.addAttribute("pager",page);
        model.addAttribute("salesallot",salesallot);
        return "base/salesallot/salesallotPage";
    }

    //跳转添加编辑页面
    @Permission("base:salesallot:view")
    @GetMapping("saveFrom")
    public String saveFrom(Salesallot salesallot,Model model){
        model.addAttribute("salesallot",salesallot);
        return "base/salesallot/salesallotSave";
    }

    //添加编辑操作
    @Permission("base:salesallot:edit")
    @ResponseBody
    @PostMapping("save")
    public ResultData<Object> save(Salesallot salesallot){
        try {
            salesallotService.save(salesallot);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }


}