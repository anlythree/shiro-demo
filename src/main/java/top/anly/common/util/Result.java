package top.anly.common.util;


import lombok.Data;
import top.anly.exception.AppError;

/**
 * 自定义响应结构
 *
 * @author anlythree
 * @date 2019年4月24日
 */
@Data
public class Result<T> {

    public final static Integer CODE_SUCCESS = 0;
    public final static Integer CODE_FAIL = 1;
    public final static String MSG_SUCCESS = "操作成功";
    public final static String MSG_FAIL = "操作失败";

    /**
     * 响应业务状态 0 成功， 1失败
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应中的数据
     */
    private T data;

    public static <T> Result<T> build(Integer status, String msg, T data) {
        return new Result<T>(status, msg, data);
    }

    public static <T> Result<T> ok(T data) {
        return new Result<T>(data);
    }

    public static <T> Result<T> ok() {
        return new Result<T>(CODE_SUCCESS, MSG_SUCCESS, null);
    }

    public static <T> Result<T> fail() {
        return new Result<T>(CODE_FAIL, MSG_FAIL, null);
    }

    public static <T> Result<T> fail(AppError appError) {
        return new Result<T>(appError.getCode(), appError.getMsg(), null);
    }

    public Result() {

    }

    public static <T> Result<T> build(Integer status, String msg) {
        return new Result<T>(status, msg, null);
    }

    public static <T> Result<T> getResult(T t) {
        Result<T> result = new Result<>(t);
        return result;
    }

    public Result(Integer status, String msg, T data) {
        this.code = status;
        this.msg = msg;
        this.data = data;
    }

    public Result(T data) {
        this.code = 0;
        this.msg = MSG_SUCCESS;
        this.data = data;
    }

}
