package com.commerce.song.exception;

import com.camping.common.exception.AwsUploadException;
import com.camping.common.exception.BadRequestException;
import com.camping.common.exception.BusinessException;
import com.camping.common.exception.ExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.AuthenticationException;
import javax.persistence.EntityNotFoundException;
import javax.validation.UnexpectedTypeException;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    /** 기본적인건 ResponseEntityExceptionHandler에 있음 */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.warn("request : " + request.getDescription(false) + " // status :  " + status.value()  + " // details : " + e.getMessage(), e);
        ExceptionResponse es = new ExceptionResponse(LocalDateTime.now(), "처리에 문제가 발생했습니다.", request.getDescription(false), null);
        return super.handleExceptionInternal(e, es, headers, status, request);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.warn("request : " + request.getDescription(false) + " // status :  " + HttpStatus.BAD_REQUEST  + " // details : " + e.getMessage(), e);
        // 여러건 validation이 걸려도 최초 1건만 메시지로 내려줌
        ExceptionResponse es = new ExceptionResponse(LocalDateTime.now(), e.getBindingResult().getAllErrors().get(0).getDefaultMessage(), request.getDescription(false), null);
        return new ResponseEntity<>(es, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(UnexpectedTypeException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.warn("request : " + request.getDescription(false) + " // status :  " + HttpStatus.BAD_REQUEST  + " // details : " + e.getMessage(), e);
        ExceptionResponse es = new ExceptionResponse(LocalDateTime.now(), e.getMessage(), request.getDescription(false), null);
        return new ResponseEntity<>(es, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.warn("request : " + request.getDescription(false) + " // status :  " + HttpStatus.BAD_REQUEST  + " // details : " + e.getMessage(), e);
        // 여러건 validation이 걸려도 최초 1건만 메시지로 내려줌
        ExceptionResponse es = new ExceptionResponse(LocalDateTime.now(), e.getBindingResult().getAllErrors().get(0).getDefaultMessage(), request.getDescription(false), null);
        return new ResponseEntity<>(es, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JwtNotAvailbleException.class)
    protected ResponseEntity<ExceptionResponse> handler(JwtNotAvailbleException e, WebRequest request) {
        logger.warn("request : " + request.getDescription(false) + " // status :  " + HttpStatus.UNAUTHORIZED  + " // details : " + e.getMessage(), e);
        ExceptionResponse es = new ExceptionResponse(LocalDateTime.now(), e.getMessage(), request.getDescription(false), null);
        return new ResponseEntity<>(es, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<ExceptionResponse> handler(BadRequestException e, WebRequest request) {
        logger.warn("request : " + request.getDescription(false) + " // status :  " + HttpStatus.BAD_REQUEST  + " // details : " + e.getMessage(), e);
        ExceptionResponse es = new ExceptionResponse(LocalDateTime.now(), e.getMessage(), request.getDescription(false), null);
        return new ResponseEntity<>(es, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ExceptionResponse> handler(BusinessException e, WebRequest request) {
        logger.warn("request : " + request.getDescription(false) + " // status :  " + HttpStatus.INTERNAL_SERVER_ERROR  + " // details : " + e.getMessage(), e);
        ExceptionResponse es = new ExceptionResponse(LocalDateTime.now(), e.getMessage(), request.getDescription(false), null);
        return new ResponseEntity<>(es, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AwsUploadException.class)
    protected ResponseEntity<ExceptionResponse> handler(AwsUploadException e, WebRequest request) {
        logger.warn("request : " + request.getDescription(false) + " // status :  " + HttpStatus.INTERNAL_SERVER_ERROR  + " // details : " + e.getMessage(), e);
        ExceptionResponse es = new ExceptionResponse(LocalDateTime.now(), e.getMessage(), request.getDescription(false), e.getCode().getCode());
        return new ResponseEntity<>(es, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ExceptionResponse> handler(EntityNotFoundException e, WebRequest request) {
        logger.warn("request : " + request.getDescription(false) + " // status :  " + HttpStatus.BAD_REQUEST  + " // details : " + e.getMessage(), e);
        ExceptionResponse es = new ExceptionResponse(LocalDateTime.now(), e.getMessage(), request.getDescription(false), null);
        return new ResponseEntity<>(es, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<ExceptionResponse> handler(BadCredentialsException e, WebRequest request) {
        logger.warn("request : " + request.getDescription(false) + " // status :  " + HttpStatus.UNAUTHORIZED  + " // details : " + e.getMessage(), e);
        ExceptionResponse es = new ExceptionResponse(LocalDateTime.now(), e.getMessage(), request.getDescription(false), null);
        return new ResponseEntity<>(es, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidParameterException.class)
    protected ResponseEntity<ExceptionResponse> handler(InvalidParameterException e, WebRequest request) {
        logger.warn("request : " + request.getDescription(false) + " // status :  " + HttpStatus.BAD_REQUEST  + " // details : " + e.getMessage(), e);
        ExceptionResponse es = new ExceptionResponse(LocalDateTime.now(), e.getMessage(), request.getDescription(false), null);
        return new ResponseEntity<>(es, HttpStatus.BAD_REQUEST);
    }
    /**
     * Authentication 객체가 필요한 권한을 보유하지 않은 경우 발생합
     */
    @ExceptionHandler({AuthenticationException.class, AccessDeniedException.class})
    protected ResponseEntity<ExceptionResponse> AuthHandler(Exception e, WebRequest request) {
        logger.warn("request : " + request.getDescription(false) + " // status :  " + HttpStatus.UNAUTHORIZED  + " // details : " + e.getMessage(), e);
        ExceptionResponse es = new ExceptionResponse(LocalDateTime.now(), e.getMessage(), request.getDescription(false), null);
        return new ResponseEntity<>(es, HttpStatus.UNAUTHORIZED);
    }

    /**
     * enum type 일치하지 않아 binding 못할 경우 발생
     * 주로 @RequestParam enum으로 binding 못했을 경우 발생
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ExceptionResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, WebRequest request) {
        logger.warn("request : " + request.getDescription(false) + " // status :  " + HttpStatus.BAD_REQUEST  + " // details : " + e.getMessage(), e);
        ExceptionResponse es = new ExceptionResponse(LocalDateTime.now(), e.getMessage(), request.getDescription(false), null);
        return new ResponseEntity<>(es, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionResponse> handler(Exception e, WebRequest request) {
        logger.warn("request : " + request.getDescription(false) + " // status :  " + HttpStatus.INTERNAL_SERVER_ERROR  + " // details : " + e.getMessage(), e);
        ExceptionResponse es = new ExceptionResponse(LocalDateTime.now(), e.getMessage(), request.getDescription(false), null);
        return new ResponseEntity<>(es, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
