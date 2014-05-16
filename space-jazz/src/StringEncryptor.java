//
//	Project space-jazz
//	Licensed under the General Public License v2
//
//	StringEncryptor.java
//	Encrypt/Decrypt strings
//

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class StringEncryptor {

	private Cipher deCipher;
	private Cipher enCipher;
	private SecretKeySpec key;
	private IvParameterSpec ivSpec;

	public void ObjectCrypter(String KeyString) {
		byte[] ivBytes = ByteBuffer.allocate(8)
				.putLong((long) KeyString.hashCode()).array();

		byte[] keyBytes = null;
		keyBytes = ConvertToByteArray(KeyString);

		ivSpec = new IvParameterSpec(ivBytes);

		try {
			DESKeySpec dkey = new DESKeySpec(keyBytes);
			key = new SecretKeySpec(dkey.getKey(), "DES");
			deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		} catch (NoSuchPaddingException e) {

			e.printStackTrace();
		} catch (InvalidKeyException e) {

			e.printStackTrace();
		}
	}

	public byte[] Encrypt(String obj) {
		byte[] ret = null;
		try {

			byte[] input = ConvertToByteArray(obj);
			enCipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
			ret = enCipher.doFinal(input);

		} catch (Exception ex) {
			System.out.println("Encryption Error!");
		}

		return ret;

	}

	public String Decrypt(byte[] encrypted) {
		String ret = "";
		try {

			deCipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
			ret = ConvertFromByteArray(deCipher.doFinal(encrypted));

		} catch (Exception ex) {

		}
		return ret;

	}

	private String ConvertFromByteArray(byte[] byteObject) {
		String converted = "";
		try {
			converted = new String(byteObject, "UTF-8");
		} catch (Exception ex) {

		}
		return converted;

	}

	private byte[] ConvertToByteArray(String complexObject) {
		byte[] converted = null;
		try {
			converted = complexObject.getBytes(Charset.forName("UTF-8"));
		} catch (Exception ex) {

		}
		
		return converted;

	}

}
