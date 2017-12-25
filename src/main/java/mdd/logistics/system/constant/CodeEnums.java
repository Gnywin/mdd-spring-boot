package mdd.logistics.system.constant;

/**
 * Created by 猫大东 on 2017/4/19.
 * 常用返回码
 * 300-399 用户权限类型错误
 * 500-599 系统内部错误
 */
public enum CodeEnums {
    SYSTEM_ERROR(500, "系统繁忙"),
    INVALID_REQUEST(400, "无效的请求"),
    USER_IS_NOT_COMPLIANCE(301, "用户不合法"),//300-329 错误均视为token无效
    TOKEN_IS_NOT_EXIST(302, "token不存在"),
    NEED_TOKEN(303, "需要token"),
    TOKEN_IS_INVALID(304, "无效的token"),
    USER_NAME_ERROR(305, "无效的登录名"),
    REGISTER_WAS_FAILED(330, "注册失败"),
    USER_PASSWORD_ERROR(331, "用户名或密码错误"),
    EMAIL_IS_ERROR(332, "邮箱地址不合法"),
    VERIFY_IS_ERROR(333, "验证码错误"),
    LOGIN_FAIL(334, "登入失败"),
    ACCESS_FLOW_LIMITED(600, "访问流量限制"),
    ACCESS_FLOW_NEED_VERIFY(601, "访问需要校验"),
    ESSAY_NEED_TITLE(700, "吐槽需要标题"),
    ESSAY_NEED_CONTENT(801, "吐槽需要内容");

    private int code;

    private String msg;

    CodeEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    // 获得 enum 对象
    public static CodeEnums get(int code) {
        for (CodeEnums item : values()) {
            if (code == item.getCode()) {
                return item;
            }
        }
        return null;
    }

}
