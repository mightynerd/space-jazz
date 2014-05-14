//
//	Project space-jazz
//	Licensed under the General Public License v2
//
//	StringEncryption.java
//	Encrypt/Decrypt strings
//

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class StringEcnryption {	

	static String IV = "SWAGOVERLOADJAZZ";
	
	 public static String Encrypt(String Input, String EncryptionKey) {
		byte[] cipherByte = null;
		
		try{
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
			SecretKeySpec key = new SecretKeySpec(EncryptionKey.getBytes("UTF-8"), "AES");
			cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
			
			cipherByte = cipher.doFinal(Input.getBytes("UTF-8"));
			
		}catch(Exception ex){
			System.out.println(ex.toString());
		}
		
		return new String(cipherByte);	
		
	 }
	 
	 public static String Decrypt(String Input, String EncryptionKey) throws Exception{
		byte[] cipherInput = Input.getBytes("UTF-8");
				
		Cipher cipher = null;
		
		try{
			cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
			SecretKeySpec key = new SecretKeySpec(EncryptionKey.getBytes("UTF-8"), "AES");
			cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		return new String(cipher.doFinal(cipherInput),"UTF-8");
	}
	 
	
	
}
