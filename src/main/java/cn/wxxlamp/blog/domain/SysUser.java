package cn.wxxlamp.blog.domain;

import cn.wxxlamp.blog.util.DateFormatUtils;

import java.util.Date;

/**
 * @author Zi10ng
 * @date 2019年9月9日22:05:12
 */
public class SysUser {

    /**
     * 主键，自增
     */
    private Long id;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 用户角色
     */
    private String role;
    /**
     * 浏览器
     */
    private String browser;
    /**
     * ip 对应的地区
     */
    private String region;
    /**
     * 联系方式
     */
    private String connect;
    /**
     * 用户ip
     */
    private String ip;

    /**
     * 用户登录次数
     */
    private long num;

    /**
     * 创建时间
     */
    private Date createBy;
    private String createByStr;
    /**
     * 修改时间
     */
    private Date modifiedBy;
    private String modifiedByStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getConnect() {
        return connect;
    }

    public void setConnect(String connect) {
        this.connect = connect;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public Date getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }


    public Date getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Date modifiedBy) {
        this.modifiedBy = modifiedBy;
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

    public String getModifiedByStr() {
        if (modifiedBy != null){
            modifiedByStr = DateFormatUtils.data2String(modifiedBy,"yyyy-MM-dd HH:mm:ss");
        }
        return modifiedByStr;
    }

    public void setModifiedByStr(String modifiedByStr) {
        this.modifiedByStr = modifiedByStr;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", browser='" + browser + '\'' +
                ", region='" + region + '\'' +
                ", connect='" + connect + '\'' +
                ", ip='" + ip + '\'' +
                ", num='" + num + '\'' +
                ", createByStr='" + createByStr + '\'' +
                ", modifiedByStr='" + modifiedByStr + '\'' +
                '}';
    }
}
