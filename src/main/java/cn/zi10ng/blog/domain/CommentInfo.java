package cn.zi10ng.blog.domain;

import cn.zi10ng.blog.util.DateFormatUtils;

import java.util.Date;
/**
 * @author zi10ng
 * @date 2019年7月24日18:15:05
 * 评论/留言详细信息
 */
public class CommentInfo {
    /**
     * 主键
     */
    private Long id;

    /**
     * 评论信息
     */
    private String content;

    /**
     * 评论时间
     */
    private Date createBy;
    private String createByStr;
    /**
     * 联系方式：邮箱，QQ、电话
     */
    private String connect;

    /**
     * 昵称
     */
    private String name;

    /**
     * 访问ip
     */
    private String ip;

    /**
     * 修改时间
     */
    private Date modifiedBy;

    /**
     * 父类id
     */
    private long parentId;

    private String modifiedByStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }

    public String getConnect() {
        return connect;
    }

    public void setConnect(String connect) {
        this.connect = connect == null ? null : connect.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
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

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "CommentInfo{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createBy=" + createBy +
                ", createByStr='" + createByStr + '\'' +
                ", connect='" + connect + '\'' +
                ", name='" + name + '\'' +
                ", ip='" + ip + '\'' +
                ", modifiedBy=" + modifiedBy +
                ", parentId=" + parentId +
                ", modifiedByStr='" + modifiedByStr + '\'' +
                '}';
    }
}