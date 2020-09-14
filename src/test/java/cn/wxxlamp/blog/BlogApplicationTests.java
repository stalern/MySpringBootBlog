package cn.wxxlamp.blog;

import cn.wxxlamp.blog.dao.ArticleInfoMapper;
import cn.wxxlamp.blog.domain.Node;
import cn.wxxlamp.blog.service.ArticleService;
import cn.wxxlamp.blog.util.Ip2Region;
import cn.wxxlamp.blog.util.TreeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    @Autowired
    ArticleInfoMapper articleInfoMapper;
    @Autowired
    ArticleService articleService;

    @Test
    public void contextLoads() {
        TreeUtils treeUtils = new TreeUtils();
        Node tree = treeUtils.buildTree(articleService.listCommentOfArticle(11),11);
        tree.setId(11);
        System.out.println(tree);
    }

    @Test
    public void textIp(){
        System.out.println(Ip2Region.sendGet("219.155.76.197"));
    }

}
