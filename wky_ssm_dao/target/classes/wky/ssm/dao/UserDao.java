package wky.ssm.dao;

import org.apache.ibatis.annotations.*;
import wky.ssm.domain.Role;
import wky.ssm.domain.UserInfo;

import java.util.List;


public interface UserDao{
    @Select("select *from users where username=#{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,
                    many = @Many(select = "wky.ssm.dao.RoleDao.findByUserId"))
    })
    public UserInfo findByUsername(String username)throws Exception;

    @Select("select *from users")
    List<UserInfo> findAllUser();

    @Insert("insert into users(email,username,password,phoneNum,status)values(" +
            "#{email},#{username},#{password},#{phoneNum},#{status})")
    void addUser(UserInfo userInfo);

    @Select("select *from users where id=#{userId}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,
                    many = @Many(select = "wky.ssm.dao.RoleDao.findByUserId"))
    })
    UserInfo findByUserId(String userId);

    /*查询出可供用户选择的角色*/
    @Select("select *from role where id not in (select roleId from users_role where userId=#{userId})")
    List<Role> findOtherRoles(String userId)throws Exception;

    @Insert("insert into users_role (userId,roleId)values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}
