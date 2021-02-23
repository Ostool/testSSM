package wky.ssm.serviceImpl;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wky.ssm.dao.PermissionDao;
import wky.ssm.domain.Permission;

import java.util.List;

@Service
public class PermissionService implements wky.ssm.service.PermissionService {
    @Autowired
    private PermissionDao permissionDao;
    @Override
    public List<Permission> findAll()throws Exception {
        List<Permission> permissions = permissionDao.findAll();
        return permissions;
    }

    @Override
    public void addPermission(Permission permission) throws Exception{
        permissionDao.addPermission(permission);
    }
}
