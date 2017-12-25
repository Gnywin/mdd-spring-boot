package mdd.logistics.interceptor;

import mdd.logistics.domain.User;
import mdd.logistics.system.annotation.CurrentUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;

/**
 * Created by 猫大东 on 2017/6/11.
 */
public class MddArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Annotation[] as = parameter.getParameterAnnotations();
        for (Annotation a : as) {
            if (a.annotationType() == CurrentUser.class) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest req =   webRequest.getNativeRequest(HttpServletRequest.class);
        if (parameter.getParameterType().isAssignableFrom(User.class)) {
            return req.getAttribute("currentUser");
        }
        return null;
    }
}
