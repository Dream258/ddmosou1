package com.xxx.model.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xxx.common.framework.base.BaseDAO;
import com.xxx.model.base.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 产品管理持久层
 */
@Component
public interface ProductDAO extends BaseMapper<Product>{

    /** 产品列表 */
    List<Map<String,Object>> findList(Page<Map<String,Object>> page, @Param("entity") Product product);

    Map<String,Object> getProductById(@Param("id") int id);

    @Select("SELECT id,image FROM product WHERE halt = '0' ORDER BY id DESC")
    List<Map<String,Object>> findBanner();

}
@Component
interface AutoProductDAO extends BaseDAO<Product>{

}