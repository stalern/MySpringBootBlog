package cn.wxxlamp.blog.util;

import cn.wxxlamp.blog.domain.CommentInfo;

import java.util.*;

/**
 * 通过组合模式生成递归
 * @author stalern
 * @date 2020/02/29~18:48
 */
public class CompositeUtils {

    public static void main(String[] args) {

    }

    public Node buildNode(List<CommentInfo> commentInfos, long parentId) {
        // 新建一个节点
        Node node = new Node("new pro");
        // 把父节点为该节点的节点添加为子节点
        node.add(buildSubNode(commentInfos,parentId));
        return node;
    }

    private Node buildSubNode(List<CommentInfo> commentInfos, long parentId) {
        Node node = null;
        if (Objects.isNull(commentInfos)) {
            return null;
        }
        for (CommentInfo commentInfo : commentInfos) {
            List<CommentInfo> commentInfoList = new ArrayList<>();
            Collections.copy(commentInfoList,commentInfos);
            commentInfoList.remove(commentInfo);
            node = new Node(commentInfo);
            node.add(buildSubNode(commentInfoList,commentInfo.getParentId()));
        }
        return node;
    }

    private static class Node {
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

        private List<Node> nodes = new ArrayList<>();

        public Node(Object object) {
            if (object instanceof CommentInfo) {
                this.object = object;
                this.parentId = ((CommentInfo) object).getParentId();
                this.id = ((CommentInfo) object).getId();
            }
        }

        public void add(Node node) {
            nodes.add(node);
        }

        public void remove(Node node) {
            nodes.remove(node);
        }

        public Object display() {
            for (Node node : nodes) {
                node.display();
            }
            return object;
        }
    }
}
