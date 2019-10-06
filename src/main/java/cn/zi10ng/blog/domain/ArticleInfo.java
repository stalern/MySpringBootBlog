package cn.zi10ng.blog.domain;

import cn.zi10ng.blog.util.DateFormatUtils;

import java.util.Date;
/**
 * @author zi10ng
 * @date 2019年7月24日18:15:05
 * 文章信息
 */
public class ArticleInfo {
    /**
     * 主键
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 文章摘要
     */
    private String summary;

    /**
     * 文章访问次数
     */
    private Integer traffic;

    /**
     * 文章创建时间
     */
    private Date createBy;
    private String createByStr;
    /**
     * 文章修改时间
     */
    private Date modifiedBy;
    private String modifiedByStr;
    /**
     * 点赞人数
     */
    private Integer loveNum;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public Integer getTraffic() {
        return traffic;
    }

    public void setTraffic(Integer traffic) {
        this.traffic = traffic;
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

    public Integer getLoveNum() {
        return loveNum;
    }

    public void setLoveNum(Integer loveNum) {
        this.loveNum = loveNum;
    }

    @Override
    public String toString() {
        return "ArticleInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", traffic=" + traffic +
                ", createBy=" + createByStr +
                ", modifiedBy=" + modifiedByStr +
                ", loveNum=" + loveNum +
                '}';
    }
}