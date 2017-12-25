package mdd.logistics.system;

import mdd.logistics.interceptor.AuthInterceptor;
import mdd.logistics.interceptor.MddArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class MddConfigure extends WebMvcConfigurerAdapter {

    @Bean
    AuthInterceptor localInterceptor() {
        return new AuthInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Override
    public  void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new MddArgumentResolver());
    }
}
