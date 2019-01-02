package com.xxx.model.base.controller;

import com.xxx.common.bean.Pager;
import com.xxx.common.bean.ResultData;
import com.xxx.common.framework.base.BaseController;
import com.xxx.common.interfaces.Permission;
import com.xxx.model.base.entity.DdMember;
import com.xxx.model.base.service.DdMemberService;
import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 会员管理视图层
 */
@Controller
@RequestMapping("ddMember")
public class DdMemberController extends BaseController {

    @Resource
    private DdMemberService ddMemberService;

    @ModelAttribute
    public DdMember get(@RequestParam(required = false) Long id) {
        if (id != null) {
            return ddMemberService.selectById(id);
        } else {
            return new DdMember();
        }
    }

    //分页列表
    @Permission("base:ddMember:view")
    @RequestMapping("page")
    public String page(DdMember ddMember, Pager<DdMember> pager, Model model) {
        Pager<DdMember> page = ddMemberService.selectPage(ddMember, pager);
        model.addAttribute("pager", page);
        model.addAttribute("ddMember", ddMember);
        return "base/ddMember/ddMemberPage";
    }

    //跳转添加编辑页面
    @Permission("base:ddMember:view")
    @GetMapping("saveFrom")
    public String saveFrom(DdMember ddMember, Model model) {
        model.addAttribute("ddMember", ddMember);
        return "base/ddMember/ddMemberSave";
    }

    //添加编辑操作
    @Permission("base:ddMember:edit")
    @ResponseBody
    @PostMapping("save")
    public ResultData<Object> save(DdMember ddMember) {
        try {
            ddMemberService.save(ddMember);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }

    //删除操作
    @Permission("base:ddMember:edit")
    @ResponseBody
    @PostMapping("delById")
    public ResultData<Object> delById(Integer id) {
        try {
            ddMemberService.delById(id);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }

}