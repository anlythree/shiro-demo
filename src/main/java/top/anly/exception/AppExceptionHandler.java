package top.anly.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.anly.common.util.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * 管理平台全局异常处理
 *
 * @author zhenhua.huo
 * @date 2019/4/24 下午1:37
 */
@ControllerAdvice
@Slf4j
public class AppExceptionHandler {
    @ExceptionHandler
    @ResponseBody
    public Result handleAndReturnData(HttpServletRequest request, Exception ex) {
        Result result = new Result();
        //系统自定义异常，透传错误码及错误信息
        if (ex instanceof AppException) {
            AppException appException = (AppException) ex;
            log.error("[{}]异常[{}]", request.getRequestURI(), appException);
            //非法参数校验：返回注解中错误描述，无需到错误信息表查询映射信息
            result.setCode(appException.getErrorCode());
            result.setMsg(appException.getErrorMessage());
            return result;
        }
        //其他异常，统一返回给前端 系统异常
        log.error("[{}]异常[{}]", request.getRequestURI(), ex);
        result.setCode(AppError.ERROR.getCode());
        result.setMsg(AppError.ERROR.getMsg());
        return result;
    }
}