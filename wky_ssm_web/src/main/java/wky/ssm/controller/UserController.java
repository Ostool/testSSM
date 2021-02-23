package wky.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import wky.ssm.domain.Role;
import wky.ssm.domain.UserInfo;
import wky.ssm.service.UserService;


import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    /*添加用户*/
    @RequestMapping("/addUser")
    public String addUser(UserInfo userInfo)throws Exception{
        userService.addUser(userInfo);
        return "redirect:findAll";
    }
    /*查询用户详情*/
    @RequestMapping("/findByUserId")
    public ModelAndView findByUserId(String id){
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findByUserId(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show1");
        return mv;
    }

    /*查询所有用户*/
    @RequestMapping("/findAll")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll()throws Exception{
        ModelAndView mv = new ModelAndView();
        List<UserInfo> users = userService.findAllUser();
        mv.addObject("userList",users);
        mv.setViewName("user-list");
        return mv;
    }

    /*查询可供用户选择的角色*/
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id" ,required = true)String userId)throws Exception{
        ModelAndView mv = new ModelAndView();
        //根据用户id查询出用户
        UserInfo userInfo = userService.findByUserId(userId);
        //查询用户可选择的角色
        List<Role> roles = userService.findOtherRoles(userId);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",roles);
        mv.setViewName("user-role-add");
        return mv;
    }

    /*将选中的角色赋予用户*/
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId",required = true)String userId,
                                @RequestParam(name = "ids",required = true)String[] roleIds)throws Exception{
        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll";
    }

}
