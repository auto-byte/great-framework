package com.great.common.utils;


import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Slf4j
public class AESUtils {

    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/GCM/NoPadding";//默认的加密算法

    private static final byte[] IV = new byte[]{
            (byte) 0, (byte) 1, (byte) 2, (byte) 0,
            (byte) 1, (byte) 2, (byte) 0, (byte) 1,
            (byte) 2, (byte) 0, (byte) 1, (byte) 2,
            (byte) 0, (byte) 1, (byte) 2, (byte) 0
    };

    /**
     * AES 加密操作
     *
     * @param content  待加密内容
     * @param password 加密密码
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String password) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器

            byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);

            GCMParameterSpec ivSpec = new GCMParameterSpec(128, IV);
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password), ivSpec);// 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent);// 加密

            return new String(Base64.getEncoder().encode(result));//通过Base64转码返回
        } catch (Exception e) {
            log.error("AES加密失败", e);
        }

        return null;
    }

    /**
     * AES 解密操作
     *
     * @param content  待加密内容
     * @param password 加密密码
     * @return 解密的内容
     */
    public static String decrypt(String content, String password) {
        try {
            // 实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            GCMParameterSpec ivSpec = new GCMParameterSpec(128, IV);
            // 使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password), ivSpec);

            // 执行操作
            byte[] result = cipher.doFinal(Base64.getDecoder().decode(content));

            return new String(result, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("AES解密失败", e);
        }

        return null;
    }

    /**
     * 生成加密秘钥
     */
    private static SecretKeySpec getSecretKey(final String password) {
        // 返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;

        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);

            // AES 要求密钥长度为 128
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());
            kg.init(128, random);

            // 生成一个密钥
            SecretKey secretKey = kg.generateKey();

            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM); // 转换为AES专用密钥
        } catch (NoSuchAlgorithmException e) {
            log.error("生成AES密钥错误", e);
        }

        return null;
    }
}
