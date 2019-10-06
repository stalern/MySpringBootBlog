package cn.zi10ng.blog.dao;

import cn.zi10ng.blog.domain.SysUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zi10ng
 * @date 2019年9月9日22:09:53
 */
@Repository
@Mapper
public interface SysUserMapper {

    /**
     * 更新密码
     */
    @Update("update sys_me set password = {password} where name = {username}")
    void updatePassword(String password, String username);

    /**
     * 列出用户
     * @return sysUser
     */
    @Select("select * from sys_user order by modified_by desc")
    @Results(id = "sysUserMap",value = {
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "modifiedBy", column = "modified_by")
    })
    List<SysUser> listUser();

    /**
     * 列出用户数目
     * @return long
     */
    @Select("select count(ip) from sys_user")
    long getUserNum();

    /**
     * 列出所有用户的登录次数
     * @return long
     */
    @Select("select sum(num) from sys_user")
    long getUserLogNum();

    /**
     * 有两种情况：
     *    1. 用户刚浏览时，需要拿到ip，
     *          如果ip存在，则num++
     *          如果ip不存在，则新建（role = any，region， browser）
     *    2. 用户提交评论后，需要拿到ip，name，connect，（role = user，region，browser），对其更新
     * @param sysUser 用户
     */
    void postUser(SysUser sysUser);

    /**
     * 通过ip获得用户
     * @param remoteAddr ip地址
     * @return sysUser
     */
    @Select("select * from sys_user where ip = #{remoteAddr}")
    SysUser getUserByIp(String remoteAddr);

    /**
     * 获取密码
     * @param username 用户名
     * @return pwd
     */
    @Select("select password from sys_me where name = #{username} or u_id = (select id from sys_user where name = #{username})")
    String getPassword(String username);
}
