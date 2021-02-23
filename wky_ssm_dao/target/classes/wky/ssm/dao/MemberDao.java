package wky.ssm.dao;

import org.apache.ibatis.annotations.Select;
import wky.ssm.domain.Member;

public interface MemberDao {
    @Select("select *from `member` where id=#{id}")
    Member findByOrdersId(String id)throws Exception;
}
