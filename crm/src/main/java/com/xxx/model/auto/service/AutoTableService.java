package com.xxx.model.auto.service;

import com.xxx.common.framework.base.BaseService;
import com.xxx.model.auto.dao.AutoTableDAO;
import com.xxx.model.auto.entity.AutoTable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 数据库表业务逻辑类
 */
@Service
public class AutoTableService extends BaseService<AutoTable> {

	@Resource
	private AutoTableDAO autoTableDAO;

    /** 保存 */
    public void save(AutoTable autoTable) {
        if(autoTable.getId() == null){
            this.insert(autoTable);
        }else{
            this.updateParams(autoTable);
        }
    }
}