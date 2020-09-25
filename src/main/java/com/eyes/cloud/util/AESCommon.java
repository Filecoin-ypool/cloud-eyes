package com.eyes.cloud.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Formatter;

/**
 * @author dyk
 * @version V1.0.0
 * @description
 * @date 2020/7/28
 * @since 1.0
 */
public class AESCommon {
    //可配置到Constant中，并读取配置文件注入,16位,自己定义
    private static final String KEY = "xxxxxxxxxxxxxxxx";

    //参数分别代表 算法名称/加密模式/数据填充方式
    private static final String ALGORITHMSTR = "AES/CBC/PKCS5Padding";
//    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";
//    private static final String ALGORITHMSTR = "AES/CBC/PKCS1OAEPPadding";

    static int fromDigit(char ch) {
        if ((ch >= '0') && (ch <= '9')) {
            return ch - '0';
        } else if ((ch >= 'A') && (ch <= 'F')) {
            return ch + 10 - 'A';
        } else if ((ch >= 'a') && (ch <= 'f')) {
            return ch + 10 - 'a';
        } else {
            throw new IllegalArgumentException(String.format("Invalid hex character 0x%04x", 0xff & ch));
        }
    }

    static byte[] hexFromString(String hex) {
        final byte[] buf = new byte[hex.length() / 2];
        for (int i = 0, j = 0; i < hex.length(); i += 2) {
            buf[j++] = (byte) (fromDigit(hex.charAt(i)) << 4 | fromDigit(hex.charAt(i + 1)));
        }
        return buf;
    }

    static String asHex(byte buf[]) {
        final Formatter formatter = new Formatter(new StringBuffer());
        for (int i = 0; i < buf.length; i++) {
            formatter.format("%02x", 0xff & buf[i]);
        }
        return formatter.toString();
    }

    public static AlgorithmParameterSpec getIV() {
        byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,};
        IvParameterSpec ivParameterSpec;
        ivParameterSpec = new IvParameterSpec("0000000000000000".getBytes());
        return ivParameterSpec;
    }

    /**
     * 加密
     *
     * @param content    加密的字符串
     * @param encryptKey key值
     * @return
     * @throws Exception
     */
    public static String encrypt(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        AlgorithmParameterSpec algorithmParameterSpec = getIV();
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"), algorithmParameterSpec);
        byte[] b = cipher.doFinal(content.getBytes("utf-8"));
        // 采用base64算法进行转码,避免出现中文乱码
        return Base64.encodeBase64String(b);

    }

    /**
     * 解密
     *
     * @param encryptStr 解密的字符串
     * @param decryptKey 解密的key值
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptStr, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        AlgorithmParameterSpec algorithmParameterSpec = getIV();
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"), algorithmParameterSpec);
        // 采用base64算法进行转码,避免出现中文乱码
        byte[] encryptBytes = Base64.decodeBase64(encryptStr);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }

    public static String encrypt(String content) throws Exception {
        return encrypt(content, KEY);
    }

    public static String decrypt(String encryptStr) throws Exception {
        return decrypt(encryptStr, KEY);
    }


    public static void main(String[] args) throws Exception {
        String decrypt1 = encrypt("123456", "1234567890123456");
        String decrypt2 = decrypt(decrypt1, "1234567890123456");
        String decrypt3 = decrypt(decrypt1, "1234567890123456");
//        Map map = new HashMap<String, String>();
//        map.put("key", "value");
//        map.put("中文", "汉字");
//        String content = JSONObject.toJSONString(map);
//        System.out.println("加密前：" + content);
//
//        String encrypt = encrypt(content, KEY);
//        System.out.println("加密后：" + encrypt);
//
//        String decrypt = decrypt(encrypt, KEY);
//        System.out.println("解密后：" + decrypt);
    }
}
