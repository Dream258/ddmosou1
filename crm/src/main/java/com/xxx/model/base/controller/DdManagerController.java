package com.xxx.model.base.controller;

import com.xxx.common.bean.Pager;
import com.xxx.common.bean.ResultData;
import com.xxx.common.framework.base.BaseController;
import com.xxx.common.interfaces.Permission;
import com.xxx.model.base.entity.DdManager;
import com.xxx.model.base.service.DdManagerService;
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
 * 模块管理视图层
 */
@Controller
@RequestMapping("ddManager")
public class DdManagerController extends BaseController {

    @Resource
    private DdManagerService ddManagerService;

    @ModelAttribute
    public DdManager get(@RequestParam(required=false) Long id) {
        if (id != null){
            return ddManagerService.selectById(id);
        }else{
            return new DdManager();
        }
    }

    //分页列表
    @Permission("base:ddManager:view")
    @RequestMapping("page")
    public String page(DdManager ddManager, Pager<DdManager> pager, Model model){
        Pager<DdManager> page = ddManagerService.selectPage(ddManager, pager);
        model.addAttribute("pager",page);
        model.addAttribute("ddManager",ddManager);
        return "base/ddManager/ddManagerPage";
    }

    //跳转添加编辑页面
    @Permission("base:ddManager:view")
    @GetMapping("saveFrom")
    public String saveFrom(DdManager ddManager,Model model){
        model.addAttribute("ddManager",ddManager);
        return "base/ddManager/ddManagerSave";
    }

    //添加编辑操作
    @Permission("base:ddManager:edit")
    @ResponseBody
    @PostMapping("save")
    public ResultData<Object> save(DdManager ddManager){
        try {
            ddManagerService.save(ddManager);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }


}