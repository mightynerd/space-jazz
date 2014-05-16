//
//	Project space-jazz
//	Licensed under the General Public License v2
//
//	StringEncryption.java
//	Encrypt/Decrypt strings
//

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class StringEcnryption {	

	private Cipher deCipher;
	private Cipher enCipher;
	private SecretKeySpec key;
	private IvParameterSpec ivSpec;
	
	public void ObjectCrypter(String KeyString,   byte[] ivBytes) {
		byte[] keyBytes = null;
		try {
			keyBytes = convertToByteArray(KeyString);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    // wrap key data in Key/IV specs to pass to cipher


	     ivSpec = new IvParameterSpec(ivBytes);
	    // create the cipher with the algorithm you choose
	    // see javadoc for Cipher class for more info, e.g.
	    try {
	         DESKeySpec dkey = new  DESKeySpec(keyBytes);
	          key = new SecretKeySpec(dkey.getKey(), "DES");
	         deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
	         enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
	    } catch (NoSuchAlgorithmException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (NoSuchPaddingException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (InvalidKeyException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}
	public  byte[] encrypt(String obj) throws InvalidKeyException, InvalidAlgorithmParameterException, IOException, IllegalBlockSizeException, ShortBufferException, BadPaddingException {
	    byte[] input = convertToByteArray(obj);
	    enCipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);

	    return enCipher.doFinal(input);




	//  cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
	//  byte[] encypted = new byte[cipher.getOutputSize(input.length)];
	//  int enc_len = cipher.update(input, 0, input.length, encypted, 0);
	//  enc_len += cipher.doFinal(encypted, enc_len);
	//  return encypted;


	}
	public String decrypt( byte[]  encrypted) throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException, ClassNotFoundException {
	    deCipher.init(Cipher.DECRYPT_MODE, key, ivSpec);

	    return convertFromByteArray(deCipher.doFinal(encrypted));

	}



	private String convertFromByteArray(byte[] byteObject) throws IOException, ClassNotFoundException {
		String converted = new String(byteObject, "UTF-8");
		return converted;

	}



	private byte[] convertToByteArray(String complexObject) throws IOException {
	    byte[] converted = complexObject.getBytes(Charset.forName("UTF-8"));
	    return converted;

	}
	 
	
	
}
