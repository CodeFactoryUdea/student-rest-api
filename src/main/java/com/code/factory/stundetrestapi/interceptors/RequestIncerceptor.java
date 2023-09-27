package com.code.factory.stundetrestapi.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
public class RequestIncerceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestIncerceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        try {

            String myHeader = request.getHeader("MY-HEADER");
            if(Objects.isNull(myHeader)||myHeader.isEmpty()){
                throw new RuntimeException("There is no header here");
            }

            LOGGER.info("My header is {}", myHeader);
            return true;
        } catch (RuntimeException e) {
            response.setContentType("application/json");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().write("Error: "+e.getMessage());
            return  false;
        } catch (Exception e) {
            response.setContentType("application/json");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().write("Unhandle error: "+e.getMessage());
            return  false;
        }

    }
}
