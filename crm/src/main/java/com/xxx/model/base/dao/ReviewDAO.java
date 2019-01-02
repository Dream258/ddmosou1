package com.xxx.model.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xxx.common.framework.base.BaseDAO;
import com.xxx.model.base.entity.Review;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 客户回访持久层
 */
@Component
public interface ReviewDAO extends BaseMapper<Review> {

    /** 回访列表 */
    List<Map<String,Object>> findList(Page<Map<String,Object>> page, @Param("entity") Review r);

    Map<String,Object> getReviewById(@Param("id") int id);

}
@Component
interface AutoReviewDAO extends BaseDAO<Review>{

}