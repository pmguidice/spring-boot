package com.pg.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class HelloInterceptor extends HandlerInterceptorAdapter {
    private static Logger logger = LoggerFactory.getLogger(HelloInterceptor.class);

    /**
     * Executed before actual handler is executed
     **/
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
            throws Exception {
        final Map<String, String> pathVariables = getPathVariables(request);

        if (pathVariables != null) {
            logger.info("[preHandle] path variables:");
            for (Map.Entry<String, String> entry : pathVariables.entrySet()) {
                logger.info("{} : {}", entry.getKey(), entry.getValue());

                if ("id".equals(entry.getKey())) {
                    try {
                        Integer.parseInt(entry.getValue());
                    } catch (NumberFormatException e) {
                        logger.info("aborting processing of request because ID is too large");

                        response.getWriter().write("Invalid ID");
                        response.setStatus(HttpStatus.BAD_REQUEST.value());

                        return false;
                    }
                }
            }
        } else {
            logger.info("path variables null");
        }

        logger.info("[preHandle][" + request + "]" + "[" + request.getMethod() + "]" + request.getRequestURI());
        return true;
    }

    /**
     * Executed before after handler is executed
     **/
    @Override
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
            final ModelAndView modelAndView) throws Exception {
        logger.info("[postHandle][" + request + "]");
    }

    /**
     * Executed after complete request is finished
     **/
    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
            final Object handler, final Exception ex) throws Exception {
        if (ex != null) {
            ex.printStackTrace();
        }
        logger.info("[afterCompletion][" + request + "][exception: " + ex + "]");
    }

    private Map<String, String> getPathVariables(HttpServletRequest req) {
        return (Map<String, String>) req.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
    }
}
