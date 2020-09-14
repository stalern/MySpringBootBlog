package cn.wxxlamp.blog.service;

import cn.wxxlamp.blog.domain.CommentInfo;

/**
 * 评论相关操作
 * @author Zi10ng
 * @date 2019年7月28日23:31:35
 */
public interface CommentService {
    /**
     * 更新评论
     * @param commentInfo 评论id，comment
     * @return 成功为true，失败为false
     */
    boolean updateCommentInfo(CommentInfo commentInfo);

    /**
     * 增加评论
     * @param commentInfo 评论信息
     * @return 返回插入后的id值，失败返回0
     */
    long insertCommentInfo(CommentInfo commentInfo);

    /**
     * 为评论信息增加用户id
     * @param uid 用户id
     * @param id 评论id
     */
    void updateCommentUserId(Long uid, Long id);
}
