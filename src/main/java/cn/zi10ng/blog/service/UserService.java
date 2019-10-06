package cn.zi10ng.blog.service;

import cn.zi10ng.blog.domain.MyPages;
import cn.zi10ng.blog.domain.SysUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Zi10ng
 * @date 2019年9月9日22:19:12
 */

public interface UserService {

    /**
     * 列出用户
     * @param myPages 页数
     * @return sysUser
     */
    List<SysUser> listUser(MyPages myPages);

    /**
     * 列出用户数目
     * @return long
     */
    long getUserNum();

    /**
     * 列出所有用户的登录
     * @return long
     */
    long getUserLogNum();

    /**
     * 有两种情况：
     *    1. 用户刚浏览时，需要拿到ip，
     *          如果ip存在，则num++
     *          如果ip不存在，则新建（role = any，region， browser）
     *    2. 用户提交评论后，需要拿到ip，name，connect，（role = user，region，browser），对其更新
     * @param sysUser 用户
     * @param request 返回的请求，用来获取ip
     * @return 成功为true，失败是false
     */
    boolean postUser(SysUser sysUser, HttpServletRequest request);

    /**
     * 通过ip获得该用户
     * @param remoteAddr ip地址
     * @return 该用户信息
     */
    SysUser getUserByIp(String remoteAddr);

    /**
     * 返回密码
     * @param username 用户名
     * @return pwd
     */
    String getPassword(String username);
}
