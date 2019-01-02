package com.xxx.model.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xxx.common.framework.base.BaseDAO;
import com.xxx.model.base.entity.DdEmail;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * 邮件通知持久层
 */
@Component
public interface DdEmailDAO extends BaseMapper<DdEmail> {

}
@Component
interface AutoDdEmailDAO extends BaseDAO<DdEmail>{

}