package cn.wxxlamp.blog.aop;

import cn.wxxlamp.blog.domain.SysLog;
import cn.wxxlamp.blog.service.SysLogService;
import cn.wxxlamp.blog.util.OperateByUtils;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 记录访问的路径，但是不记录log路径
 * @author Zi10ng
 * @date 2019年7月25日15:49:51
 */
@Component
@Aspect
public class SysLogAspect {

    private final SysLogService sysLogService;
    private final HttpServletRequest request;

    @Autowired
    public SysLogAspect(SysLogService sysLogService, HttpServletRequest request) {
        this.sysLogService = sysLogService;
        this.request = request;
    }
    /**
     * 后置通知
     */
    @After("execution(* cn.wxxlamp.blog.controller.*.*(..))")
    public void doAfter(){
        String url = String.valueOf(request.getRequestURL());
        String log = "/log";
        if (!url.contains(log)) {
            SysLog sysLog = new SysLog();
            sysLog.setIp(request.getRemoteAddr());
            sysLog.setOperateUrl(url);
            sysLog.setOperateBy(OperateByUtils.getOsAndBrowserInfo(request));
            sysLogService.insertSysLog(sysLog);
        }
    }
}
