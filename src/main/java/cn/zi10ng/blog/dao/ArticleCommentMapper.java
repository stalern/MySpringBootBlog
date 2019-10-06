package cn.zi10ng.blog.dao;

import cn.zi10ng.blog.domain.ArticleComment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zi10ng
 * @date 2019年7月24日18:46:19
 */
@Mapper
@Repository
public interface ArticleCommentMapper {

    /**
     * 删除文章评论
     * @param id 文章id
     */
    @Delete("delete from article_comment where article_id = #{id}")
    void deleteArticleComment(long id);

    /**
     * 删除文章评论
     * @param id 文章id
     */
    @Delete("delete from article_comment where comment_id = #{id}")
    void deleteArticleCommentByCommentId(long id);

    /**
     * 插入文章评论
     * @param articleComment 文章评论，文章id，评论id
     */
    @Insert("insert into article_comment (article_id, comment_id) values (#{articleId}, #{commentId})")
    void insertArticleComment(ArticleComment articleComment);

    /**
     * 列出某一文章的所有评论id
     * @param id article_id
     * @return 文章评论的list集合
     */
    @Select("select comment_id from article_comment where article_id = #{id}")
    List<Long> listArticleCommentId(long id);

    /**
     * 设置articleComment的effective
     * @param id commentId
     * @param eff is_effective
     */
    @Update("update article_comment set is_effective = #{eff} where comment_id = #{id}")
    void setEff(@Param("id") long id, @Param("eff") boolean eff);
}