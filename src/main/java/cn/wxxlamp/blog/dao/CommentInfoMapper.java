package cn.wxxlamp.blog.dao;

import cn.wxxlamp.blog.domain.CommentInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zi10ng
 * @date 2019年7月24日18:46:19
 */
@Mapper
@Repository
public interface CommentInfoMapper {

    /**
     * 列出分类信息
     * @return 分类信息列表
     */
    @Select("select * from comment_info c, sys_user u where c.user_id = u.id")
    @Results(id = "commentInfoMap",value = {
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "modifiedBy", column = "modified_by"),
            @Result(property = "parentId", column = "parent_id")
    })
    List<CommentInfo> listCommentInfo();

    /**
     * 通过article_comment中的comment_id查找评论信息
     * @param id comment_id
     * @return List 集合
     */
    @Select("select * from comment_info c, sys_user u where c.user_id = u.id and c.id = #{id}")
    @ResultMap("commentInfoMap")
    CommentInfo getCommentOfArticle(long id);

    /**
     * 插入评论信息
     * @param commentInfo 必须有content
     * @return 插入后的自增主键
     */
    long insertCommentInfo(CommentInfo commentInfo);

    /**
     * 更新评论信息
     * @param commentInfo 可选
     */
    void updateCommentInfo(CommentInfo commentInfo);

    /**
     * 删除评论信息
     * @param id 评论id
     */
    @Delete("delete from comment_info where id = #{id}")
    void deleteCommentInfo(long id);

    /**
     * 插入评论的用户id
     * @param uid userId
     * @param id id
     */
    @Update("update comment_info set user_id = #{uid} where id = #{id}")
    void updateCommentUserId(@Param("uid") Long uid, @Param("id") Long id);
}