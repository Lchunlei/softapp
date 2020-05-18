package com.chun.lei.utils;

import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Created by lcl on 2020/4/28 0028
 */
public class TokenUtil {

    private static final String KEY_ALGORITHM = "AES";
    private static final String CIPHER_ALGORITHM_ECB = "AES/CBC/PKCS5Padding";
    private static final String Key = "lclmv";
    private static final String IV = "d039b7a926c3b289";

    private static SimpleDateFormat format = new SimpleDateFormat("MM-dd");

    private static Cipher global_cipher;
    private static SecretKeySpec global_secretKey;

    static {
        try {
            global_secretKey=new SecretKeySpec(getMD5Byte(Key), KEY_ALGORITHM);
            global_cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //oYmsa0VexWjPUPA_k1qy5JiXHWAg
    //根据token获取id
    public static Integer getUidByToken(String content){
        String nowStr = format.format(new Date());
        try {
            String str = decrypt(content);
            if(str!=null&&str.startsWith(nowStr)){
                String uidStr = str.split("_")[1];
                return Integer.parseInt(uidStr);
            }else {
                return null;
            }
        }catch (Exception e){
            return null;
        }
    }


    //获取用户标识
    public static String getToken(Integer uId){
        String nowStr = format.format(new Date());
        String str = nowStr+"_"+uId;
        return encrypt(str);
    }

    /**
     * 使用AES 算法 加密
     */
    private static synchronized  String encrypt(String content) {
        String result = null;
        if (StringTool.isBlank(content)) {
            return null;
        }
        try {
            IvParameterSpec iv = new IvParameterSpec(IV.getBytes("UTF-8"));
            global_cipher.init(Cipher.ENCRYPT_MODE, global_secretKey,iv);//
            // 密钥
            byte[] encrypt = global_cipher.doFinal(content.getBytes("UTF-8"));
            result = Base64.encodeBase64String(encrypt);
        } catch (Exception e) {

        }

        return result;
    }

    private static byte[] getMD5Byte(final String data) {
        try {
            final MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(data.getBytes());
            return digest.digest();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //解密
    private static synchronized String decrypt(String content) {
        String result = null;
        if (StringTool.isBlank(content)) {
            return null;
        }
        try {
            IvParameterSpec iv = new IvParameterSpec(IV.getBytes("UTF-8"));
            global_cipher.init(Cipher.DECRYPT_MODE, global_secretKey, iv);//
            // 密钥
            byte[] decrypt = global_cipher.doFinal(Base64.decodeBase64(content));
            result = new String(decrypt,"UTF-8");
        } catch (Exception e) {

        }
        return result;
    }
}
