package top.anly.exception;
/**
 * 应用自定义异常
 *
 * @author zhenhua.huo
 * @date 2019/4/23 下午1:37
 */
public class AppException extends RuntimeException {

  private final Integer errorCode;
  private final String errorMessage;

  public AppException(Integer exceptionCode, String exceptionMessage) {
    super(exceptionCode + ":" + exceptionMessage);
    this.errorCode = exceptionCode;
    this.errorMessage = exceptionMessage;
  }
  public AppException(AppError appError, String errorMessage) {
    super(appError.getCode() + ":" + errorMessage);
    this.errorCode = appError.getCode();
    this.errorMessage = errorMessage;
  }
  public AppException(Integer errorCode) {
    super();
    this.errorCode = errorCode;
    this.errorMessage = "";
  }

  public AppException(Integer exceptionCode, String exceptionMessage, Throwable throwable) {
    super(throwable);
    this.errorCode = exceptionCode;
    this.errorMessage = exceptionMessage;
  }

  public AppException(AppError errorMessage) {
    super(errorMessage.getCode() + ":" + errorMessage.getMsg());
    this.errorCode = errorMessage.getCode();
    this.errorMessage = errorMessage.getMsg();
  }

  public AppException(AppError errorMessage, Throwable throwable) {
    super(errorMessage.getCode() + ":" + errorMessage.getMsg(), throwable);
    this.errorCode = errorMessage.getCode();
    this.errorMessage = errorMessage.getMsg();

  }


  public Integer getErrorCode() {
    return errorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
