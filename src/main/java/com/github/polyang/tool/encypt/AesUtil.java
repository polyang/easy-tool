package com.github.polyang.tool.encypt;

import com.github.polyang.tool.hex.HexUtil;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @Author polyang
 * @Description AES加密工具类
 * @Date 2020/3/28 20:55
 **/
public class AesUtil {

	/**
	 * aes加密
	 * @param content	待加密的字符串
	 * @param aesKey	aes秘钥
	 * @return	加密后的字符串
	 */
	public static String encrypt(String content, String aesKey) {
		try {
			//创建密码器
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			byte[] byteContent = content.getBytes("UTF-8");
			cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(aesKey));
			//加密
			byte[] result = cipher.doFinal(byteContent);
			//通过Base64转码返回
			return HexUtil.byte2ToHexStr(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * aes解密
	 * @param content	待解密的字符串
	 * @param aesKey	aes秘钥
	 * @return	解密后的字符串
	 */
	public static String decrypt(String content, String aesKey) {
		try {
			//实例化
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(aesKey));
            //执行操作
            byte[] result = cipher.doFinal(HexUtil.hexStrToByte2(content));
            return new String(result, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
    /**
     * 生成加密秘钥
     * @param password	密码
     * @return 秘钥
     * @throws NoSuchAlgorithmException
     */
    private static SecretKeySpec getSecretKey(String password) throws NoSuchAlgorithmException {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;
        //以下两行是为了解决linux系统下加解密报错
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(password.getBytes());
        try {
            kg = KeyGenerator.getInstance("AES");
            //AES要求密钥长度为 128
            kg.init(128, random);
            //生成一个密钥
            SecretKey secretKey = kg.generateKey();
            //转换为AES专用密钥
            return new SecretKeySpec(secretKey.getEncoded(), "AES");
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }
	
}
