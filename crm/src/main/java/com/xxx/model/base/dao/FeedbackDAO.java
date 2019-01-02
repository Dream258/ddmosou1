package com.xxx.model.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xxx.common.framework.base.BaseDAO;
import com.xxx.model.base.entity.Feedback;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 客户反馈持久层
 */
@Component
public interface FeedbackDAO extends BaseMapper<Feedback> {

    List<Map<String,Object>> findList(Page<Map<String,Object>> page, @Param("entity") Feedback f);

    @Select("SELECT f.*,c.name AS customerName,d.name AS departName,c.phone AS phone FROM feedback f " +
            "JOIN customer c ON f.customer = c.id JOIN sys_department d ON f.department = d.id WHERE 1=1 AND f.id = #{id}")
    Map<String,Object> getFeedbackById(@Param("id") int id);

}
@Component
interface AutoFeedbackDAO extends BaseDAO<Feedback>{

}