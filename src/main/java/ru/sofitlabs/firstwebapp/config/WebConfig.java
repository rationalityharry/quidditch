package ru.sofitlabs.firstwebapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ru.sofitlabs.firstwebapp.utils.AuthorisationInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorisationInterceptor())
                .addPathPatterns("/anime**")
                .addPathPatterns("/user**");
    }
}
