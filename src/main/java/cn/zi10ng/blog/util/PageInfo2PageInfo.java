package cn.zi10ng.blog.util;

import cn.zi10ng.blog.domain.ArticleInfo;
import cn.zi10ng.blog.domain.ArticleInfoCategory;
import cn.zi10ng.blog.service.CategoryInfoService;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zi10ng
 * @date 2019年8月29日12:44:34
 */
public class PageInfo2PageInfo {

    /**
     * 把articleInfo转换为articleInfoCategory
     * @param pageInfo 页面信息
     * @param categoryInfoService 服务层
     * @return articleInfoCategory
     */
    public static PageInfo<ArticleInfoCategory> article2ArticleCategory(PageInfo<ArticleInfo> pageInfo, CategoryInfoService categoryInfoService){
        List<ArticleInfoCategory> list = new ArrayList<>();
        for (ArticleInfo articleInfo : pageInfo.getList()) {
            list.add(new ArticleInfoCategory(articleInfo,categoryInfoService.listCategoryNameByArticleId(articleInfo.getId())));
        }
        PageInfo<ArticleInfoCategory> pageInfoOut = new PageInfo<>(list);
        pageInfoOut.setTotal(pageInfo.getTotal());
        pageInfoOut.setPageSize(pageInfo.getPageSize());
        pageInfoOut.setPageNum(pageInfo.getPageNum());
        return pageInfoOut;
    }
}
