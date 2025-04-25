package com.drinktea.interceptor;

import com.drinktea.constant.JwtClaimsConstant;
import com.drinktea.context.BaseContext;
import com.drinktea.properties.JwtProperties;
import com.drinktea.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 校验jwt
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */


    //1、从请求头中获取令牌
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String token = request.getHeader(jwtProperties.getAdminTokenName());
        //2、校验令牌
        try {
            log.info("jwt校验: {}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
            Long empID = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());
            log.info("当前员工id: {}", empID);
            BaseContext.setCurrentId(empID);
            //3、通过，放行
            return true;
        } catch (Exception e) {
            //4、不通过，响应401状态码
            log.error(e.getMessage());
            response.setStatus(401);
            return false;
        }
    }
}
