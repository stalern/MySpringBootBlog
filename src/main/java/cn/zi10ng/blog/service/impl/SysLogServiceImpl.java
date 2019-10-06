package cn.zi10ng.blog.service.impl;

import cn.zi10ng.blog.dao.SysLogMapper;
import cn.zi10ng.blog.domain.SysLog;
import cn.zi10ng.blog.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Zi10ng
 * @date 2019年7月25日15:27:20
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    private final SysLogMapper sysLogMapper;

    @Autowired
    public SysLogServiceImpl(SysLogMapper sysLogMapper) {
        this.sysLogMapper = sysLogMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertSysLog(SysLog sysLog) {
        sysLogMapper.insertSysLog(sysLog);
    }

    @Override
    public List<SysLog> listSysLog() {
        return sysLogMapper.listSysLog();
    }
}
