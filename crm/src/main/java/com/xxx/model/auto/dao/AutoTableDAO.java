package com.xxx.model.auto.dao;

import com.xxx.common.framework.base.BaseDAO;
import com.xxx.model.auto.entity.AutoTable;
import org.springframework.stereotype.Component;

/**
 * 数据库表持久层接口
 */
@Component
public interface AutoTableDAO {
	
}
@Component
interface AutoAutoTableDAO extends BaseDAO<AutoTable>{

}
