package com.example.mall2.pojo;

/**
 * @author lala
 */

import lombok.Data;

/**
 * 自定义
 */

@Data
public class Result {
    //其中msg是你想要返回的信息，code就是自定义的状态码了,data是要返回的数据，这里我采用了Object类型。
    private Integer code;
    private String msg;
    private Object data;

    public Result() {
        this.code = Integer.valueOf(0);
        this.msg = "";
        this.data = null;
    }
    /**
     * 返回状态码，信息，以及数据
     */
    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    /**
     * 只返回状态码和信息，用于失败时使用
     */
    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }
}
