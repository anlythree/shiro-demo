package top.anly.common.util;


import top.anly.exception.AppError;
import top.anly.exception.AppException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * 该类用于校验导入的excel文件格式中必填字段是否导入(勿删！)
 *
 * @author wangli
 * @date 2020/5/7 17:08
 */
public class ValidationUtil {

    /**
     * 验证器构造
     */
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 通过hibernate-validator注解来校验字段（需手动调用）
     *
     * @param obj
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <T> void validate(T obj, Integer index) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
        // 抛出检验异常
        if (constraintViolations.size() > 0) {
            throw new AppException(AppError.IMPORT_ERROR, "第" + index + "条数据中" + constraintViolations.iterator().next().getMessage());
        }
    }
}
