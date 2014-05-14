import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class User
{
	public String userName;
	public String password;
	public int points;
	
	public User()
	{
		points = 0;
	}
	
	public User(String name, String passwd)
	{
		points = 0;
		userName = name;
		password = passwd;
	}
}