package com.xxx.model.base.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.xxx.common.framework.base.BaseService;
import com.xxx.model.base.dao.ProductDAO;
import com.xxx.model.base.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 产品管理业务逻辑类
 */
@Service
public class ProductService extends BaseService<Product> {

    @Resource
    private ProductDAO productDAO;

    /** 保存 */
    @Transactional
    public void save(Product product) {
        if(product.getId() == null){
            this.insert(product);
        }else{
            this.updateParams(product);
        }
    }

    public void add(Product product){
        product.setHalt("0");
        product.setCreatetime(new Date());
        productDAO.insert(product);
    }

    public void halt(Product product){
        product.setHalt("1");
        productDAO.updateById(product);
    }

    public Page<Map<String,Object>> findList(Page<Map<String,Object>> page,Product product){
        return page.setRecords(productDAO.findList(page, product));
    }

    public List<Map<String,Object>> findBanner(){
        return productDAO.findBanner();
    }

    public Map<String,Object> getProductById(int id){
        return productDAO.getProductById(id);
    }
}