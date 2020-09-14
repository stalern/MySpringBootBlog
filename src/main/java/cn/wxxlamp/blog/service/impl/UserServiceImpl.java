package cn.wxxlamp.blog.service.impl;

import cn.wxxlamp.blog.dao.SysUserMapper;
import cn.wxxlamp.blog.domain.MyPages;
import cn.wxxlamp.blog.domain.SysUser;
import cn.wxxlamp.blog.util.Ip2Region;
import cn.wxxlamp.blog.util.OperateByUtils;
import cn.wxxlamp.blog.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author stalern
 * @date 2019年9月9日22:21:37
 */
@Service
public class UserServiceImpl implements UserService {

    private final SysUserMapper sysUserMapper;

    @Autowired
    public UserServiceImpl(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    public List<SysUser> listUser(MyPages myPages) {
        PageHelper.startPage(myPages.getPage(), myPages.getSize());
        return sysUserMapper.listUser();
    }

    @Override
    public long getUserNum() {
        return sysUserMapper.getUserNum();
    }

    @Override
    public long getUserLogNum() {
        return sysUserMapper.getUserLogNum();
    }

    @Override
    public boolean postUser(SysUser sysUser, HttpServletRequest request) {
        boolean flag = false;
        // 通过nginx传来ip
        if (sysUser.getBrowser() == null) {
            // nginx
            sysUser.setIp(request.getHeader("X-Real-Ip"));
            sysUser.setBrowser(OperateByUtils.getOsAndBrowserInfo(request));
            sysUser.setRegion(Ip2Region.sendGet(sysUser.getIp()));
        }
        sysUser.setRole(sysUser.getName() == null ? "ANY" : "USER");
        try {
            sysUserMapper.postUser(sysUser);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public SysUser getUserByIp(String remoteAddr) {
        return sysUserMapper.getUserByIp(remoteAddr);
    }

    @Override
    public String getPassword(String username) {
        return sysUserMapper.getPassword(username);
    }
}
