package com.xxx.model.base.controller;

import com.xxx.common.bean.Pager;
import com.xxx.common.bean.ResultData;
import com.xxx.common.framework.base.BaseController;
import com.xxx.common.interfaces.Permission;
import com.xxx.model.base.entity.Product;
import com.xxx.model.base.service.ProductService;
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
 * 产品管理视图层
 */
@Controller
@RequestMapping("product")
public class ProductController extends BaseController {

    @Resource
    private ProductService productService;

    @ModelAttribute
    public Product get(@RequestParam(required=false) Long id) {
        if (id != null){
            return productService.selectById(id);
        }else{
            return new Product();
        }
    }

    //分页列表
    @Permission("base:product:view")
    @RequestMapping("page")
    public String page(Product product, Pager<Product> pager, Model model){
        Pager<Product> page = productService.selectPage(product, pager);
        model.addAttribute("pager",page);
        model.addAttribute("product",product);
        return "base/product/productPage";
    }

    //跳转添加编辑页面
    @Permission("base:product:view")
    @GetMapping("saveFrom")
    public String saveFrom(Product product,Model model){
        model.addAttribute("product",product);
        return "base/product/productSave";
    }

    //添加编辑操作
    @Permission("base:product:edit")
    @ResponseBody
    @PostMapping("save")
    public ResultData<Object> save(Product product){
        try {
            productService.save(product);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }


}