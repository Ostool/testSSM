package wky.ssm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import wky.ssm.domain.SysLog;

import java.util.List;

@Repository
public interface SysLogDao {

    @Insert("insert into sysLog (visitTime,username,ip,url,executionTime,method)values(" +
            "#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void saveSysLog(SysLog sysLog)throws Exception;

    @Select("select *from sysLog order by visitTime desc")
    List<SysLog> findAllSysLog()throws Exception;
}
