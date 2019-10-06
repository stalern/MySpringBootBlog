package cn.zi10ng.blog.service;

import cn.zi10ng.blog.domain.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 关于文章信息和内容
 * @author Zi10ng
 * @date 2019年7月25日21:20:35
 */
public interface ArticleService {

    /**
     * 通过id查询特定文章的信息
     * @param id id
     * @return 文章信息
     */
    ArticleInfo getArticleInfoById(long id);

    /**
     * 根据时间列出文章数目
     * @return map
     */
    List<Map<String, Integer>> listNumOfTime();

    /**
     * 按时间列出所有文章信息
     * @param myPages 传递第几页和数量
     * @return 所有文章信息
     */
    List<ArticleInfo> listArticleInfoByTime(MyPages myPages);

    /**
     * 按访问量列出所有文章信息
     * @param myPages 传递第几页和数量
     * @return 所有文章信息
     */
    List<ArticleInfo> listArticleInfoByTra(MyPages myPages);

    /**
     * 按分类列出文章</br>
     * 此处需要操作两个表信息
     * 先通过id列出来树，然后查找children，最后返回集合
     * @param categoryTree node类型的category树
     * @param categoryId 分类id
     * @param myPages 自定义页面，传递第几页和数量
     * @return list
     */
    List<ArticleInfo> listArticleInfoByCategory(Node categoryTree, long categoryId, MyPages myPages);

    /**
     * 通过时间获取文章信息
     * @param time year-month
     * @param myPages pages
     * @return category_id
     */
    List<ArticleInfo> listArticleInfoByTimeNum(String time, MyPages myPages);

    /**
     * 通过文章的id获取文章内容，可能整改
     * 注：文章的id可以在前端通过文章信息获取
     * @param id id
     * @return 文章内容
     */
    ArticleContent getArticleContentById(Long id);

    /**
     * 通过文章article_id获得评论
     * @param id article_id
     * @return 评论集合
     */
    List<CommentInfo> listCommentOfArticle(long id);

    /**
     * 通过文章id列出分类
     * @param id id
     * @return 分类信息
     */
    CategoryInfo getCategoryInfoById(long id);

    /**
     * 增加一篇文章
     * @param articleInfo 要增加的信息，包含title，summary
     * @param articleContent 要增加的内容，包含content即可
     * @param articleCategory 要增加的分类和文章之间的关系，此时其中只包含一个id
     * @return 成功为true，失败为false
     * 其实也可以在插入成功的时候返回主键，这样耦合度更低
     */
    boolean insertArticle(ArticleInfo articleInfo, ArticleContent articleContent, ArticleCategory articleCategory);

    /**
     * 增加文章和评论的映射
     * @param articleComment 文章评论
     * @return 成功为true，失败为false
     */
    boolean insertArticleComment(ArticleComment articleComment);

    /**
     * 更新文章的基本信息
     * @param articleInfo 文章信息，必须有id
     * @return 成功为true，失败为false
     */
    boolean updateArticleInfo(ArticleInfo articleInfo);

    /**
     * 使得文章浏览量加一
     * @param aid article_id
     * @param ip ip 为-1说明是traffic，否则是love的ip
     * @param flag 为true说明是love，否则是noLike
     * @return 成功为true，失败为false
     */
    boolean updateArticleInfo(long aid, String ip, boolean flag);
    /**
     * 更新文章的内容
     * @param articleContent 文章内容，必须有article_id和content
     * @return 成功为true，失败为false
     */
    boolean updateArticleContent(ArticleContent articleContent);

    /**
     * 更新文章和分类之间的关系
     * @param articleCategory 文章id和分类id
     * @return 成功为true，失败为false
     */
    boolean updateArticleCategory(ArticleCategory articleCategory);

    /**
     * 删除一篇文章，只要知道文章信息的id就可以，但是要连接4个数据库
     * 要删除文章的信息，内容，和分类之间的关系，和图片之间的关系，以及和评论之间的关系
     * @param id 要删除的文章id
     * @return 成功为true，失败为false
     */
    boolean deleteArticle(long id);

    /**
     * 把article_comment的effect设置为0或1
     * @param id 评论id
     * @param flag 设1为flag，0位false
     * @return 成功为true，失败是false
     */
    boolean setArticleCommentEff(long id, boolean flag);

    /**
     * 删除文章与评论的关系
     * @param id 评论id
     * @return 成功为true，失败是false
     */
    boolean deleteArticleComment(long id);

    /**
     * 查看该ip用户是否点赞
     * @param request 请求
     * @param aid 文章id
     * @return 如果未点赞返回-1，点赞返回id
     */
    long getLoveTrueOrFalse(HttpServletRequest request, long aid);
}
