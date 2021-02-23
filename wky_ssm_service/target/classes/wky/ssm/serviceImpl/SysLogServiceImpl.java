package wky.ssm.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wky.ssm.dao.SysLogDao;
import wky.ssm.domain.SysLog;
import wky.ssm.service.SysLogService;

import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogDao sysLogDao;
    @Override
    public void saveSysLog(SysLog sysLog)throws Exception {
        sysLogDao.saveSysLog(sysLog);
    }

    @Override
    public List<SysLog> findAllSysLog() throws Exception {
        return sysLogDao.findAllSysLog();
    }
}
