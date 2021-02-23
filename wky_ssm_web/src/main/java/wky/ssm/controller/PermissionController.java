package wky.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import wky.ssm.domain.Permission;
import wky.ssm.service.PermissionService;

import java.security.Permissions;
import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @RequestMapping("/findAll")
    public ModelAndView findAllPermission()throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll();
        mv.setViewName("permission-list");
        mv.addObject("permissions",permissionList);
        return mv;
    }

    @RequestMapping("/addPermission")
    public String addPermission(Permission permission)throws Exception{
        permissionService.addPermission(permission);
        return "redirect:findAll";
    }
}
