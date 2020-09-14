package cn.wxxlamp.blog.dao;

import cn.wxxlamp.blog.domain.CategoryInfo;
import cn.wxxlamp.blog.domain.ArticleCategory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 对文章分类的操作
 * 注：一个文章有多个分类，不用更新分类，直接删除或者增加
 * @author zi10ng
 * @date 2019年7月28日21:18:08
 */
@Mapper
@Repository
public interface ArticleCategoryMapper {

    /**
     * 通过文章id获得分类信息
     * @param id 文章id
     * @return 分类信息
     */
    @Select("select c.* from article_category ac join category_info c on c.id = ac.category_id where ac.article_id = #{id}")
    CategoryInfo getCategoryInfoById(long id);

    /**
     * 通过分类id获得分类信息
     * @param id 分类id
     * @return 分类信息
     */
    @Select("select * from article_category where category_id = #{id}")
    List<ArticleCategory> getCategoryInfoByArtId(long id);

    /**
     * 增加文章分类
     * @param articleCategory 分类，包含文章id和分类id
     */
    @Insert("insert into article_category (category_id, article_id) values (#{categoryId}, #{articleId})")
    void insertArticleCategory(ArticleCategory articleCategory);

    /**
     * 删除文章映射分类
     * @param id article_id
     */
    @Delete("delete from article_category where article_id = #{id}")
    void deleteArticleCategoryByArtId(Long id);

    /**
     * 删除文章映射分类
     * @param id category_id
     */
    @Delete("delete from article_category where category_id = #{id}")
    void deleteArticleCategoryByCatId(Long id);

    /**
     * 更新文章分类
     * @param articleCategory 文章分类
     */
    @Update("update article_category set category_id = #{categoryId} where article_id = #{articleId}")
    void updateArticleCategory(ArticleCategory articleCategory);
}