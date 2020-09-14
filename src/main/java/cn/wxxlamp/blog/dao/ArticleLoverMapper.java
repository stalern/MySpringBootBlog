package cn.wxxlamp.blog.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * 2019年7月29日12:10:12这个感觉不用要了
 * @author Zi10ng
 * @date 2019年7月24日18:48:42
 */
@Mapper
@Repository
public interface ArticleLoverMapper {
    /**
     * 删除文章和用户点赞之间的联系
     * @param aid article_id
     * @param ip ip
     */
    @Delete("delete from article_lover where article_id = #{aid} and user_id = (select id from sys_user where ip = #{ip})")
    void deleteArticleLover(@Param("aid") long aid, @Param("ip") String ip);

    /**
     * 插入文章图片见的关系
     * @param aid 文章
     * @param ip 用户ip
     */
    @Insert("insert into article_lover (article_id, user_id) values (#{aid}, (select id from sys_user where ip = #{ip}))")
    void insertArticleLover(@Param("aid") long aid, @Param("ip") String ip);

    /**
     * 通过ip查看id
     * @param remoteAddr ip
     * @param aid 文章id
     * @return id
     */
    @Select("select su.id from article_lover al, sys_user su where su.ip = #{remoteAddr} and su.id = al.user_id and al.article_id = #{aid}")
    Long getLoveTrueOrFalse(@Param("remoteAddr") String remoteAddr, @Param("aid") long aid);
}
