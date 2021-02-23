package wky.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import wky.ssm.domain.Permission;
import wky.ssm.domain.Role;
import wky.ssm.service.RolesService;

import java.util.List;


@Controller
@RequestMapping("/role")
public class RolesController {
    @Autowired
    private RolesService rolesService;

    /*将权限添加到角色中*/
    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true)String roleId,
                                      @RequestParam(name = "ids",required = true)String[] permissionIdx)throws Exception{
        rolesService.addPermissionToRole(roleId,permissionIdx);
        return "redirect:findAll";
    }

    /*查询可供角色选择的权限*/
    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermissionid(@RequestParam(name = "id")String roleId)throws Exception{
        ModelAndView mv = new ModelAndView();
        Role role = rolesService.findByRoleId(roleId);
        List<Permission> permissions = rolesService.findOtherPermissions(roleId);
        mv.addObject("role",role);
        mv.addObject("permissionList",permissions);
        mv.setViewName("role-permission-add");
        return mv;
    }

    /*查询所有的角色*/
    @RequestMapping("/findAll")
    public ModelAndView findAll()throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Role> roles =  rolesService.findAll();
        mv.addObject("roleList",roles);
        mv.setViewName("role-list");
        return mv;
    }

    /*添加角色*/
    @RequestMapping("/addRole")
    public String addRole(Role role)throws Exception{
        rolesService.addRole(role);
        return "redirect:findAll";
    }

}
