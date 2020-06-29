package com.example.wkl_android.http;

public enum ResultCode {
    /**
     * 成功
     */
    LOGIN_SUCCESS("0x10000", "登录成功"),

    LOGOUT_ERROR("0x10001","退出错误"),


    /**
     * 未知错误
     */
    EXCEPTION_ERROR("0x9999", "服务器繁忙"),

    /**
     * 用户名错误或不存在
     */
    USERNAME_ERROR("0x10002", "用户名错误或不存在"),

    /**
     * 密码错误
     */
    PASSWORD_ERROR("0x10003", "密码错误"),

    /**
     * 用户名不能为空
     */
    USERNAME_EMPTY("0x10004", "用户名不能为空"),

    /**
     * sql异常
     */
    SQL_EXCEPTION("0x10005","sql异常"),

    /**
     * 空值针异常
     */
    NULL_POINTER_EXCEPTION("0x10006","空值针异常"),
    /**
     * IO异常
     */
    IO_Exception("0x10007","io异常"),

    /**
     * 权限不足
     */
    ERROR_PERMSSION_DENIRD("0x10008","权限不足"),
    /**
     * 用户冻结
     */
    USER_FREEZE("0x10009","用户冻结"),
    /**
     *保存成功
     */
    SAVE_SUCCESSFULLY("0x10010","保存成功"),
    /**
     *保存失败
     */
    SAVE_FAILED("0x10011","保存失败"),



    /**
     * 更新成功
     */
    UPDATE_SUCCESSFULLY("0x10012","更新成功"),
    /**
     * 更新失败
     */
    UPDATE_FAILED("0x10013","更新失败"),

    /**
     * 删除成功
     */
    DELETE_SUCCESSFULLY("0x10014","删除成功"),
    /**
     * 删除失败
     */
    DELETE_FAILED("0x10015","删除失败"),
    /**
     *非法登录
     */
    ILLEGAL_LOGON("0x10016","非法登录"),
    /**
     *非法参数
     */
    ILLEGAL_PARAMETER("0x10017","非法参数"),

    /***
     * 积分不足
     */
    INTEGRAL_NOTZULL("0x10018","积分不足"),


    /***
     * token过期
     */
    TOKEN_PASTDUE("0x10019","token认证过期"),

    /***
     * token错误
     */
    TOKEN_FAILED("0x10020","token认证错误"),

    /***
     * 积分获取失败
     */
    INTEGRAL_FAILED("0x10021","积分获取失败"),
    /***
     * 积分获取成功
     */
    INTEGRAL_SUCCESS("0x10022","积分获取成功"),

    /***
     * 添加失败，已存在
     */
    SAVE_FAILEDISEXIST("0x10023","保存失败,数据已存在"),

    /***
     * 数据不存在
     */
    DATA_ISNOTEXIST("0x10024","数据不存在"),


    /***
     * 数据存在
     */
    DATA_ISEXIST("0x10025","数据存在"),


    /***
     * 验证码过期
     */
    CODE_AUTHISNOTEXIST("0x10026","验证码过期"),

    /***
     * 验证码错误
     */
    CODE_AUTHISFAILED("0x10027","验证码错误"),

    /***
     * 手机号码错误
     */
    NUMBER_PHONEISFAILED("0x10028","手机号码错误"),

    /***
     * 手机号码验证通过
     */
    NUMBER_DURCHISSUCCESS("0x10029","手机号码验证通过"),

    /***
     * 手机号码验证失败
     */
    NUMBER_DURCHISFAILED("0x10030","手机号码验证失败"),

    /***
     * 手机号码可用
     */
    NUMBER_PHONEISNOTEXIST("0x10031","手机号码可用"),

    /***
     * 手机号码不可用
     */
    NUMBER_PHONEISEXIST("0x10032","手机号码不可用"),

    /***
     * 用户名未被注册
     */
    NAME_ISNOTEXIST("0x10033","用户名可用"),

    /***
     * 用户名已被注册
     */
    NAME_ISEXIST("0x10034","用户名不可用"),

    /***
     * 成功
     */
    SUCCESS("0x10035","成功"),

    /***
     * 失败
     */
    FAILED("0x10036","失败"),

    /***
     * 注册成功
     */
    USER_REGISTERSUCCESS("0x10037","注册成功"),

    /***
     * 注册失败
     */
    USER_REGISTERFAILED("0x10038","注册失败"),

    /***
     * 认证过期，请重新登录
     */
    TOKEN_REFRESHISNOTEXIST("0x10039","认证过期，请重新登录"),

    /**
     * 身份证号码验证错误
     */
    IDCARD_ISFAILED("0x10040","身份证号码验证错误"),



    ;
    /**
     * 结果码
     */
    private String code;

    /**
     * 结果码描述
     */
    private String msg;


    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

