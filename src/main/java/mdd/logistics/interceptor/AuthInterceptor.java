package mdd.logistics.interceptor;

import io.jsonwebtoken.ClaimJwtException;
import mdd.logistics.domain.User;
import mdd.logistics.service.jwt.JwtCreator;
import mdd.logistics.system.annotation.NonLogin;
import mdd.logistics.system.constant.CodeEnums;
import mdd.logistics.system.exception.MddException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    JwtCreator jwtCreator;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod hm = (HandlerMethod) handler;
        NonLogin nl = hm.getMethodAnnotation(NonLogin.class);
        if (nl != null && nl.value()) {
            return true;
        }
        String token = request.getHeader("token");
        if (token == null) {
            throw new MddException(CodeEnums.NEED_TOKEN);
        }
        try {
            User user = jwtCreator.getJwtUser(token);
            request.setAttribute("currentUser",user);
            response.setHeader("token", jwtCreator.createJwt(user.getId()));
        } catch (ClaimJwtException e) {
            throw new MddException(CodeEnums.TOKEN_IS_INVALID);
        }
        return true;
    }
}
