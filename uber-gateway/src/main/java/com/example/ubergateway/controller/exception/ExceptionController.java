package com.example.ubergateway.controller.exception;

import com.example.ubergateway.exception.Code;
import com.example.ubergateway.exception.XException;
import com.example.ubergateway.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@Slf4j
@RestControllerAdvice
public class ExceptionController {
    // Mono中异常转此处理
    // filter内无效，单独处理。
    @ExceptionHandler(XException.class)
    public Mono<ResultVO> handleXException(XException exception) {
        if(exception.getCode() != null) {
            return Mono.just(ResultVO.error(exception.getCode()));
        }
        return Mono.just(ResultVO.error(exception.getCodeN(), exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResultVO> handleException(Exception exception) {
        return Mono.just(ResultVO.error(Code.BAD_REQUEST.getCode(), exception.getMessage()));
    }

}
