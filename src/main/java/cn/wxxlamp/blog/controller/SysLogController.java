package cn.wxxlamp.blog.controller;

import cn.wxxlamp.blog.domain.SysLog;
import cn.wxxlamp.blog.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用于展示日志
 * @author Zi10ng
 * @date 2019年7月25日16:19:08
 */
@RestController
@RequestMapping("/admin/log")
public class SysLogController {

    private final SysLogService sysLogService;

    @Autowired
    public SysLogController(SysLogService sysLogService) {
        this.sysLogService = sysLogService;
    }

    @GetMapping("/list")
    public List<SysLog> listSysLog(){
        return sysLogService.listSysLog();
    }
}
