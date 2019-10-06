package cn.zi10ng.blog.dao;

import cn.zi10ng.blog.domain.CategoryInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zi10ng
 * @date 2019年7月24日18:46:19
 */
@Mapper
@Repository
public interface CategoryInfoMapper {

    /**
     * 列出所有分类信息
     * @return list
     */
    @Select("select * from category_info")
    @Results(id = "categoryMap", value = {
            @Result(property = "createBy",column = "create_by"),
            @Result(property = "modifiedBy",column = "modified_by"),
            @Result(property = "parentId", column = "parent_id")
    })
    List<CategoryInfo> listCategoryInfo();

    /**
     * 列出所有子分类
     * @return list
     */
    @Select("select distinct * from category_info c1 where c1.id not in (select parent_id from category_info)")
    List<CategoryInfo> listSubCategoryInfo();

    /**
     * 通过名称返回该分类，主要考虑到了可能一个名称可能有多个分类
     * @param name 分类名
     * @return 分类的集合
     */
    @Select("select * from category_info where name = #{name}")
    @ResultMap("categoryMap")
    List<CategoryInfo> listCategoryInfoByName(String name);

    /**
     * 通过主键返回该分类
     * @param id 分类id
     * @return 分类
     */
    @Select("select * from category_info where id = #{id}")
    @ResultMap("categoryMap")
    CategoryInfo getCategoryInfoById(Long id);

    /**
     * 通过文章id返回该分类
     * @param id 文章id
     * @return 单个分类
     */
    @Select("select c.* " +
            "from category_info c " +
            "join article_category ac on c.id = ac.category_id " +
            "join article_info a on a.id = ac.article_id " +
            "where a.id = #{id}")
    @ResultMap("categoryMap")
    CategoryInfo getCategoryInfoByArticleId(long id);

    /**
     * 通过id返回该分类名称
     * @param id 分类主键
     * @return 分类名称
     */
    @Select("select name from category_info where id = #{id}")
    String getCategoryNameById(Long id);

    /**
     * 插入一个分类，
     * @param categoryInfo 分类信息，插入值一定有name，可能有subId
     */
    void insertCategoryInfo(CategoryInfo categoryInfo);

    /**
     * 通过id更新该分类
     * @param categoryInfo 新的分类信息，一定会传id，但用户名，子分类id，数量可能没有
     */
    void updateCategoryInfo(CategoryInfo categoryInfo);

    /**
     * 通过名称删除
     * @param id 分类id
     */
    @Delete("delete from category_info where id = #{id}")
    void deleteById(long id);
}