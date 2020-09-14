package cn.wxxlamp.blog.controller;

import cn.wxxlamp.blog.domain.*;
import cn.wxxlamp.blog.service.ArticleService;
import cn.wxxlamp.blog.service.CategoryInfoService;
import cn.wxxlamp.blog.service.CommentService;
import cn.wxxlamp.blog.service.UserService;
import cn.wxxlamp.blog.util.PageInfo2PageInfo;
import cn.wxxlamp.blog.util.TreeUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 文章相关操作
 *
 * @author Zi10ng
 * @date 2019年7月25日17:48:30
 */
@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;
    private final CategoryInfoService categoryInfoService;
    private final CommentService commentService;
    private final UserService userService;

    @Autowired
    public ArticleController(ArticleService articleService, CategoryInfoService categoryInfoService, CommentService commentService , UserService userService) {
        this.articleService = articleService;
        this.categoryInfoService = categoryInfoService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/any/byId")
    public ArticleInfo getArticleInfoById(long id) {
        return articleService.getArticleInfoById(id);
    }

    @GetMapping("/any/contentById")
    public ArticleContent getArticleContentById(long id) {
        return articleService.getArticleContentById(id);
    }

    @GetMapping("/any/getCategoryInfoById")
    public CategoryInfo getCategoryInfoById(long id) {
        return articleService.getCategoryInfoById(id);
    }

    @GetMapping("/any/listNumOfTime")
    public String listNumOfTime() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(articleService.listNumOfTime());
    }

    @GetMapping("/any/byTime")
    public PageInfo<ArticleInfoCategory> listArticleInfoByTime(MyPages myPages) {
        // 也可用mybatis 一对多查询
        PageInfo<ArticleInfo> pageInfo = new PageInfo<>(articleService.listArticleInfoByTime(myPages));
        return PageInfo2PageInfo.article2ArticleCategory(pageInfo, categoryInfoService);
    }

    @GetMapping("/any/byTra")
    public PageInfo<ArticleInfo> listArticleInfoByTra(MyPages myPages) {
        return new PageInfo<>(articleService.listArticleInfoByTra(myPages));
    }

    @GetMapping("/any/{categoryId}")
    public PageInfo<ArticleInfoCategory> listArticleInfoByCategory(@PathVariable long categoryId, MyPages pages) {
        TreeUtils treeUtils = new TreeUtils();
        PageInfo<ArticleInfo> pageInfo = new PageInfo<>(articleService.listArticleInfoByCategory(treeUtils.buildCategoryTree(categoryInfoService.listCategoryInfo(), 0), categoryId, pages));
        return PageInfo2PageInfo.article2ArticleCategory(pageInfo, categoryInfoService);
    }

    @GetMapping("/any/listArtByTime/{time}")
    public PageInfo<ArticleInfoCategory> listArticleInfoByNum(@PathVariable String time, MyPages myPages) {
        PageInfo<ArticleInfo> pageInfo = new PageInfo<>(articleService.listArticleInfoByTimeNum(time, myPages));
        return PageInfo2PageInfo.article2ArticleCategory(pageInfo, categoryInfoService);
    }

    @GetMapping("/user/getLoveTrueOrFalse")
    public long getLoveTrueOrFalse(HttpServletRequest request, long aid){
        return articleService.getLoveTrueOrFalse(request, aid);
    }
    @GetMapping("/any/article")
    public List<Object> getArticle(long id) {
        List<Object> list = new ArrayList<>();
        TreeUtils treeUtils = new TreeUtils();
        list.add(articleService.getArticleContentById(id));
        list.add(treeUtils.buildTree(articleService.listCommentOfArticle(id), id));
        list.add(categoryInfoService.listCategoryNameByArticleId(id));
        articleService.updateArticleInfo(id, "traffic", true);
        return list;
    }

    /**
     * @param map 两个元素，第一个是文章信息，第二个是文章内容，第三个是文章和分类间的list映射
     * @return 成功为true，失败为false
     */
    @PostMapping("/admin/postArticle")
    public boolean postArticle(@RequestBody Map<String, Object> map) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        ArticleInfo articleInfo = objectMapper.readValue(
                objectMapper.writeValueAsString(map.get("articleInfo")), ArticleInfo.class);
        ArticleContent articleContent = objectMapper.readValue(
                objectMapper.writeValueAsString(map.get("articleContent")), ArticleContent.class);
        ArticleCategory articleCategory = objectMapper.readValue(
                objectMapper.writeValueAsString(map.get("articleCategory")), ArticleCategory.class);

        if (articleService.insertArticle(articleInfo, articleContent, articleCategory)){
            categoryInfoService.updateCategoryNumById(articleCategory.getCategoryId(), 1);
            return true;
        }
        return false;
    }

    @PostMapping("/user/postComment/{id}")
    public boolean postArticleComment(@PathVariable long id, @RequestBody CommentInfo commentInfo, HttpServletRequest request) {
        // 配置nginx
        SysUser sysUser = userService.getUserByIp(request.getHeader("X-Real-Ip"));
        if (commentInfo.getName() != null){
            sysUser.setName(commentInfo.getName());
            sysUser.setConnect(commentInfo.getConnect());
            userService.postUser(sysUser, request);
        }
        commentService.insertCommentInfo(commentInfo);
        // 此处就要这样写
        commentService.updateCommentUserId(sysUser.getId(), commentInfo.getId());
        articleService.insertArticleComment(new ArticleComment(id, commentInfo.getId()));
        return true;
    }

    @PostMapping("/user/postLover")
    public boolean postArticleLover(long aid, HttpServletRequest request, boolean flag) {
        // 配置nginx
        return articleService.updateArticleInfo(aid, request.getHeader("X-Real-Ip"), flag);
    }

    @PutMapping("/admin/updateArticle")
    public boolean updateArticleInfo(@RequestBody Map<String, Object> map) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        ArticleInfo articleInfo = objectMapper.readValue(
                objectMapper.writeValueAsString(map.get("articleInfo")), ArticleInfo.class);
        ArticleContent articleContent = objectMapper.readValue(
                objectMapper.writeValueAsString(map.get("articleContent")), ArticleContent.class);
        ArticleCategory articleCategory = objectMapper.readValue(
                objectMapper.writeValueAsString(map.get("articleCategory")), ArticleCategory.class);

        long rawCategoryId = articleService.getCategoryInfoById(articleCategory.getArticleId()).getId();
        // 如果分类没有更新
        if (rawCategoryId == articleCategory.getCategoryId()){
            return articleService.updateArticleInfo(articleInfo) &&
                    articleService.updateArticleContent(articleContent);
        } else {
            return articleService.updateArticleInfo(articleInfo) &&
                    articleService.updateArticleContent(articleContent) &&
                    articleService.updateArticleCategory(articleCategory) &&
                    categoryInfoService.updateCategoryNumById(rawCategoryId, -1) &&
                    categoryInfoService.updateCategoryNumById(articleCategory.getCategoryId(), 1);
        }
    }

    @DeleteMapping("/admin/delete")
    public boolean deleteArticle(long id) {
        // 可能是这里顺序的问题
        return categoryInfoService.updateCategoryNumById(articleService.getCategoryInfoById(id).getId(), -1) && articleService.deleteArticle(id);
    }

    @PutMapping("/admin/delete/setCommentEff")
    public boolean putArticleComment(long id, boolean flag) {
        return articleService.setArticleCommentEff(id, flag);
    }

    @DeleteMapping("/admin/delete/comment")
    public boolean deleteArticleComment(long id) {
        return articleService.deleteArticleComment(id);
    }
}
