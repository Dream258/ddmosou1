package com.xxx.model.base.controller;

import com.xxx.common.bean.Pager;
import com.xxx.common.bean.ResultData;
import com.xxx.common.framework.base.BaseController;
import com.xxx.common.interfaces.Permission;
import com.xxx.model.base.entity.Review;
import com.xxx.model.base.service.ReviewService;
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
 * 客户回访视图层
 */
@Controller
@RequestMapping("review")
public class ReviewController extends BaseController {

    @Resource
    private ReviewService reviewService;

    @ModelAttribute
    public Review get(@RequestParam(required=false) Long id) {
        if (id != null){
            return reviewService.selectById(id);
        }else{
            return new Review();
        }
    }

    //分页列表
    @Permission("base:review:view")
    @RequestMapping("page")
    public String page(Review review, Pager<Review> pager, Model model){
        Pager<Review> page = reviewService.selectPage(review, pager);
        model.addAttribute("pager",page);
        model.addAttribute("review",review);
        return "base/review/reviewPage";
    }

    //跳转添加编辑页面
    @Permission("base:review:view")
    @GetMapping("saveFrom")
    public String saveFrom(Review review,Model model){
        model.addAttribute("review",review);
        return "base/review/reviewSave";
    }

    //添加编辑操作
    @Permission("base:review:edit")
    @ResponseBody
    @PostMapping("save")
    public ResultData<Object> save(Review review){
        try {
            reviewService.save(review);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }


}