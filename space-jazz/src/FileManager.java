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
	StringEncryptor enc = new StringEncryptor();
	
	public FileManager()
	{

	}
	
	public void writeFile(Users users)
	{
		try
		{
			FileOutputStream usersFile = new FileOutputStream(System.getProperty("user.dir") + "\\users.bin");
			StringWriter sw = new StringWriter();
			byte[] encXML = null;
			JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(users, sw);
				
			enc.ObjectCrypter("1863189616648964");
			encXML = enc.Encrypt(sw.toString());
			System.out.println(sw.toString());
			
			usersFile.write(encXML);
			usersFile.close();
		}
		
		catch (JAXBException | IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public Users readFile()
	{
		try
		{
			File usersFile = new File(System.getProperty("user.dir") + "\\users.bin");
			FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "\\users.bin");
			byte[] encXML = new byte[(int) usersFile.length()];
			String unencXML;
			JAXBContext jaxbContent = JAXBContext.newInstance(Users.class);
			Unmarshaller jaxbUnmarshaller = jaxbContent.createUnmarshaller();
			
			inputStream.read(encXML);
			inputStream.close();
			unencXML = enc.Decrypt(encXML);
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