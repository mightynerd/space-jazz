import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlRootElement
public class Users
{
	public Users()
	{
		
	}

	//The list of users
	@XmlElement(name = "user")
	public List<User> users = new ArrayList<User>();
	
	//Checks if a user exists
	Boolean userExists(String name)
	{
		//Loops through all users in the list
		for (User user : users)
		{
			if (user.userName.equals(name))
			{
				//If the name exists it returns true
				return true;
			}
		}
		return false;
	}
	
	//Checks if the user exists and if it has the correct password
	Boolean correctPassword(String name, String pass)
	{
		for (User user : users)
		{
			if (user.userName.equals(name))
			{
				//When the name is correct it checks if the password is correct
				if (user.password.equals(String.valueOf(pass)))
				{
					return true;
				}
				return false;
			}
		}
		return false;
	}
	
	User getUser(String name)
	{
		for (User user : users)
		{
			if (user.userName == name)
			{
				return user;
			}
		}
		
		return new User();
	}
}
