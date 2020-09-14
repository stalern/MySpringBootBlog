package cn.wxxlamp.blog.service.impl;

import cn.wxxlamp.blog.service.CategoryInfoService;
import cn.wxxlamp.blog.dao.ArticleCategoryMapper;
import cn.wxxlamp.blog.dao.CategoryInfoMapper;
import cn.wxxlamp.blog.domain.ArticleCategory;
import cn.wxxlamp.blog.domain.CategoryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Zi10ng
 * @date 2019年7月24日21:34:00
 */
@Service("categoryInfoService")
@Transactional(rollbackFor = Exception.class)
public class CategoryInfoServiceImpl implements CategoryInfoService {

    private final CategoryInfoMapper categoryInfoMapper;
    private final ArticleCategoryMapper articleCategoryMapper;

    @Autowired
    public CategoryInfoServiceImpl(CategoryInfoMapper categoryInfoMapper, ArticleCategoryMapper articleCategoryMapper) {
        this.categoryInfoMapper = categoryInfoMapper;
        this.articleCategoryMapper = articleCategoryMapper;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<CategoryInfo> listCategoryInfo() {
        return categoryInfoMapper.listCategoryInfo();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<String> listCategoryNameByArticleId(long id) {
        CategoryInfo categoryInfo = categoryInfoMapper.getCategoryInfoByArticleId(id);
        List<String> list = new ArrayList<>();
        while (categoryInfo != null) {
            list.add(categoryInfoMapper.getCategoryNameById(categoryInfo.getId()));
            categoryInfo = categoryInfoMapper.getCategoryInfoById(categoryInfo.getParentId());
        }
        Collections.reverse(list);
        return list;
    }

    @Override
    public List<CategoryInfo> listSubCategoryInfo() {
        return categoryInfoMapper.listSubCategoryInfo();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<CategoryInfo> listCategoryInfoByName(String name) {
        return categoryInfoMapper.listCategoryInfoByName(name);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<String> listCategoryNameById(List<Long> ids) {
        return ids.stream().map(categoryInfoMapper::getCategoryNameById).collect(Collectors.toList());
    }

    @Override
    public boolean insertCategoryInfo(CategoryInfo categoryInfo) {
        boolean flag = false;
        try {
            categoryInfoMapper.insertCategoryInfo(categoryInfo);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean deleteById(long id) {
        boolean flag = false;
        try {
            // 增加多个文章和上一个分类的关系，如果上一个分类parentId = 0，则不可删除该分类
//             1. 通过分类id在article_category中拿到文章多个id
            List<ArticleCategory> list = articleCategoryMapper.getCategoryInfoByArtId(id);
//             2. 通过分类id拿到parentId
            long parentId = categoryInfoMapper.getCategoryInfoById(id).getParentId();
//             3. 增加文章id和parentId的连接（判断parentId ！= 0）
            if (parentId == 0){
                return false;
            } else {
                for (ArticleCategory articleCategory : list) {
                    articleCategory.setCategoryId(parentId);
                    articleCategoryMapper.insertArticleCategory(articleCategory);
                }
            }
            // 删除分类信息，删除分类和文章的关系
            categoryInfoMapper.deleteById(id);
            articleCategoryMapper.deleteArticleCategoryByCatId(id);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateCategoryInfo(CategoryInfo categoryInfo) {
        boolean flag = false;
        try {
            categoryInfoMapper.updateCategoryInfo(categoryInfo);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateCategoryNumById(long id, int value) {
        boolean flag = false;
        try {
            CategoryInfo categoryInfo = categoryInfoMapper.getCategoryInfoById(id);
            while (categoryInfo != null) {
                categoryInfo.setNumber((byte) (categoryInfo.getNumber() + value));

                categoryInfoMapper.updateCategoryInfo(categoryInfo);
                categoryInfo = categoryInfoMapper.getCategoryInfoById(categoryInfo.getParentId());
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
