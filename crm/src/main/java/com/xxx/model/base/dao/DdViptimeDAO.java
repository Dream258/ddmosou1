package com.xxx.model.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xxx.common.framework.base.BaseDAO;
import com.xxx.model.base.entity.DdViptime;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * 会员日期持久层
 */
@Component
public interface DdViptimeDAO extends BaseMapper<DdViptime> {
    //查询到期天数
    public Integer selectDays(@Param("telephone") String telephone);
}
@Component
interface AutoDdViptimeDAO extends BaseDAO<DdViptime>{

}