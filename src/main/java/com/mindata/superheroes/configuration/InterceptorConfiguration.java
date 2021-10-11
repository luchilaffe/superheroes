
package com.mindata.superheroes.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.mindata.superheroes.interceptor.RunTimeCounterInterceptor;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    private RunTimeCounterInterceptor runTimeCounterInterceptor;

    public InterceptorConfiguration(RunTimeCounterInterceptor runTimeCounterInterceptor) {
        this.runTimeCounterInterceptor = runTimeCounterInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(runTimeCounterInterceptor)
                // Add routes where it should not be applied
                .excludePathPatterns("/users**")
                // Add routes where it should be applied
                .addPathPatterns("/superheroes**");
    }
}
