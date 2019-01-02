package com.xxx.model.base.controller;

import com.xxx.common.bean.Pager;
import com.xxx.common.bean.ResultData;
import com.xxx.common.framework.base.BaseController;
import com.xxx.common.interfaces.Permission;
import com.xxx.model.base.entity.DdOrders;
import com.xxx.model.base.service.DdOrdersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 订单管理视图层
 */
@Controller
@RequestMapping("ddOrders")
public class DdOrdersController extends BaseController {

    @Resource
    private DdOrdersService ddOrdersService;

    @ModelAttribute
    public DdOrders get(@RequestParam(required = false) Long id) {
        if (id != null) {
            return ddOrdersService.selectById(id);
        } else {
            return new DdOrders();
        }
    }

    //分页列表
    @Permission("base:ddOrders:view")
    @RequestMapping("page")
    public String page(DdOrders ddOrders, Pager<DdOrders> pager, Model model) {
        Pager<DdOrders> page = ddOrdersService.selectPage(ddOrders, pager);
        model.addAttribute("pager", page);
        model.addAttribute("ddOrders", ddOrders);
        return "base/ddOrders/ddOrdersPage";
    }

    //跳转添加编辑页面
    @Permission("base:ddOrders:view")
    @GetMapping("saveFrom")
    public String saveFrom(DdOrders ddOrders, Model model) {
        model.addAttribute("ddOrders", ddOrders);
        return "base/ddOrders/ddOrdersSave";
    }

    //跳转搜索页面
    @Permission("base:ddOrders:view")
    @GetMapping("searchOrder")
    public String searchOrder(DdOrders ddOrders, Model model) {
        model.addAttribute("ddOrders", ddOrders);
        return "base/ddOrders/ddOrdersSearch";
    }

    //添加编辑操作
    @Permission("base:ddOrders:edit")
    @ResponseBody
    @PostMapping("save")
    public ResultData<Object> save(DdOrders ddOrders) {
        System.out.println("日期：=====================================================" + ddOrders.getOrderCurrentDate());

        try {
            ddOrdersService.save(ddOrders);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }

    //删除操作
    @Permission("base:ddOrders:edit")
    @ResponseBody
    @PostMapping("delById")
    public ResultData<Object> delById(Integer id) {
        try {
            ddOrdersService.delById(id);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }

    /*//搜索操作
    @Permission("base:ddOrders:edit")
    @PostMapping("orderSearch")
    public String orderSearch(String user_telephone, HttpSession session) {
        try {
            List<DdOrders> ordersListByPhone = ddOrdersService.orderSearch(user_telephone);
            if (ordersListByPhone != null) {
                session.setAttribute("ordersListByPhone", ordersListByPhone);
                return "base/ddOrders/ddOrdersSearchList";
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }*/
}