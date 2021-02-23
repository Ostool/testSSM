package wky.ssm.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wky.ssm.dao.RoleDao;
import wky.ssm.domain.Permission;
import wky.ssm.domain.Role;
import wky.ssm.service.RolesService;

import java.util.List;
@Service
public class RolesServiceImpl implements RolesService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIdx)throws Exception {
        for (String permissionId:permissionIdx){
            roleDao.addPermissionToRole(roleId,permissionId);
        }

    }

    /*根据角色查询可赋予的权限内容*/
    @Override
    public List<Permission> findOtherPermissions(String roleId) {
        return roleDao.findOtherPermissions(roleId);
    }

    /*根据ID查角色*/
    @Override
    public Role findByRoleId(String roleId)throws Exception {
        return roleDao.findByRoleId(roleId);
    }

    /*查询所有角色*/
    @Override
    public List<Role> findAll() throws Exception{
        return roleDao.findAll();
    }

    /*添加角色*/

    @Override
    public void addRole(Role role)throws Exception {
        roleDao.addRole(role);
    }
}
