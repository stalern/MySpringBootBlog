package cn.zi10ng.blog.dao;

import cn.zi10ng.blog.domain.ArticleInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zi10ng
 * @date 2019年7月24日18:46:19
 */
@Mapper
@Repository
public interface ArticleInfoMapper {

    /**
     * 按时间降序查询全部文章信息
     * @return list
     */
    @Select("select * from article_info order by create_by desc")
    @Results(id = "ArticleInfoMap",value = {
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "modifiedBy", column = "modified_by"),
            @Result(property = "loveNum", column = "love_num")
    })
    List<ArticleInfo> listArticleInfoByTime();

    /**
     * 通过信息id查询文章信息
     * @param id id
     * @return 文章信息
     */
    @Select("select * from article_info where id = #{id}")
    @ResultMap("ArticleInfoMap")
    ArticleInfo getArticleInfoById(long id);

    /**
     * 列出时间的文章数目
     * @return map
     */
    @Select("SELECT concat(EXTRACT(YEAR FROM create_by), '-', EXTRACT(MONTH FROM create_by)) AS time, COUNT(id) AS num FROM article_info GROUP BY time;")
    @MapKey("time")
    List<Map<String, Integer>> listNumOfTime();

    /**
     * 通过某一时间查询文章信息
     * @param time 时间
     * @return list
     */
    @Select("select * " +
            "from article_info " +
            "where concat(EXTRACT(YEAR FROM create_by), '-', EXTRACT(MONTH FROM create_by)) = #{time} and id not in (2,3)" +
            "order by create_by desc")
    @ResultMap("ArticleInfoMap")
    List<ArticleInfo> listArticleInfoByTimeNum(String time);
    /**
     * 按照热度降序查询全部文章
     * @return list
     */
    @Select("select * from article_info order by traffic desc")
    @ResultMap("ArticleInfoMap")
    List<ArticleInfo> listArticleInfoByTra();

    /**
     * 按分类，时间降序查询全部文章
     * @param categoryId 分类id
     * @return list
     */
    @Select({"<script>",
            "select a.*" +
                    "from article_info as a, article_category as ac,category_info as c ",
                    "where c.id = ac.category_id and a.id = ac.article_id ",
                            "and c.id in",
                     "  <foreach item= 'categoryId' index= 'index' collection= 'list'",
                     "      open='(' separator=',' close=')'>",
                     "        #{categoryId}",
                     "  </foreach>",
                    "order by create_by desc",
             "</script>"})
    @ResultMap("ArticleInfoMap")
    List<ArticleInfo> listArticleInfoByCategory(List<Long> categoryId);

    /**
     * 插入文章信息
     * @param articleInfo 文章信息，包含title和summary
     * @return long
     */
    long insertArticleInfo(ArticleInfo articleInfo);

    /**
     * 更新文章信息
     * @param articleInfo 文章信息，必须有id，其他可选
     */
    void updateArticleInfo(ArticleInfo articleInfo);

    /**
     * 使文章浏览量加一
     * @param id article_id
     */
    @Update("update article_info set traffic = traffic + 1 where id = #{id}")
    void addArticleTra(long id);

    /**
     * 使文章喜欢者加一
     * @param id article_id
     * @param flag 1为++，-1 为--
     */
    @Update("update article_info set love_num = love_num + #{flag} where id = #{id}")
    void updateArticleLov(@Param("id") long id, @Param("flag") int flag);

    /**
     * 删除文章信息
     * @param id 信息id
     */
    @Delete("delete from article_info where id = #{id}")
    void deleteArticleInfo(Long id);
}