package com.k.passbook.security;


import com.k.passbook.constant.Constants;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器：需要实现org.springframework.web.servlet里的HandlerInterceptor接口
 */
@Component
public class AuthCheckInterceptor implements HandlerInterceptor {


    /**
     * 请求之前的处理
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String token = httpServletRequest.getHeader(Constants.TOKEN_STRING);

        //token 为空
        if(StringUtils.isEmpty(token)){
            throw  new Exception("Header 中缺少" + Constants.TOKEN_STRING);

        }
        //token 不相等（颁发的token和传递过来的token不相等）
        if (!token.equals(Constants.TOKEN)){
            throw new Exception("Header 中" + Constants.TOKEN_STRING+ "错误");
        }

        //通过前面的校验后设置token
        AccessContext.setToken(token);


        return true;
    }

    /**
     * 这是请求完成后的处理
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * afterCompletion 是即使postHandle 抛出异常也会执行的方法
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //请求完成后将线程的token 清除
        AccessContext.clearAccessKey();
    }
}
