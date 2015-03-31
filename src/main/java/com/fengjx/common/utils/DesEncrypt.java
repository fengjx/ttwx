
package com.fengjx.common.utils;

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
 * @author
 */
public class DesEncrypt {

    private Charset UTF8 = Charset.forName("UTF-8");

    private Key key;

    public DesEncrypt(String str) {
        setKey(str);// 生成密匙
    }

    public DesEncrypt() {
        setKey("fjx123!@");
    }

    /**
     * 根据参数生成KEY
     */
    public void setKey(String strKey) {
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            this.key = keyFactory.generateSecret(new DESKeySpec(strKey.getBytes(UTF8)));
        } catch (Exception e) {
            throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
        }
    }

    /**
     * 加密String明文输入,String密文输出
     */
    public String encrypt(String strMing) throws Exception {
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
    public String decrypt(String strMi) throws Exception {
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
