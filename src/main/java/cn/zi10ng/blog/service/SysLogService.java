package cn.zi10ng.blog.service;

import cn.zi10ng.blog.domain.SysLog;

import java.util.List;

/**
 * 记录系统日志
 * @author Zi10ng
 * @date 2019年7月25日15:24:35
 */
public interface SysLogService {

    /**
     * 插入日志
     * @param sysLog 日志
     * @return 成功为true，失败为false
     */
    void insertSysLog(SysLog sysLog);

    /**
     * 以list形式展示日志
     * @return list
     */
    List<SysLog> listSysLog();
}
