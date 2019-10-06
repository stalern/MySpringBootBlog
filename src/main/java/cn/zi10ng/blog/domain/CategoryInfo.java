package cn.zi10ng.blog.domain;

import cn.zi10ng.blog.util.DateFormatUtils;

import java.util.Date;
/**
 * @author zi10ng
 * @date 2019年7月24日18:15:05
 * 分类信息，类似标签
 */
public class CategoryInfo {
    /**
     * 主键
     */
    private Long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类的文章数量
     */
    private Byte number;

    /**
     * 创建分类的时间
     */
    private Date createBy;
    private String createByStr;
    /**
     * 分类的修改时间
     */
    private Date modifiedBy;
    private String modifiedByStr;

    /**
     * 分类的父id
     */
    private long parentId;

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
        this.name = name == null ? null : name.trim();
    }

    public Byte getNumber() {
        return number;
    }

    public void setNumber(Byte number) {
        this.number = number;
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

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long  parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "CategoryInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", createBy=" + createByStr +
                ", modifiedBy=" + modifiedByStr +
                ", parentId=" + parentId +
                '}';
    }
}