package com.atguigu.ssyx.common.exception;

import com.atguigu.ssyx.common.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * FileName: GlobalExceptionHandler
 * Package: com.atguigu.ssyx.common.exception
 * Description: TODO
 *
 * @Author: lknowing
 * @Create: 2023/06/19-22:43
 * @Version: 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
//    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.fail(null);
    }

    @ExceptionHandler(SsyxException.class)
//    @ResponseBody
    public Result error(SsyxException e) {
        e.printStackTrace();
        return Result.fail(null);
    }
}
