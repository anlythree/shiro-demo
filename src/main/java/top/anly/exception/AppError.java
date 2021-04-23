package top.anly.exception;

/**
 * 应用全局错误码及提示枚举类
 *
 * @author zhenhua.huo
 * @date 2019/4/23 下午1:37
 */
public enum AppError {
    /**
     * 错误码 错误提示
     */
    OK(0, "成功"),
    INVALID_PARAMS(1001, "非法参数"),
    SIGN_ERROR(1002, "签名错误"),
    FREQUENTLY_REQUEST(1003, "操作频繁"),
    FAILED(1004, "操作失败"),
    NOLOGIN(7777, "没有登录信息"),
    AUTHORITY(8888, "没有访问权限"),
    ERROR(9999, "系统异常"),
    SAVE_FAILED(1004, "保存失败"),
    ERROR_PARAMS(1005, "入参错误"),
    FILE_FORMAT_ERROR(1006, "文件格式错误"),
    IMPORT_ERROR(1007, "导入失败"),
    FEIGN_ERROR(1008, "服务调用异常"),
    SERVICE_DOWN(9997, "服务器开小差了，请稍后再试"),
    NOT_KNOW_INPUT(1009, "不识别的输入"),
    FILED(9999, "操作失败"),
    EXIST_CUSTOMER(1010, "存在相同的客户"),
    NOT_EXIST_CUSTOMER(1011, "找不到客户"),
    ALREADY_EXIST_GOODS(1012, "已经存在的货物"),
    NO_EXIST_MATERIAL(1013, "没有查到该商品信息"),
    UNIT_ERROR(1014,"单价输入矛盾"),
    TEMPLATE_ALREADY_EXIST(1015,"模板名已被占用"),
    ERROR_TEMPLATE_TYPE(1016,"错误的模板类型"),
    FOUND_NO_TEMPLATE(1017,"找不到该模板的信息"),
    EXIST_PROBLEMTYPE(1018,"问题类型已存在"),
    EXIST_SOLVETYPE(1019,"处理方式类型已存在"),
    MODIFIED_FAILED(1020,"修改失败"),
    EXIST_DELETED(1021,"删除失败, 引用此问题类型的问题单未处理完成"),
    FOUND_NO_TEMPLATE_TYPE(1022,"找不到该模板类型"),
    PROBLEM_STATUS(1023,"问题单状态异常"),
    PROBLEMCOST_STATUS(1024,"成本单状态异常"),
    CONTRACT_ALREADY_EXIST(1025,"合同已存在"),
    PLEASE_CHOOSE_DATE(1026,"请选择要操作的数据"),
    CONTRACT_GOODS_INFO_NOT_FOUND(1027,"请填写相应的合同商品信息"),
    CONTRACT_PAY_INFO_NOT_FOUND(1028,"请填写相应的合同付款信息"),
    CHANCE_ALREADY_EXIST(1029,"商机已存在"),
    DATA_EXIST(2001, "数据已存在"),
    ERROR_DELETE(1030,"删除失败"),
    SEND_APPROVE_FAILED(1033,"发起审批失败"),
    REVOCATION_DELETE(1031,"撤回失败"),
    EXIST_SAME_DATA(1032, "存在相同订单、物料代码且未发起审批的发货申请"),
    RESPONSIBLE_CUSTOMER_CATEGORY_NOT_EXIST(2002, "负责客户类别不存在"),
    CONTRACT_CHANGE_ALREADY_EXIST(2003,"合同修改单已存在"),
    MATERIAL_UNREASONABLE(2004,"商品价格不在合理区间"),
    SHIPMENT_NO_ALREADY_EXIST(2005,"发货单号已存在"),
    UNAUTHORIZED_OPERATION(2006,"无权操作"),
    NOT_EXIST_USER(2007,"此角色下没有用户"),
    DATA_NOT_EXISTS(2008, "数据不存在"),
    UPDATE_FAILED(2009,"修改失败"),
    QUERY_FAILED(2010,"查询失败"),
    ;

    private Integer code;
    private String msg;

    AppError(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return code + ":" + msg;
    }

    public Integer getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }

}
