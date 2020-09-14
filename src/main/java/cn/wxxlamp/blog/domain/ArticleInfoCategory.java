package cn.wxxlamp.blog.domain;

import java.util.List;

/**
 * @author Zi10ng
 * @date 2019年8月28日12:54:20
 * 不与数据库交互，用于混合问斩信息和分类
 */
public class ArticleInfoCategory {
    private ArticleInfo articleInfo;
    private List<String>  articleCategoryNames;

    public ArticleInfo getArticleInfo() {
        return articleInfo;
    }

    public ArticleInfoCategory(ArticleInfo articleInfo, List<String> articleCategoryNames) {
        this.articleInfo = articleInfo;
        this.articleCategoryNames = articleCategoryNames;
    }

    public void setArticleInfo(ArticleInfo articleInfo) {
        this.articleInfo = articleInfo;
    }

    public List<String> getArticleCategoryNames() {
        return articleCategoryNames;
    }

    public void setArticleCategoryNames(List<String> articleCategoryNames) {
        this.articleCategoryNames = articleCategoryNames;
    }

    @Override
    public String toString() {
        return "ArticleInfoCategory{" +
                "articleInfo=" + articleInfo +
                ", articleCategoryNames=" + articleCategoryNames +
                '}';
    }
}
