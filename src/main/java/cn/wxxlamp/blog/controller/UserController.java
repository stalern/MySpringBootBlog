package cn.wxxlamp.blog.controller;

import cn.wxxlamp.blog.domain.MyPages;
import cn.wxxlamp.blog.domain.SysUser;
import cn.wxxlamp.blog.util.Md5Utils;
import cn.wxxlamp.blog.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zi10ng
 * @date 2019年9月9日22:12:45
 * 用户相关
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 列出用户
     * @return sysUser
     */
    @GetMapping("/admin/listUser")
    public PageInfo<SysUser> listUser(MyPages myPages){
        return new PageInfo<>(userService.listUser(myPages));
    }

    /**
     * 列出用户的数目
     * @return num
     */
    @GetMapping("/any/getUserNum")
    public long getUserNum(){
        return userService.getUserNum();
    }
    /**
     * 列出所有用户的登录次数
     * @return num
     */
    @GetMapping("/any/getUserLogNum")
    public long getUserLogNum(){
        return userService.getUserLogNum();
    }

    /**
     * 通过ip列出该用户，前端检测是否有用户名
     * @return sysUser
     */
    @GetMapping("/any/getUserByIp")
    public SysUser getUserByIp(HttpServletRequest request){
        return userService.getUserByIp(request.getHeader("X-Real-Ip"));
    }

    @PostMapping("/any/postUser")
    public boolean postUser(@RequestBody SysUser sysUser, HttpServletRequest request){
        return userService.postUser(sysUser, request);
    }

    @PostMapping("/any/login")
    public String login(String username, String password, HttpServletRequest request){
        if (password.equals(userService.getPassword(username))){
            return username + '.' + Md5Utils.encode(username);
        }
        return "false";
    }

}
