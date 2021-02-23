package wky.ssm.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import wky.ssm.domain.Role;
import wky.ssm.domain.UserInfo;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserInfo> findAllUser()throws Exception;

    void addUser(UserInfo userInfo);

    UserInfo findByUserId(String userId);

    List<Role> findOtherRoles(String userId)throws Exception;

    void addRoleToUser(String userId, String[] roleIds)throws Exception;
}
