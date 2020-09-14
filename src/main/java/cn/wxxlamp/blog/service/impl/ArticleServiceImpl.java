package cn.wxxlamp.blog.service.impl;

import cn.wxxlamp.blog.dao.*;
import cn.wxxlamp.blog.domain.*;
import cn.wxxlamp.blog.service.ArticleService;
import cn.wxxlamp.blog.util.TreeUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Zi10ng
 * @date 2019年7月26日08:56:23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleServiceImpl implements ArticleService {

    private final ArticleInfoMapper articleInfoMapper;
    private final ArticleContentMapper articleContentMapper;
    private final ArticleCategoryMapper articleCategoryMapper;
    private final ArticleCommentMapper articleCommentMapper;
    private final CommentInfoMapper commentInfoMapper;
    private final ArticleLoverMapper articleLoverMapper;

    @Autowired
    public ArticleServiceImpl(ArticleInfoMapper articleInfoMapper, ArticleContentMapper articleContentMapper,
                              ArticleCategoryMapper articleCategoryMapper, ArticleCommentMapper articleCommentMapper,
                              CommentInfoMapper commentInfoMapper, ArticleLoverMapper articleLoverMapper) {
        this.articleInfoMapper = articleInfoMapper;
        this.articleContentMapper = articleContentMapper;
        this.articleCategoryMapper = articleCategoryMapper;
        this.articleCommentMapper = articleCommentMapper;
        this.commentInfoMapper = commentInfoMapper;
        this.articleLoverMapper = articleLoverMapper;
    }

    @Override
    public ArticleInfo getArticleInfoById(long id) {
        return articleInfoMapper.getArticleInfoById(id);
    }

    @Override
    public List<Map<String, Integer>> listNumOfTime() {
        return articleInfoMapper.listNumOfTime();
    }

    @Override
    public List<ArticleInfo> listArticleInfoByTime(MyPages myPages) {
        PageHelper.startPage(myPages.getPage(), myPages.getSize());
        return articleInfoMapper.listArticleInfoByTime();
    }

    @Override
    public List<ArticleInfo> listArticleInfoByTra(MyPages myPages) {
        PageHelper.startPage(myPages.getPage(), myPages.getSize());
        return articleInfoMapper.listArticleInfoByTra();
    }

    @Override
    public List<ArticleInfo> listArticleInfoByCategory(Node categoryTree, long categoryId, MyPages mypages) {
        TreeUtils treeUtils = new TreeUtils();
        List<Long> articleInfoId = new ArrayList<>(treeUtils.travelSubTree(treeUtils.travelTree(categoryTree, categoryId)));
        if (categoryTree.getId() == 0) {
            articleInfoId.add(categoryId);
        }
        PageHelper.startPage(mypages.getPage(), mypages.getSize());
        return articleInfoMapper.listArticleInfoByCategory(articleInfoId);
    }

    @Override
    public List<ArticleInfo> listArticleInfoByTimeNum(String time, MyPages myPages) {
        PageHelper.startPage(myPages.getPage(), myPages.getSize());
        return articleInfoMapper.listArticleInfoByTimeNum(time);
    }

    @Override
    public ArticleContent getArticleContentById(Long id) {
        return articleContentMapper.getArticleContentById(id);
    }

    @Override
    public List<CommentInfo> listCommentOfArticle(long id) {
        List<CommentInfo> comments = new ArrayList<>();
        for (Long aLong : articleCommentMapper.listArticleCommentId(id)) {
            comments.add(commentInfoMapper.getCommentOfArticle(aLong));
        }
        return comments;
    }

    @Override
    public CategoryInfo getCategoryInfoById(long id) {
        return articleCategoryMapper.getCategoryInfoById(id);
    }

    @Override
    public boolean insertArticle(ArticleInfo articleInfo, ArticleContent articleContent, ArticleCategory articleCategory) {
        boolean flag = false;
        try {
            // 插入文章
            articleInfoMapper.insertArticleInfo(articleInfo);

            long articleId = articleInfo.getId();

            articleContent.setArticleId(articleId);
            // 插入内容
            articleContentMapper.insertArticleContent(articleContent);

            //插入文章分类
            articleCategory.setArticleId(articleId);
            articleCategoryMapper.insertArticleCategory(articleCategory);

            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean insertArticleComment(ArticleComment articleComment) {
        boolean flag = false;
        try {
            articleCommentMapper.insertArticleComment(articleComment);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateArticleInfo(ArticleInfo articleInfo) {
        boolean flag = false;
        try {
            articleInfoMapper.updateArticleInfo(articleInfo);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateArticleInfo(long aid, String ip, boolean f) {
        boolean flag = false;
        String traffic = "traffic";
        try {
            if (traffic.equals(ip)) {
                articleInfoMapper.addArticleTra(aid);
            } else {
                if (f) {
                    articleLoverMapper.insertArticleLover(aid, ip);
                    articleInfoMapper.updateArticleLov(aid, 1);
                } else {
                    articleLoverMapper.deleteArticleLover(aid, ip);
                    articleInfoMapper.updateArticleLov(aid, -1);
                }
            }
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateArticleContent(ArticleContent articleContent) {
        boolean flag = false;
        try {
            articleContentMapper.updateArticleContent(articleContent);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateArticleCategory(ArticleCategory articleCategory) {
        boolean flag = false;
        try {
            articleCategoryMapper.updateArticleCategory(articleCategory);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    @Override
    public boolean deleteArticle(long id) {
        boolean flag = false;
        try {
            articleInfoMapper.deleteArticleInfo(id);
            articleContentMapper.deleteArticleContent(id);
            articleCategoryMapper.deleteArticleCategoryByArtId(id);
            articleCommentMapper.deleteArticleComment(id);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean setArticleCommentEff(long id, boolean flag) {
        TreeUtils treeUtils = new TreeUtils();
        boolean flag1 = false;
        try {
            articleCommentMapper.setEff(id, flag);
            for (Long aLong : treeUtils.travelSubTree(treeUtils.travelTree(treeUtils.buildTree(commentInfoMapper.listCommentInfo(), 0), id))) {
               articleCommentMapper.setEff(aLong, flag);
            }
            flag1 = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag1;
    }

    @Override
    public boolean deleteArticleComment(long id) {
        TreeUtils treeUtils = new TreeUtils();
        boolean flag = false;
        try {
            articleCommentMapper.deleteArticleCommentByCommentId(id);
            for (Long aLong : treeUtils.travelSubTree(treeUtils.travelTree(treeUtils.buildTree(commentInfoMapper.listCommentInfo(), 0), id))) {
                articleCommentMapper.deleteArticleCommentByCommentId(aLong);
            }
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public long getLoveTrueOrFalse(HttpServletRequest request, long aid) {
        return articleLoverMapper.getLoveTrueOrFalse(request.getHeader("X-Real-Ip"), aid) == null ? -1 : 1;
    }
}
