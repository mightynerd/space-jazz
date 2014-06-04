import java.awt.Color;


public class GameOverMenu {

	private User user;
	MenuButton RestartButton;
	private StateManager stateManager;
	Sprite backGround;
	
	public GameOverMenu(StateManager stateMan, User user)
	{
		this.user = user;
		this.stateManager = stateMan;
		RestartButton = new MenuButton(540, 500, "Restart", 30);
		RestartButton.SetActive(true);
		RestartButton.SetTextPos(new Vector2D(60, 60));
		
		backGround = new Sprite(0, 0);
		backGround.AddTexture("Space pixel.png");
	}
	
	public void Update(InputManager inputManager)
	{
		if (inputManager.IsKeyPressed(InputManager.Key.SpaceBar) | inputManager.IsKeyPressed(InputManager.DEFAULT_ACCEPT))
		{
			stateManager.SetState(StateManager.State.MainMenu);
		}
	}
	
	public void Draw(Renderer renderer)
	{
		backGround.Draw(renderer);
		
		renderer.DrawString("GameOver", 485, 235, Color.red, 70);
		renderer.DrawString("Points: " + user.points, 540, 330, Color.white, 30);
		renderer.DrawString("Player: " + user.userName, 540, 370, Color.white, 30);
		RestartButton.Draw(renderer);
	}

}
