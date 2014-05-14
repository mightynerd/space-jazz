import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

//
//	Project space-jazz
//	Licenced under the Genereal Public Licence v2
//
//	InputManager.java
//	Registers keyboard input for supported keys, supports multiple keys at once.
//


public class StringEcnryption {
	public void Ecnrypt(String Input, String Pass) {
		byte[] InputByte = Input.getBytes(Charset.forName("UTF-8"));
		//Hash to encrypt with from specified password
		int PassHash = Pass.hashCode();
		byte[] CryptoByte = ByteBuffer.allocate(4).putInt(PassHash).array();
		//User specified password
		byte[] KeyByte = Pass.getBytes(Charset.forName("UTF-8"));
		
		SecretKeySpec key = new SecretKeySpec(KeyByte, "DES");
		IvParameterSpec ivSpec = new IvParameterSpec(CryptoByte);
		
	}
}
