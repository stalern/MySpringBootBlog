package cn.zi10ng.blog.dao;

import cn.zi10ng.blog.domain.ArticleContent;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


/**
 * @author zi10ng
 * @date 2019年7月28日21:18:03
 */
@Mapper
@Repository
public interface ArticleContentMapper {

    /**
     * 按文章的id获取文章内容
     * @param id id
     * @return 文章内容
     */
    @Select("select * from article_content where article_id = #{id}")
    @Results(id = "ArticleContentMap",value = {
            @Result(property = "articleId", column = "article_id"),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "modifiedBy", column = "modified_by")
    })
    ArticleContent getArticleContentById(Long id);

    /**
     * 插入文章内容
     * @param articleContent 文章内容类
     */
    @Insert("insert into article_content (content, article_id) values (#{content}, #{articleId})")
    void insertArticleContent(ArticleContent articleContent);

    /**
     * 更新文章内容
     * @param articleContent 文章内容content, article_id
     */
    void updateArticleContent(ArticleContent articleContent);

    /**
     * 删除文章内容
     * @param id 文章信息的id
     */
    @Delete("delete from article_content where article_id = #{id}")
    void deleteArticleContent(Long id);
}