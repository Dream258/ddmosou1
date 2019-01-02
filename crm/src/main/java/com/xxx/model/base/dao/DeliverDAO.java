package com.xxx.model.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xxx.common.framework.base.BaseDAO;
import com.xxx.model.base.entity.Deliver;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 客户转交持久层
 */
@Component
public interface DeliverDAO extends BaseMapper<Deliver> {

    /** 分配/转交列表 */
    List<Deliver> findList(Page<Deliver> page, @Param("entity") Deliver d);

}
@Component
interface AutoDeliverDAO extends BaseDAO<Deliver>{

}