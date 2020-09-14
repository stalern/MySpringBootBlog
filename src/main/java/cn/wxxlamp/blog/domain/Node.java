package cn.wxxlamp.blog.domain;

import java.util.List;

/**
 * @author Zi10ng
 * @date 2019年8月24日21:20:33
 */
public class Node {
    /**
     * 放置信息
     */
    private Object object;

    /**
     * 父类id
     */
    private long parentId;

    /**
     * 自己id
     */
    private long id;

    /**
     * 放置子类
     */
    private List<Node> children;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Node{" +
                "object=" + object +
                ", parentId=" + parentId +
                ", id=" + id +
                ", children=" + children +
                '}';
    }
}
