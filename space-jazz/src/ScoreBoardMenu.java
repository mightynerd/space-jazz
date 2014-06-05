import java.awt.Color;


public class ScoreBoardMenu {

	private Users users;
	private StateManager stateManager;
	
	private static final Color COLOR_GOLD = new Color(255, 223, 0);
	private static final Color COLOR_SILVER = new Color(131, 137, 150);
	private static final Color COLOR_BRONZE = new Color(205, 127, 50);
	private static final Color COLOR_DEFAULT = new Color(50, 50, 50);
	
	private MenuButton btnBack;
	private Sprite backGround;
	
	public ScoreBoardMenu(User user, StateManager stateManager)
	{
		this.stateManager = stateManager;
		
		btnBack = new MenuButton(520, 500, "Back", 30);
		btnBack.SetActive(true);
		
		FileManager fm = new FileManager();
		users = fm.readFile();
		users.sort();
		
		backGround = new Sprite(0, 0);
		backGround.AddTexture("Space pixel.png");
		
	}
	
	public void Update(InputManager inputManager)
	{
		btnBack.Update(1000);
		
		if (inputManager.IsKeyPressed(InputManager.Key.SpaceBar) || inputManager.IsKeyPressed(inputManager.DEFAULT_ACCEPT))
		{
			stateManager.SetState(StateManager.State.MainMenu);
		}
	}
	
	public void Draw(Renderer renderer)
	{
		backGround.Draw(renderer);
		btnBack.Draw(renderer);
		renderer.DrawString("Score board", 520, 100, Color.WHITE, 40);
		
		int i = 0;
		for (User user : users.users) {
			if (i < 5 && user.userName.equals("Admin") == false)
			{
				i++;
				if (i == 1)
				{
					renderer.DrawString(i + ". " + user.userName + " - " + user.points, 530, 170 + (i * 50), COLOR_GOLD, 35);
				}
				else if (i == 2)
				{
					renderer.DrawString(i + ". " + user.userName + " - " + user.points, 530, 170 + (i * 50), COLOR_SILVER, 30);
				}
				else if (i == 3)
				{
					renderer.DrawString(i + ". " + user.userName + " - " + user.points, 530, 170 + (i * 50), COLOR_BRONZE, 30);
				}
				else
				{
					renderer.DrawString(i + ". " + user.userName + " - " + user.points, 530, 170 + (i * 50), COLOR_DEFAULT, 30);
				}
			}
		}
	}
	
}
