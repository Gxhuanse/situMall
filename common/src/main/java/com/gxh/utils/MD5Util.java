package com.gxh.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Base64.Encoder;

public class MD5Util {
	// MD5加密
	public static String getMD5(String str) {
		
		String newstr = null;
		try {
			//确定计算方法
			MessageDigest md5=MessageDigest.getInstance("MD5");
			//加密后的字符串
			newstr=encryptBASE64(md5.digest(str.getBytes(StandardCharsets.UTF_8)));
		} catch (Exception e) {
		}
		return newstr; 
	}	
	
	private static String encryptBASE64(byte[] data) {
		// BASE64Encoder encoder = new BASE64Encoder();
		// String encode = encoder.encode(data);
		// 从JKD 9开始rt.jar包已废除，从JDK 1.8开始使用java.util.Base64.Encoder
		Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(data);
	}

}
