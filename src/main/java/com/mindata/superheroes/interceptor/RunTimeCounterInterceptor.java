package com.mindata.superheroes.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.mindata.superheroes.annotation.RunTimeCounter;

/**
 * Component that implements a HandlerInterceptor to verify if the request method has
 * the @RunTimeAnnotation to log the time that the method takes to response.
 * 
 * @author carlos.lafferriere
 *
 */
@Component
public class RunTimeCounterInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(RunTimeCounterInterceptor.class);

    private String timeAttr = "startTime";

    /**
     * PreHandler of the method that verify if the request method has or not the annotation to
     * initialize the timeAttr, to count the time that takes to run.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {

        if (hasAnnotation((HandlerMethod) handler)) {
            request.setAttribute(timeAttr, System.currentTimeMillis());
        }
        return true;
    }

    /**
     * PostHandler of the method that verify if the request method has or not the annotation to log
     * the time that the method takes to run.
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView model) throws Exception {

        if (hasAnnotation((HandlerMethod) handler)) {
            Long start = (Long) request.getAttribute(timeAttr);
            String method = request.getMethod();
            String url = request.getRequestURL().toString();
            Long time = (System.currentTimeMillis() - start);
            logger.info("{} {} - Tiempo de ejecucion: {} milisegundos.", method, url, time);
        }
    }

    /**
     * Method that verify if the handler has the @RunTimeCounter annotation.
     * 
     * @param handler to verify
     * @return true if the handler has the @RunTimeCounter annotation.
     */
    private boolean hasAnnotation(HandlerMethod handler) {
        return handler.hasMethodAnnotation(RunTimeCounter.class);
    }

}
