package com.xxx.model.sys.service;

import com.xxx.common.framework.base.BaseService;
import com.xxx.model.sys.dao.SysLogsDAO;
import com.xxx.model.sys.entity.SysLogs;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 日志业务逻辑类
 */
@Service
public class SysLogsService extends BaseService<SysLogs> {

    @Resource
    private SysLogsDAO sysLogsDAO;

    /** 保存 */
    @Transactional
    public void save(SysLogs sysLogs) {
        if(sysLogs.getId() == null){
            this.insert(sysLogs);
        }else{
            this.updateParams(sysLogs);
        }
    }
}