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

	@XmlElement(name = "user")
	public List<User> users = new ArrayList<User>();
	
	Boolean userExists(String name)
	{
		for (User user : users)
		{
			if (user.userName.equals(name))
			{
				return true;
			}
		}
		return false;
	}
	
	Boolean correctPassword(String name, String pass)
	{
		for (User user : users)
		{
			if (user.userName.equals(name))
			{
				if (user.password.equals(String.valueOf(pass)))
				{
					return true;
				}
				return false;
			}
		}
		return false;
	}
}
