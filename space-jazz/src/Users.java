import java.util.ArrayList;
import java.util.Collections;
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
	boolean userExists(String name)
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
	boolean correctPassword(String name, String pass)
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
			System.out.println(user.userName + name);
			if (user.userName.equals(name))
			{
				return user;
			}
		}
		
		return new User("Invalid user", "invalid");
	}
	
	void updateUser(User user)
	{
		int i = 0;
		
		for (User currentUser : users)
		{
			if (user.userName.equals(currentUser.userName))
			{
				users.set(i, user);
			}
			
			i++;
		}
	}
	
	void sort()
	{
		Collections.sort(users);
		
		for (User user : users)
		{
			System.out.println(user.points);
		}
	}
}
