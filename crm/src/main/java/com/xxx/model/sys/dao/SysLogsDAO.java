package com.xxx.model.sys.dao;

import com.xxx.common.framework.base.BaseDAO;
import com.xxx.model.sys.entity.SysLogs;
import org.springframework.stereotype.Component;

/**
 * 日志持久层
 */
@Component
public interface SysLogsDAO {

}
@Component
interface AutoSysLogsDAO extends BaseDAO<SysLogs>{

}