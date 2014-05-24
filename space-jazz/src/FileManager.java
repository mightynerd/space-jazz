import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class FileManager
{
	//Our own encryptor class
	StringEncryptor enc = new StringEncryptor();
	
	public FileManager()
	{

	}
	
	//Saves the file encrypted. Encryption is so that you shouldn't be able to give yourself points by editing the file.
	public void writeFile(Users users)
	{
		try
		{
			//The file that is going to be written to
			FileOutputStream usersFile = new FileOutputStream(System.getProperty("user.dir") + "\\users.bin");
			StringWriter sw = new StringWriter();
			byte[] encXML = null;
			
			//For the XML-writer
			JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			//Writes XML from the users-class to the stringwriter
			jaxbMarshaller.marshal(users, sw);
				
			//Encryption-password. Should not be hardcoded fot maximum security, but we don't need that much sequrity
			enc.ObjectCrypter("1863189616648964");
			//Encrypts the XML
			encXML = enc.Encrypt(sw.toString());
			//Debug
			System.out.println(sw.toString());
			
			//Writes the encrypted XML to the file and closes the stream
			usersFile.write(encXML);
			usersFile.close();
		}
		
		catch (JAXBException | IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	//Decrypts the file and returns a list of users
	public Users readFile()
	{
		try
		{
			//The file that will be read
			File usersFile = new File(System.getProperty("user.dir") + "\\users.bin");
			
			//If the file doesn't exist it creates it with a debug-user.
			if (!usersFile.exists())
			{
				Users users = new Users();
				User user = new User("Admin", "StaplesKollegie");
				user.points = 1000;
				users.users.add(user);
				writeFile(users);
			}
			
			//A fielreader
			FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "\\users.bin");
			//A byte array with the size of the file
			byte[] encXML = new byte[(int) usersFile.length()];
			String unencXML;
			
			//For the XML reader
			JAXBContext jaxbContent = JAXBContext.newInstance(Users.class);
			Unmarshaller jaxbUnmarshaller = jaxbContent.createUnmarshaller();
			
			//Encryption pasword
			enc.ObjectCrypter("1863189616648964");
			//Reads the encrypted file to the byte array and closes it
			inputStream.read(encXML);
			inputStream.close();
			
			//Decrypts the XML
			unencXML = enc.Decrypt(encXML);
			
			//Reads the string and creates a users-class
			StringReader sr = new StringReader(unencXML);
			Users users = (Users) jaxbUnmarshaller.unmarshal(sr);

			return users;
		}
		
		catch (JAXBException | IOException e)
		{
			System.out.println(e.toString());
			return null;
		}
	}
	
	//Debug
	public static void main(String[] args)
	{
		User u1 = new User("Daniel", "Staples");
		User u2 = new User("Stas", "Kollegieblock");
		Users us = new Users();
		
		us.users.add(u1);
		us.users.add(u2);
		
		FileManager f = new FileManager();
		f.writeFile(us);
		
		
		us = f.readFile();
		System.out.println(us.users.get(0).userName);
	}
}