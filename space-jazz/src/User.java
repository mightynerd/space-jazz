import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class User implements Comparable<User>
{
	public String userName;
	public String password;
	public int points;
	public int money;
	public int weaponLevel;
	public int armorLevel;
	public int currentHealth;
	
	public User()
	{
		points = 0;
		money = 0;
		weaponLevel = 1;
		armorLevel = 1;
		currentHealth = getMaxHealth();
	}
	
	public User(String name, String passwd)
	{
		points = 0;
		money = 0;
		weaponLevel = 1;
		armorLevel = 1;
		currentHealth = getMaxHealth();
		userName = name;
		password = passwd;
	}
	
	public int getMaxHealth()
	{
		return armorLevel * StatTrack.ARMOR_MULTIPLIER;
	}
	
	public int getWeaponDamage()
	{
		return weaponLevel * StatTrack.WEAPON_MULTIPLIER;
	}
	
	public void addPoint(int point)
	{
		points += point;
		money += point;
	}

	@Override
	public int compareTo(User u) {
		// TODO Auto-generated method stub
		return u.points - points;
	}
}