package com.xxx.model.base.controller;

import com.xxx.common.bean.Pager;
import com.xxx.common.bean.ResultData;
import com.xxx.common.framework.base.BaseController;
import com.xxx.common.interfaces.Permission;
import com.xxx.model.base.entity.Feedback;
import com.xxx.model.base.service.FeedbackService;
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
 * 客户反馈视图层
 */
@Controller
@RequestMapping("feedback")
public class FeedbackController extends BaseController {

    @Resource
    private FeedbackService feedbackService;

    @ModelAttribute
    public Feedback get(@RequestParam(required=false) Long id) {
        if (id != null){
            return feedbackService.selectById(id);
        }else{
            return new Feedback();
        }
    }

    //分页列表
    @Permission("base:feedback:view")
    @RequestMapping("page")
    public String page(Feedback feedback, Pager<Feedback> pager, Model model){
        Pager<Feedback> page = feedbackService.selectPage(feedback, pager);
        model.addAttribute("pager",page);
        model.addAttribute("feedback",feedback);
        return "base/feedback/feedbackPage";
    }

    //跳转添加编辑页面
    @Permission("base:feedback:view")
    @GetMapping("saveFrom")
    public String saveFrom(Feedback feedback,Model model){
        model.addAttribute("feedback",feedback);
        return "base/feedback/feedbackSave";
    }

    //添加编辑操作
    @Permission("base:feedback:edit")
    @ResponseBody
    @PostMapping("save")
    public ResultData<Object> save(Feedback feedback){
        try {
            feedbackService.save(feedback);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }


}