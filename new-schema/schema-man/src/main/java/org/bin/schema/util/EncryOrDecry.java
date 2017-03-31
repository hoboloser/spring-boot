package org.bin.schema.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密  解密
 * @author Administrator
 *
 */
public class EncryOrDecry {

	/**
	 * MD5加密
	 * @param value
	 * @return
	 */
	public static String md5Encry(String value){
		try {  
            MessageDigest digest = MessageDigest.getInstance("md5");  
            byte[] result = digest.digest(value.getBytes());  
            StringBuffer buffer = new StringBuffer();  
            for (byte b : result) {  
                int number = b & 0xff;// 加盐  
                String str = Integer.toHexString(number);  
                if (str.length() == 1) {  
                    buffer.append("0");  
                }  
                buffer.append(str);  
            }  
  
            return buffer.toString();  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
            return "";  
        }  
	}
}
