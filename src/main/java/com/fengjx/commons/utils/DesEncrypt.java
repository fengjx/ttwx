
package com.fengjx.commons.utils;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.Charset;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * des加密解密
 * 
 * @author fengjx
 */
public class DesEncrypt {

    private static Charset UTF8 = Charset.forName("UTF-8");

    private static Key key;

    static{
        setKey("fjx123!@");
    }

    /**
     * 根据参数生成KEY
     */
    private static void setKey(String strKey) {
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            key = keyFactory.generateSecret(new DESKeySpec(strKey.getBytes(UTF8)));
        } catch (Exception e) {
            throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
        }
    }

    /**
     * 加密String明文输入,String密文输出
     */
    public static String encrypt(String strMing) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key, SecureRandom.getInstance("SHA1PRNG"));
        byte[] byteMi = cipher.doFinal(strMing.getBytes(UTF8));
        return new String(Base64.encodeBase64(byteMi));
    }

    /**
     * 解密 以String密文输入,String明文输出
     *
     * @param strMi
     */
    public static String decrypt(String strMi) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, key, SecureRandom.getInstance("SHA1PRNG"));
        byte[] byteMing = cipher
                .doFinal(Base64.decodeBase64(strMi.getBytes()));
        if (null == byteMing) {
            throw new RuntimeException("decrypt error");
        }
        return new String(byteMing, UTF8);
    }

}
