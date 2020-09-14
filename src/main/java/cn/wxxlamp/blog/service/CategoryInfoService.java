package cn.wxxlamp.blog.service;

import cn.wxxlamp.blog.domain.CategoryInfo;

import java.util.List;

/**
 * @author Zi10ng
 * @date 2019年7月24日21:31:11
 * 分类功能的实现类
 */
public interface CategoryInfoService {

    /**
     * 返回所有分类
     * @return list
     */
    List<CategoryInfo> listCategoryInfo();

    /**
     * 通过文章id返回文章分类名称
     * @param id 文章id
     * @return 文章可能由多个分类，故为list
     */
    List<String> listCategoryNameByArticleId(long id);

    /**
     * 列出所有的子分类
     * @return list
     */
    List<CategoryInfo> listSubCategoryInfo();

    /**
     * 通过名称返回该分类
     * @param name 分类名
     * @return 分类
     */
    List<CategoryInfo> listCategoryInfoByName(String name);

    /**
     * 通过id获取分类名称
     * @param ids 分类id
     * @return list集合的名字
     */
    List<String> listCategoryNameById(List<Long> ids);

    /**
     * 新建一个分类
     * @param categoryInfo 分类，一定有分类的名称，可能有子分类名
     * @return 成功为true，失败为false
     */
    boolean insertCategoryInfo(CategoryInfo categoryInfo);

    /**
     * 通过分类id删除分类
     *  // 增加多个文章和上一个分类的关系，如果上一个分类parentId = 0，则不可删除该分类
     *    1. 通过分类id在article_category中拿到文章id
     *    2. 通过分类id拿到parentId
     *    3. 增加文章id和parentId的连接（判断parentId ！= 0）
     * // 删除分类信息，删除分类和文章的关系
     * @param id 分类id
     * @return 成功为true，失败为false
     */
    boolean deleteById(long id);

    /**
     * 必须要传过来一个id值
     * @param categoryInfo 该类中必须要有id值，可能有名字，子类名，文章数
     * @return 成功为true，失败为false
     */
    boolean updateCategoryInfo(CategoryInfo categoryInfo);

    /**
     * 通过id更新其以及其父类的num++/--
     * @param id 分类id
     * @param value 加num还是减num
     * @return 成功为true，失败为false
     */
    boolean updateCategoryNumById(long id, int value);
}
