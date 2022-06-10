package prv.jarkchen.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "服务器异常"),

    LOG_MESSAGE_NULL_ERROR(000001, "日志存储失败，没有日志信息！"),
    LOG_SOURCE_NULL_ERROR(000002, "日志存储失败，未知日志来源！"),

    USER_ACOUNT_NOT_INPUT(000010, "请输入账号！"),
    USER_PWD_NOT_INPUT(000011, "请输入密码！"),
    USER_REGIST_SUCCESS(000012, "注册成功！"),
    USER_NOT_EXIST(000013, "用户不存在！"),
    USER_ACOUNT_ALREADY_EXIST(000014, "用户账号已存在！"),
    USER_REGIST_ERROR(000015, "用户注册失败！"),
    USER_PASSWORD_ERROR(000016, "用户密码错误！"),
    USER_SALT_NOT_INPUT(000017, "请输入盐！"),
    USER_LOGIN_SUCCESS(000020, "登陆成功！"),
    
    ARGUMENTS_NOT_EXISTS(000030, "必要参数不能为空！"),
    TOKEN_NOT_CORRECT(000032, "密令不正确！"),
    QUERY_SUCCESS(000033, "查询成功！"),
    ;

    private final Integer code;
    private final String message;
}
