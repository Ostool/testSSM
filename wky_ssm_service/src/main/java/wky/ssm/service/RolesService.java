package wky.ssm.service;

import wky.ssm.domain.Permission;
import wky.ssm.domain.Role;

import java.util.List;

public interface RolesService {
    List<Role> findAll()throws Exception;

    void addRole(Role role)throws Exception;

    Role findByRoleId(String roleId)throws Exception;

    List<Permission> findOtherPermissions(String roleId);

    void addPermissionToRole(String roleId, String[] permissionIdx)throws Exception;
}
