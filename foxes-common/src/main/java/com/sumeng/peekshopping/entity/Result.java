package com.sumeng.peekshopping.entity;

import com.sumeng.peekshopping.constant.StatusCode;
import lombok.Data;

/**
 * 返回结果实体类
 *
 * @date: 2020/6/27 9:41
 * @author: sumeng
 */
@Data
public class Result<T> {

    /**
     * 是否成功
     */
    private boolean flag;

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String message;

    /**
     *
     */
    private T data;

    public T getData() {
        return data;
    }

    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = (T) data;
    }

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result() {
        this.flag = true;
        this.code = StatusCode.OK;
        this.message = "执行成功";
    }
}
