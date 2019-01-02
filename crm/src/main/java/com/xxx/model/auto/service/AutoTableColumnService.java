package com.xxx.model.auto.service;

import com.xxx.common.framework.base.BaseService;
import com.xxx.model.auto.dao.AutoTableColumnDAO;
import com.xxx.model.auto.entity.AutoTableColumn;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 属性列表业务逻辑类
 */
@Service
public class AutoTableColumnService extends BaseService<AutoTableColumn> {

	@Resource
	private AutoTableColumnDAO autoTableColumnDAO;

    /** 保存 */
    public void save(AutoTableColumn autoTableColumn) {
        if(autoTableColumn.getId() == null){
            this.insert(autoTableColumn);
        }else{
            this.updateParams(autoTableColumn);
        }
    }
}