package com.eyes.cloud.interceptor;

import com.eyes.cloud.common.enums.VerifyCodeTypeEnum;
import lombok.Data;

/**
 * 换成key
 *
 * @author liul
 */
@Data
public class CacheKey {
    /**
     * 验证码key失效时间（秒）
     * 5分钟
     */
    public static final long VERIFY_CODE_KEY_EXPIRE = 5 * 60L;

    /**
     * 验证码长度
     */
    public static final int VERIFY_CODE_LENGTH = 6;
    /**
     * 验证码最大错误次数
     * 3次
     */
    public static final int VERIFY_CODE_ERROR_TIMES = 3;
    /**
     * 用户登录后最大未操作时间（秒）
     * 24小时
     */
    public static final long NOT_OPERATED_EXPIRE = 24 * 60 * 60L;

    /**
     * 验证码key
     *
     * @param phone
     * @return
     */
    public static String getVerifyCodeKey(Long phone, VerifyCodeTypeEnum typeEnum) {
        return "verify_code_" + typeEnum.name() + "_" + phone;
    }

    /**
     * web登录token的key
     */
    public static String getWebLoginTokenKey(String token, long phone) {
        return "web_login_" + phone + "_" + token;
    }

    /**
     * admin登录token的key
     */
    public static String getAdminLoginTokenKey(String token, String userName) {
        return "admin_login_" + userName + "_" + token;
    }

    /**
     * web登录token格式
     */
    public static String getWebLoginTokenKeyPatternByPhone(long phone) {
        return "web_login_" + phone + "_*";
    }

    /**
     * admin登录token格式
     */
    public static String getAdminLoginTokenKeyPatternByPhone(String userName) {
        return "admin_login_" + userName + "_*";
    }

    /**
     * web登录token格式
     */
    public static String getWebLoginTokenKeyPatternByToken(String token) {
        return "web_login_*_" + token;
    }

    /**
     * admin登录token格式
     */
    public static String getAminLoginTokenKeyPatternByToken(String token) {
        return "admin_login_*_" + token;
    }

    public static String getVerifyCodeValue(String code) {
        return code + "_" + VERIFY_CODE_ERROR_TIMES;
    }

    public static String getSecretKey(int uid) {
        return "secret_ket_" + uid;
    }

    public static String getBlockNum(int schedule) {
        return "block_num_eos_" + schedule;
    }

    public static String seeAdvertiseSignal(String adCode, int uid) {
        return "see_advertise_signal_" + uid + "_" + adCode;
    }

    public static String seeAdvertiseSignalLimit(String adCode, int uid, String date) {
        return "see_advertise_signal_limit_" + uid + "_" + adCode + "_" + date;
    }

    public static String getDownloadSignal(String signal) {
        return "app_download_signal_" + signal;
    }

    /**
     * 获取有积分广告列表
     *
     * @param date
     */
    public static String getRichAdvertiseListKey(String date) {
        return "advertise_rich_list_" + date;
    }

    /**
     * 获取没积分广告列表
     *
     * @param date
     */
    public static String getPoorAdvertiseListKey(String date) {
        return "advertise_poor_list_" + date;
    }

    /**
     * 看广告剩余次数Key
     */
    public static String getSeeAdvertiseRestTimesKey(int aid, String date) {
        return "see_advertise_rest_times_" + date + "_" + aid;
    }

    /**
     * 更新看广告数据锁
     */
    public static String updateSeeAdvertiseListLockKey() {
        return "update_see_advertise_list_lock";
    }

    /**
     * 剩余有积分广告数量
     */
    public static String getRichAdvertiseCountKey(String date) {
        return "advertise_rich_count_" + date;
    }

    /**
     * 看有积分广告记录
     */
    public static String seeRichAdvertiseRecordKey(int uid, String date) {
        return "see_rich_advertise_record_" + date + "_" + uid;
    }

    /**
     * 获取区块交易锁
     *
     * @param schedule
     */
    public static String getTransactionInfoLockKey(int schedule) {
        return "transaction_info_lock_" + schedule;
    }

    public static String getWalletAddressSetKey() {
        return "wallet_address_set";
    }
}
