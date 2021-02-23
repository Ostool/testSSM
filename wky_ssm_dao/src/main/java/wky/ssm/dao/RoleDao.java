package wky.ssm.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import wky.ssm.domain.Permission;
import wky.ssm.domain.Role;

import java.util.List;

@Repository
public interface RoleDao {
    @Select("select *from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,
            many = @Many(select = "wky.ssm.dao.PermissionDao.findByRoleId"))
    })
    public List<Role> findByUserId(String userId)throws Exception;

    @Select("select *from role")
    List<Role> findAll()throws Exception;

    @Insert("insert into role (roleName,roleDesc)values(#{roleName},#{roleDesc})")
    void addRole(Role role);

    @Select("select *from role where id=#{roleId}")
    Role findByRoleId(String roleId)throws Exception;

    @Select("select *from permission where id not in (select permissionId from " +
            "role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermissions(String roleId);

    @Insert("insert into role_permission (permissionId,roleId)values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId)throws Exception;
}
