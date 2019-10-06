package cn.zi10ng.blog.interceptor;

import cn.zi10ng.blog.service.UserService;
import cn.zi10ng.blog.util.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author stalern
 * @date 2019年9月17日19:01:09
 * 拦截器
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String admin = "admin";
        // 从 http 请求头中取出 token
        String tokenRaw = httpServletRequest.getHeader("token");
        String uri = httpServletRequest.getRequestURI();
        //如果路径中包含admin
        if (uri.contains(admin)) {
            if (tokenRaw == null) {
                return false;
            } else {
                String[] token = httpServletRequest.getHeader("token").split("[.]");
                return !token[1].equals(Md5Utils.encode(userService.getPassword(token[0])));
            }
        }

        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
