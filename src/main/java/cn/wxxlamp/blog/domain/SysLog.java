package cn.wxxlamp.blog.domain;

import cn.wxxlamp.blog.util.DateFormatUtils;

import java.util.Date;
/**
 * @author zi10ng
 * @date 2019年7月24日18:15:05
 * 系统日志
 */
public class SysLog {
    /**
     * 主键
     */
    private Long id;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 访问时间
     */
    private Date createBy;
    private String createByStr;
    /**
     * 访问的url
     */
    private String operateUrl;

    /**
     * 访问的浏览器
     */
    private String operateBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }

    public String getOperateUrl() {
        return operateUrl;
    }

    public void setOperateUrl(String operateUrl) {
        this.operateUrl = operateUrl == null ? null : operateUrl.trim();
    }

    public String getOperateBy() {
        return operateBy;
    }

    public void setOperateBy(String operateBy) {
        this.operateBy = operateBy == null ? null : operateBy.trim();
    }

    public String getCreateByStr() {
        if (createBy != null){
            createByStr = DateFormatUtils.data2String(createBy,"yyyy-MM-dd HH:mm:ss");
        }
        return createByStr;
    }

    public void setCreateByStr(String createByStr) {
        this.createByStr = createByStr;
    }

    @Override
    public String toString() {
        return "SysLogService{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", createBy=" + createByStr +
                ", operateUrl='" + operateUrl + '\'' +
                ", operateBy='" + operateBy + '\'' +
                '}';
    }
}