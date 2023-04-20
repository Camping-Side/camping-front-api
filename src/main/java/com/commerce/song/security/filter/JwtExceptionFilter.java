package com.commerce.song.security.filter;

import com.camping.common.exception.ExceptionResponse;
import com.commerce.song.exception.JwtNotAvailbleException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class JwtExceptionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        try{
            filterChain.doFilter(request, response);
        }catch (JwtNotAvailbleException e){
            //토큰의 유효기간 만료
            writeException(response, HttpStatus.UNAUTHORIZED, e);
        }catch(AccessDeniedException e) {
            writeException(response, HttpStatus.FORBIDDEN, e);
        }
    }


    private void writeException(HttpServletResponse response, HttpStatus status, RuntimeException e) {
        ExceptionResponse es = new ExceptionResponse(LocalDateTime.now(), e.getMessage());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        try{
            response.getWriter().write(objectMapper.writeValueAsString(es));
        }catch (IOException ioe){
            logger.warn("token valid exception response failed");
            e.printStackTrace();
        }
    }

}
