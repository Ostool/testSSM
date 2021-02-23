package wky.ssm.service;

import wky.ssm.domain.SysLog;

import java.util.List;

public interface SysLogService {
    void saveSysLog(SysLog sysLog)throws Exception;
    List<SysLog> findAllSysLog()throws Exception;
}
