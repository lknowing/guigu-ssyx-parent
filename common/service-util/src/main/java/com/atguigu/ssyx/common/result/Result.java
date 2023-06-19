package com.atguigu.ssyx.common.result;

import lombok.Data;

/**
 * FileName: Result
 * Package: com.atguigu.ssyx.common.result
 * Description: 统一返回结果类
 *
 * @Author: lknowing
 * @Create: 2023/06/19-22:27
 * @Version: 1.0
 */
@Data
public class Result<T> {

    private Integer code;

    private String message;

    private T data;

    private Result() {
    }

    public static <T> Result<T> build(T data, ResultCodeEnum resultCodeEnum) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }

    public static <T> Result<T> ok(T data) {
        return build(data, ResultCodeEnum.SUCCESS);
    }

    public static <T> Result<T> fail(T data) {
        return build(data, ResultCodeEnum.FAIL);
    }
}
