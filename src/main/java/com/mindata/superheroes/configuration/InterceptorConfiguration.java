
package com.mindata.superheroes.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.mindata.superheroes.interceptor.RunTimeCounterInterceptor;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    @Autowired
    private RunTimeCounterInterceptor runTimeCounterInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(runTimeCounterInterceptor)
                // Add routes where it should not be applied
                .excludePathPatterns("/users**")
                // Add routes where it should be applied
                .addPathPatterns("/superheroes**");
    }
}
