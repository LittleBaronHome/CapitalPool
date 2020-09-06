package com.ihq.capitalpool.ssoserver.Util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtil {
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 获取密钥对
     *
     * @return 密钥对
     */
    private static KeyPair getKeyPair() {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);
            return generator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("获取密钥对失败：" + e.getMessage());
        }
    }

    /**
     * 获取私钥
     *
     * @param privateKey 私钥字符串
     */
    public static PrivateKey getPrivateKey(String privateKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] decodedKey = Base64.decodeBase64(privateKey.getBytes());
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
            return keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            throw new RuntimeException("获取密钥对失败：" + e.getMessage());
        }
    }

    /**
     * 获取公钥
     *
     * @param publicKey 公钥字符串
     */
    public static PublicKey getPublicKey(String publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] decodedKey = Base64.decodeBase64(publicKey.getBytes());
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
            return keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            throw new RuntimeException("获取公钥失败：" + e.getMessage());
        }
    }

    /**
     * RSA加密
     *
     * @param data 待加密数据
     * @param publicKey 公钥
     */
    public static String encrypt(String data, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            int inputLen = data.getBytes().length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offset = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段加密
            while (inputLen - offset > 0) {
                if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(data.getBytes(), offset, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(data.getBytes(), offset, inputLen - offset);
                }
                out.write(cache, 0, cache.length);
                i++;
                offset = i * MAX_ENCRYPT_BLOCK;
            }
            byte[] encryptedData = out.toByteArray();
            out.close();
            // 获取加密内容使用base64进行编码,并以UTF-8为标准转化成字符串
            // 加密后的字符串
            return Base64.encodeBase64String(encryptedData);
        } catch (IOException | NoSuchPaddingException | InvalidKeyException
                | BadPaddingException | NoSuchAlgorithmException | IllegalBlockSizeException e) {
            e.printStackTrace();
            throw new RuntimeException("RSA加密失败：" + e.getMessage());
        }
    }

    /**
     * RSA解密
     *
     * @param data 待解密数据
     * @param privateKey 私钥
     */
    public static String decrypt(String data, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] dataBytes = Base64.decodeBase64(data);
            int inputLen = dataBytes.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offset = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段解密
            while (inputLen - offset > 0) {
                if (inputLen - offset > MAX_DECRYPT_BLOCK) {
                    cache = cipher.doFinal(dataBytes, offset, MAX_DECRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(dataBytes, offset, inputLen - offset);
                }
                out.write(cache, 0, cache.length);
                i++;
                offset = i * MAX_DECRYPT_BLOCK;
            }
            byte[] decryptedData = out.toByteArray();
            out.close();
            // 解密后的内容
            return new String(decryptedData, StandardCharsets.UTF_8);
        } catch (IOException | NoSuchPaddingException | InvalidKeyException | BadPaddingException
                | NoSuchAlgorithmException | IllegalBlockSizeException e) {
            e.printStackTrace();
            throw new RuntimeException("RSA解密失败：" + e.getMessage());
        }
    }

    /**
     * 签名
     *
     * @param data 待签名数据
     * @param privateKey 私钥
     * @return 签名
     */
    public static String sign(String data, PrivateKey privateKey){
        try {
            byte[] keyBytes = privateKey.getEncoded();
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey key;
            key = keyFactory.generatePrivate(keySpec);
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initSign(key);
            signature.update(data.getBytes());
            return new String(Base64.encodeBase64(signature.sign()));
        } catch (NoSuchAlgorithmException | SignatureException | InvalidKeySpecException | InvalidKeyException e) {
            e.printStackTrace();
            throw new RuntimeException("签名失败：" + e.getMessage());
        }
    }

    /**
     * 验签
     *
     * @param srcData 原始字符串
     * @param publicKey 公钥
     * @param sign 签名
     * @return 是否验签通过
     */
    public static boolean verify(String srcData, PublicKey publicKey, String sign) {
        try {
            byte[] keyBytes = publicKey.getEncoded();
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey key = keyFactory.generatePublic(keySpec);
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initVerify(key);
            signature.update(srcData.getBytes());
            return signature.verify(Base64.decodeBase64(sign.getBytes()));
        } catch (NoSuchAlgorithmException | SignatureException | InvalidKeySpecException | InvalidKeyException e) {
            e.printStackTrace();
            throw new RuntimeException("验签失败：" + e.getMessage());
        }
    }

//    public static void main(String[] args) {
////        KeyPair keyPair = getKeyPair();
////        System.out.println(new BASE64Encoder().encode(keyPair.getPublic().getEncoded()));
////        System.out.println(new BASE64Encoder().encode(keyPair.getPrivate().getEncoded()));
//
//        String s = encrypt("hello", getPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCcE59RIcup3vlcZbT3e97F/2CVsjS24bAbJGdb75W9OzxLtMwwA3cEb83nXKcIpjcSTqtjpND0V2epmIE8J/Ne411+9I6SEoxVa5t6zi14qwE1mVu4TfgOC5GVCltDzSc9mkx/rT6DEbZFnBd+VMVEUBkbmlftnYk3VTwqvIijMQIDAQAB"));
//        System.out.println(decrypt(s, getPrivateKey("MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJwTn1Ehy6ne+VxltPd73sX/YJWyNLbhsBskZ1vvlb07PEu0zDADdwRvzedcpwimNxJOq2Ok0PRXZ6mYgTwn817jXX70jpISjFVrm3rOLXirATWZW7hN+A4LkZUKW0PNJz2aTH+tPoMRtkWcF35UxURQGRuaV+2diTdVPCq8iKMxAgMBAAECgYAGb+y/POSYggDI5cz79BjxE6JZKjtwW4iW5f55VT1Yy6ul97QAOvRas33LkA+3lJtq3uHEv1R672vwG8EgyQkJjOG4WtVwSJaON+IePdQkQmRmAhUhb6lxFsRIwhH0CW8n0/dpX3iBEoTxwA6Z5z6a39WpKQW1CK3mLABd8k3cwQJBAP4kw86fDPMVk5KLbGHMAxcaXIejUom/f+D5HtMlpjFdwSFAYe3sX/vIGwW+6M4ErSmQ/nsttqadMlECZkAAWMkCQQCdN3o4O/CFYvszSgG8wLqSCjs8zM5ANRMeb/iY4eHbgiUjmLpQPPcjYKUL8So8KvAiyY7+H7JVo0v2oZL8B5MpAkA8SAot42oV6qNDWlDN7a859qXGAoZcaBD0EwMtwDtocD2UHopb/fpSZYeLWFouK8vLFTpXi2NMp/K8Zl5MhgiJAkB8fQ9kM+IyXLw4UqOpiJqzRTK+5BV1NhGbimI4/LPREu7BjxAItR2kf8Rw/lfJKN7ZTOzTQNn/gbyG4E35Dy5xAkB8NkYR3x9E8kpucccoymxy7PtzjHdYk8u4rV5Fgm1hpdluYlOzvF+YnV1YSubyEGJ1FkHEtFX/olGgPVwFoSln")));
//
//    }


}
