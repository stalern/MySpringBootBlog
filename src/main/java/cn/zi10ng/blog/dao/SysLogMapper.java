package cn.zi10ng.blog.dao;

import cn.zi10ng.blog.domain.SysLog;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zi10ng
 * @date 2019年7月24日18:46:19
 */
@Mapper
@Repository
public interface SysLogMapper {

    /**
     * 以list形式展示日志
     * @return list
     */
    @Select("select * from sys_log")
    @Results({
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "operateUrl", column = "operate_url"),
            @Result(property = "operateBy", column = "operate_by"),
    })
    List<SysLog> listSysLog();

    /**
     * 插入日志
     * @param sysLog 日志
     */
    @Insert("insert into sys_log (ip, operate_url, operate_by) values (#{ip},#{operateUrl},#{operateBy})")
    void insertSysLog(SysLog sysLog);
}