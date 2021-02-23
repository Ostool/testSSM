package wky.ssm.service;

import wky.ssm.domain.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll()throws Exception;

    void addPermission(Permission permission)throws Exception;
}
