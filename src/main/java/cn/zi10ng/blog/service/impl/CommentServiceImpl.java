package cn.zi10ng.blog.service.impl;

import cn.zi10ng.blog.dao.CommentInfoMapper;
import cn.zi10ng.blog.domain.CommentInfo;
import cn.zi10ng.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Zi10ng
 * @date 2019年7月28日23:32:31
 * 注：删除文章之后不会删除文章和评论的信息
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CommentServiceImpl implements CommentService {


    private final CommentInfoMapper commentInfoMapper;

    @Autowired
    public CommentServiceImpl(CommentInfoMapper commentInfoMapper) {
        this.commentInfoMapper = commentInfoMapper;
    }

    @Override
    public boolean updateCommentInfo(CommentInfo commentInfo) {
        boolean flag = false;
        try {
            commentInfoMapper.updateCommentInfo(commentInfo);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public long insertCommentInfo(CommentInfo commentInfo) {
        try {
            return commentInfoMapper.insertCommentInfo(commentInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void updateCommentUserId(Long uid, Long id) {
       commentInfoMapper.updateCommentUserId(uid, id);
    }
}
