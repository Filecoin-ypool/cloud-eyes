package com.eyes.cloud.config;

/**
 * @description: 通用数据
 * @author: fugq
 * @create: 2019-02-12 16:43
 **/
public interface CommonValue {
    /**
     * 后台Api前缀
     */
    String ADMIN_PREFIX = "/admin";
    /**
     * APP端Api前缀
     */
    String APP_PREFIX = "/app/v1";

    /**
     * 日期正则
     */
    String DATE_REG = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";

    /**
     * 前台Api前缀
     */
    String API_PREFIX = "/api/v1";

    /**
     * 正则：手机号（精确）
     * <p>移动：134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188、198</p>
     * <p>联通：130、131、132、145、155、156、175、176、185、186、166</p>
     * <p>电信：133、153、173、177、180、181、189、199</p>
     * <p>全球星：1349</p>
     * <p>虚拟运营商：170</p>
     */
    String REGEX_MOBILE_EXACT = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
    /**
     * 身份证正则（15位或18位）
     */
    String REGEX_ID_CARD_EXACT = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
    /**
     * APP用户注册短信过期时间（秒）
     */
    long APP_USER_REGISTER_SMS_EXPIRE = 30 * 60;
    /**
     * APP用户重置密码短信过期时间（秒）
     */
    long APP_USER_RESET_PASSWORD_SMS_EXPIRE = 30 * 60;
    /**
     * APP用户换绑手机号过期时间（秒）
     */
    long APP_USER_BING_MOBILE_SMS_EXPIRE = 30 * 60;

    /**
     * APP用户注册短信验证允许最大错误次数
     */
    String APP_USER_REGISTER_SMS_CHECK_TIME = "3";
    /**
     * APP用户重置密码短信验证允许最大错误次数
     */
    String APP_USER_RESET_PASSWORD_SMS_CHECK_TIME = "3";
    /**
     * APP用户换绑手机号短信验证允许最大错误次数
     */
    String APP_USER_BING_MOBILE_SMS_CHECK_TIME = "3";


    /**
     * APP用户登陆过期时间（天）
     */
    long APP_USER_LOGIN_EXPIRE = 7;

    /**
     * 是否实名认证
     */
    class IsRealName {
        public static final byte OK = 1;
        public static final byte WAIT = 2;
    }

    /**
     * 身份证识别token距过期时间间隔（ms）
     */
    long ID_IDENTIFY_EXPIRE_INTERVAL = 24 * 60 * 60 * 1000L;

    /**
     * APP用户最大绑卡数量
     */
    int MAX_BING_BANK_CARD_NUM = 5;

    /**
     * 获取HIS排班记录列表地址
     */
    String HIS_SCHEDULE_LIST_URL = "";

}
