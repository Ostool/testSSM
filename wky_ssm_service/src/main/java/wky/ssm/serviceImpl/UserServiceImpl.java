package wky.ssm.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import wky.ssm.dao.UserDao;
import wky.ssm.domain.Role;
import wky.ssm.domain.UserInfo;
import wky.ssm.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    //添加用户
    @Override
    public void addUser(UserInfo userInfo) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userDao.addUser(userInfo);
    }

    //将角色赋予用户
    @Override
    public void addRoleToUser(String userId, String[] roleIds)throws Exception {
        for (String roleId:roleIds){
            userDao.addRoleToUser(userId,roleId);
        }

    }

    //查询出可供用户选择的角色
    @Override
    public List<Role> findOtherRoles(String userId)throws Exception {
        return userDao.findOtherRoles(userId);
    }

    /*根据ID查询用户详情*/
    @Override
    public UserInfo findByUserId(String userId) {
        return userDao.findByUserId(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUsername(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //User user = new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority());
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==0?false:true,
                true,true,true,
                getAuthority(userInfo.getRoles()));
        return user;
    }

    //查询所有用户
    public List<UserInfo> findAllUser()throws Exception{
        return userDao.findAllUser();
    }

    //添加角色
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role:roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }
}
