package com.commerce.song.security.filter;

import com.commerce.song.exception.ExceptionResponse;
import com.commerce.song.exception.JwtNotAvailbleException;
import com.commerce.song.util.SecurityUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.util.NestedServletException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PermitAllFilter extends FilterSecurityInterceptor {
    private static final String FILTER_APPLIED = "__spring_security_filterSecurityInterceptor_filterApplied";
    private boolean observeOncePerRequest = true;

    private List<RequestMatcher> permitAllRequestMatchers = new ArrayList<>();

    public PermitAllFilter(String... permitAllResources) {
        for(String resource : permitAllResources) {
            permitAllRequestMatchers.add(new AntPathRequestMatcher(resource));
        }
    }

    @Override
    protected InterceptorStatusToken beforeInvocation(Object object) {
        boolean permitAll = false;
        HttpServletRequest request = ((FilterInvocation) object).getRequest();
        for(RequestMatcher requestMatcher : permitAllRequestMatchers) {
            if(requestMatcher.matches(request)) {
                permitAll = true;
                break;
            }
        }

        // local 마스터 계정이면 pass
        String userEmail = SecurityUtil.getCurrentEmail();
        if(StringUtils.equals("master@camping.kr",userEmail) || permitAll) {
            return null;
        }

        return super.beforeInvocation(object);
    }

    public void invoke(FilterInvocation fi) throws IOException, ServletException {
        if ((fi.getRequest() != null)
                && (fi.getRequest().getAttribute(FILTER_APPLIED) != null)
                && observeOncePerRequest) {
            // filter already applied to this request and user wants us to observe
            // once-per-request handling, so don't re-do security checking
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        }
        else {
            // first time this request being called, so perform security checking
            if (fi.getRequest() != null && observeOncePerRequest) {
                fi.getRequest().setAttribute(FILTER_APPLIED, Boolean.TRUE);
            }
            HttpServletResponse response = fi.getResponse();

            try {
                InterceptorStatusToken token = beforeInvocation(fi);

                try {
                    fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
                }
                finally {
                    super.finallyInvocation(token);
                }

                super.afterInvocation(token, null);
            } catch(NestedServletException e) {
                resWrite(response, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            } catch(JwtNotAvailbleException e) {
                resWrite(response, HttpStatus.FORBIDDEN, e.getMessage());
            } catch(Exception e) {
                resWrite(response, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }


        }
    }

    private void resWrite(HttpServletResponse response, HttpStatus status, String msg) {
        logger.warn(msg);
        ExceptionResponse es = new ExceptionResponse(LocalDateTime.now(), msg);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        try{
            response.getWriter().write(objectMapper.writeValueAsString(es));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
