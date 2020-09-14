package cn.wxxlamp.blog.controller;

import cn.wxxlamp.blog.domain.CategoryInfo;
import cn.wxxlamp.blog.domain.Node;
import cn.wxxlamp.blog.service.CategoryInfoService;
import cn.wxxlamp.blog.util.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Zi10ng
 * @date 2019年7月24日21:07:05
 * 处理分类标签相关请求
 */
@RestController
@RequestMapping("/categoryInfo")
public class CategoryInfoController {

    private final CategoryInfoService categoryInfoService;

    @Autowired
    public CategoryInfoController(CategoryInfoService categoryInfoService) {
        this.categoryInfoService = categoryInfoService;
    }

    @GetMapping("/any/list")
    public Node listCategoryInfo(){
        TreeUtils treeUtils = new TreeUtils();
        return treeUtils.buildCategoryTree(categoryInfoService.listCategoryInfo(),0);
    }

    @GetMapping("/any/listPa")
    public List<CategoryInfo> listCategoryInfoPa(){
        return categoryInfoService.listCategoryInfo();
    }

    @GetMapping("/any/listSub")
    public List<CategoryInfo> listSubCategoryInfo(){
        return categoryInfoService.listSubCategoryInfo();
    }

    @GetMapping("/any/categoryByArtId")
    public List<String> listCategoryNameByArticleId(long id){
        return categoryInfoService.listCategoryNameByArticleId(id);
    }

    @PostMapping("/admin/post")
    public boolean postCategoryInfo(@RequestBody CategoryInfo categoryInfo){
        return categoryInfoService.insertCategoryInfo(categoryInfo);
    }

    @DeleteMapping("/admin/{id}")
    public boolean deleteCategoryInfoById(@PathVariable long id){
        return categoryInfoService.deleteById(id);
    }

    @GetMapping("/any/{name}")
    public List<CategoryInfo> getCategoryInfoByName(@PathVariable String name){
        return categoryInfoService.listCategoryInfoByName(name);
    }

    @PutMapping("/admin/update")
    public boolean updateCategoryInfo(@RequestBody CategoryInfo categoryInfo){
        return categoryInfoService.updateCategoryInfo(categoryInfo);
    }
}
