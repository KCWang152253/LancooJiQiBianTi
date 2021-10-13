package com.lancoo.interceptor;

import com.lancoo.global.exception.MyRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取token
        String token = request.getHeader("token");
        /*if (StringUtils.isEmpty(token)){
            throw new MyRuntimeException("token不能为空",299,"");
        }*/
        //验证token是否有效
        //String result = userManagerService.TokenCheck(token);
        String result = "true";
        if ("true".equals(result)) {
            return true;
        } else {
            log.error("token :" + token + "  错误！");
            //String loginUrl = sysConfigService.getLgBasicWebInfo().getServerip() + "UserMgr/Login/Login.aspx?lg_sysid=012&lg_preurl=";
            throw new MyRuntimeException(299,"loginUrl");
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
