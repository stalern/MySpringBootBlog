package cn.wxxlamp.blog.util;

import cn.wxxlamp.blog.domain.CategoryInfo;
import cn.wxxlamp.blog.domain.CommentInfo;
import cn.wxxlamp.blog.domain.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zi10ng
 * @date 2019年8月24日21:20:16
 */
public class TreeUtils {
    private List<Long> longs = new ArrayList<>();
    private Node nodeMe = new Node();
    /**
     * 把评论信息的集合转化为一个树
     */
    public Node buildTree(List<CommentInfo> commentInfo, long id){
        Node tree = new Node();
        List<Node> children = new ArrayList<>();
        List<Node> nodeList = new ArrayList<>();
        for (CommentInfo info : commentInfo) {
            children.add(buildNode(info));
        }
        tree.setId(id);
        tree.setChildren(children);
        for (Node child : children) {
            Node node = findNode(children, child.getParentId());
            List<Node> nodes = new ArrayList<>();
            if (node != null) {
                if (node.getChildren() != null) {
                    nodes = node.getChildren();
                    nodes.add(child);
                    node.setChildren(nodes);
                }else {
                    nodes.add(child);
                    node.setChildren(nodes);
                }
                nodeList.add(child);
            }
        }
        for (Node node : nodeList) {
            children.remove(node);
        }
        return tree;
    }

    public Node buildCategoryTree(List<CategoryInfo> categoryInfo, long id){
        Node tree = new Node();
        List<Node> children = new ArrayList<>();
        List<Node> nodeList = new ArrayList<>();
        for (CategoryInfo info : categoryInfo) {
            children.add(buildNode(info));
        }
        tree.setId(id);
        tree.setChildren(children);
        for (Node child : children) {
            Node node = findNode(children, child.getParentId());
            List<Node> nodes = new ArrayList<>();
            if (node != null) {
                if (node.getChildren() != null) {
                    nodes = node.getChildren();
                    nodes.add(child);
                    node.setChildren(nodes);
                }else {
                    nodes.add(child);
                    node.setChildren(nodes);
                }
                nodeList.add(child);
            }
        }
        for (Node node : nodeList) {
            children.remove(node);
        }
        return tree;
    }

    /** 把树转换为list
     * @param node 节点
     * @return list
     */
    public List<Long> travelSubTree(Node node){
        //如果不是父节点的话
        if(node.getChildren() != null) {
            for (Node index : node.getChildren()) {
                longs.add(index.getId());
                if (index.getChildren() != null && index.getChildren().size() > 0 ) {
                    travelSubTree(index);
                }
            }
        }
        return longs;
    }

    /**
     * 拿到某一节点的树以及其子节点
     * @param node 树节点
     * @param id 标识id
     * @return node
     */
    public Node travelTree(Node node, long id){
        if(node != null) {
            for (Node index : node.getChildren()) {
                if (index.getId() == id){
                    return index;
                }
                if (index.getChildren() != null && index.getChildren().size() > 0 ) {
                    nodeMe = travelTree(index, id);
                }
            }
        }
        return nodeMe;
    }
    private void insertNode(CommentInfo info, Node tree){

        Node node = new Node();
        node.setId(info.getId());
        node.setParentId(info.getParentId());
        node.setObject(info);
        node.setChildren(null);

        List<Node> children;

        while (tree != null) {
            children = tree.getChildren();
            if (node.getParentId() == tree.getId()) {
                children.add(node);
                tree.setChildren(children);
                break;
            }
            tree = children.get(0);
        }
    }

    private Node findNode(List<Node> nodes, long id){
        for (Node node : nodes) {
            if (node.getId() == id) {
                return node;
            }
        }
        return null;
    }

    private Node buildNode(CommentInfo info){
        Node node = new Node();
        node.setId(info.getId());
        node.setParentId(info.getParentId());
        node.setObject(info);
        node.setChildren(null);

        return node;
    }
    private Node buildNode(CategoryInfo info){
        Node node = new Node();
        node.setId(info.getId());
        node.setParentId(info.getParentId());
        node.setObject(info);
        node.setChildren(null);

        return node;
    }


}
