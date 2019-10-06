package cn.zi10ng.blog.domain;

import cn.zi10ng.blog.util.DateFormatUtils;

import java.util.Date;
/**
 * @author zi10ng
 * @date 2019年7月24日18:15:05
 * 文章内容
 */
public class ArticleContent {
    /**
     * 主键
     */
    private Long id;

    /**
     * 文章主键
     */
    private Long articleId;

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
    /**
     * 文章内容
     */
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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
        return "ArticleContent{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", createBy=" + createByStr +
                ", modifiedBy=" + modifiedByStr +
                ", content='" + content + '\'' +
                '}';
    }
}