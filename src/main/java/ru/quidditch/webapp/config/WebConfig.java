package ru.quidditch.webapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ru.quidditch.webapp.utils.AuthorisationInterceptor;

import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorisationInterceptor())
                .addPathPatterns("/anime**")
                .addPathPatterns("/user**")
                .addPathPatterns("/anime/**")
                .addPathPatterns("/addAnime**")
                .addPathPatterns("/user/**").addPathPatterns("/admin**").addPathPatterns("/admin/**");
    }

    @Override
    public void extendMessageConverters(final List<HttpMessageConverter<?>> converters) {
        super.extendMessageConverters(converters);
        for (int i = converters.size() - 1; i >= 0; i--) {
            if (converters.get(i) instanceof StringHttpMessageConverter) {
                converters.remove(i);
            }
        }
    }

}
