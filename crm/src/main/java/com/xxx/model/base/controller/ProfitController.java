package com.xxx.model.base.controller;

import com.xxx.common.bean.Pager;
import com.xxx.common.bean.ResultData;
import com.xxx.common.framework.base.BaseController;
import com.xxx.common.interfaces.Permission;
import com.xxx.model.base.entity.Profit;
import com.xxx.model.base.service.ProfitService;
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
 * 签约收益视图层
 */
@Controller
@RequestMapping("profit")
public class ProfitController extends BaseController {

    @Resource
    private ProfitService profitService;

    @ModelAttribute
    public Profit get(@RequestParam(required=false) Long id) {
        if (id != null){
            return profitService.selectById(id);
        }else{
            return new Profit();
        }
    }

    //分页列表
    @Permission("base:profit:view")
    @RequestMapping("page")
    public String page(Profit profit, Pager<Profit> pager, Model model){
        Pager<Profit> page = profitService.selectPage(profit, pager);
        model.addAttribute("pager",page);
        model.addAttribute("profit",profit);
        return "base/profit/profitPage";
    }

    //跳转添加编辑页面
    @Permission("base:profit:view")
    @GetMapping("saveFrom")
    public String saveFrom(Profit profit,Model model){
        model.addAttribute("profit",profit);
        return "base/profit/profitSave";
    }

    //添加编辑操作
    @Permission("base:profit:edit")
    @ResponseBody
    @PostMapping("save")
    public ResultData<Object> save(Profit profit){
        try {
            profitService.save(profit);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }


}