package wky.ssm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import wky.ssm.domain.Permission;

import java.util.List;

public interface PermissionDao {
    @Select("select *from permission where id in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findByRoleId(String roleId);

    @Select("select *from permission")
    List<Permission> findAll()throws Exception;

    @Insert("insert into permission(permissionName,url)values(#{permissionName},#{url})")
    void addPermission(Permission permission)throws Exception;
}
