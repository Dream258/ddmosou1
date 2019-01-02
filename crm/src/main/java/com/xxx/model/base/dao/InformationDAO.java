package com.xxx.model.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xxx.common.framework.base.BaseDAO;
import com.xxx.model.base.entity.Information;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 我的消息持久层
 */
@Component
public interface InformationDAO extends BaseMapper<Information> {

    List<Map<String,Object>> findList(Page<Map<String,Object>> page, @Param("entity") Information i);

    int findNum(@Param("target") int target);

    @Update("UPDATE information SET `read` = 1 WHERE id = #{id}")
    void updateRead(@Param("id") int id);

}
@Component
interface AutoInformationDAO extends BaseDAO<Information>{

}