package com.xxx.model.sys.controller;

import com.xxx.common.bean.Pager;
import com.xxx.common.bean.ResultData;
import com.xxx.common.framework.base.BaseController;
import com.xxx.common.interfaces.Permission;
import com.xxx.model.sys.entity.SysLogs;
import com.xxx.model.sys.service.SysLogsService;
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
 * 日志视图层
 */
@Controller
@RequestMapping("sysLogs")
public class SysLogsController extends BaseController {

    @Resource
    private SysLogsService sysLogsService;

    @ModelAttribute
    public SysLogs get(@RequestParam(required=false) Long id) {
        if (id != null){
            return sysLogsService.selectById(id);
        }else{
            return new SysLogs();
        }
    }

    //分页列表
    @Permission("sys:sysLogs:view")
    @RequestMapping("page")
    public String page(SysLogs sysLogs, Pager<SysLogs> pager, Model model){
        Pager<SysLogs> page = sysLogsService.selectPage(sysLogs, pager);
        model.addAttribute("pager",page);
        model.addAttribute("sysLogs",sysLogs);
        return "sys/sysLogs/sysLogsPage";
    }

    //跳转添加编辑页面
    @Permission("sys:sysLogs:view")
    @GetMapping("saveFrom")
    public String saveFrom(SysLogs sysLogs,Model model){
        model.addAttribute("sysLogs",sysLogs);
        return "sys/sysLogs/sysLogsSave";
    }

    //添加编辑操作
    @Permission("sys:sysLogs:edit")
    @ResponseBody
    @PostMapping("save")
    public ResultData<Object> save(SysLogs sysLogs){
        try {
            sysLogsService.save(sysLogs);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }

    //删除
    @Permission("sys:sysLogs:edit")
    @GetMapping("delById")
    public String delById(Long id){
        if(id != null){
            sysLogsService.delById(id);
        }
        return "redirect:/sysLogs/page";
    }

    //批量删除
    @Permission("sys:sysLogs:edit")
    @GetMapping("delByIds")
    public String delByIds(String ids){
        if(StringUtils.isBlank(ids)){
            sysLogsService.deleteByIds(ids.split(","));
        }
        return "redirect:/sysLogs/page";
    }


}