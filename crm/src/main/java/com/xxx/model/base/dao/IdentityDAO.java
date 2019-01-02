package com.xxx.model.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xxx.common.framework.base.BaseDAO;
import com.xxx.model.base.entity.Identity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 身份信息持久层
 */
@Component
public interface IdentityDAO extends BaseMapper<Identity>{

    @Select("SELECT id,name,phone,img_a,img_b FROM identity WHERE user = #{id}")
    Map<String,Object> findIdentityById(@Param("id") int id);

    @Select("SELECT id FROM identity WHERE user = #{id}")
    int findIdByUserId(@Param("id") int id);

}
@Component
interface AutoIdentityDAO extends BaseDAO<Identity>{

}