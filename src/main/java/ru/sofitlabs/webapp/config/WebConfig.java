package ru.sofitlabs.webapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ru.sofitlabs.webapp.utils.AuthorisationInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorisationInterceptor())
                .addPathPatterns("/anime**")
                .addPathPatterns("/user**")
                .addPathPatterns("/anime/**")
                .addPathPatterns("/addAnime**")
                .addPathPatterns("/user/**");
    }
}
