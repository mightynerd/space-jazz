import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class FileManager
{
	public FileManager()
	{

	}
	
	public void writeFile(Users users)
	{
		try
		{
			File usersFile = new File(System.getProperty("user.dir") + "\\users.bin");
			JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			jaxbMarshaller.marshal(users, usersFile);
			jaxbMarshaller.marshal(users, System.out);
		}
		
		catch (JAXBException e)
		{
			System.out.println(e.toString());
		}
	}
	
	public Users readFile()
	{
		try
		{
			File usersFile = new File(System.getProperty("user.dir") + "\\users.bin");
			JAXBContext jaxbContent = JAXBContext.newInstance(Users.class);
			Unmarshaller jaxbUnmarshaller = jaxbContent.createUnmarshaller();
			Users users = (Users) jaxbUnmarshaller.unmarshal(usersFile);
			
			return users;
		}
		
		catch (JAXBException e)
		{
			System.out.println(e.toString());
			return null;
		}
	}
	
	public static void main(String[] args)
	{
		//User u1 = new User("Daniel", "Staples");
		//User u2 = new User("Stas", "Kollegieblock");
		//Users us = new Users();
		
		//us.users.add(u1);
		//us.users.add(u2);
		
		//FileManager f = new FileManager();
		//f.writeFile(us);
		
		FileManager f = new FileManager();
		f.readFile();
	}
}