package cn.wxxlamp.blog.domain;

import cn.wxxlamp.blog.util.DateFormatUtils;

import java.util.Date;

/**
 * @author Zi10ng
 * @date 2019年7月24日18:43:09
 * 文章中引用图片的url
 */
public class ArticlePicture {
    /**
     * 主键
     */
    private Long id;

    /**
     * 文章id
     */
    private Long articleId;

    /**
     * 图片url
     */
    private String pictureUrl;

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

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
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
        return "ArticlePicture{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", createBy=" + createByStr +
                ", modifiedBy=" + modifiedByStr +
                '}';
    }
}
