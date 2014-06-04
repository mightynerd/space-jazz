import java.awt.Color;


public class GameOverMenu {

	private User user;
	MenuButton RestartButton;
	private StateManager stateManager;
	
	public GameOverMenu(StateManager stateMan, User user)
	{
		this.user = user;
		this.stateManager = stateMan;
		RestartButton = new MenuButton(100, 100, "Restart");
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
		renderer.DrawString("GameOver", 640, 250, Color.white, 40);
		renderer.DrawString("Points: " + user.points, 100, 60, Color.white, 30);
		renderer.DrawString("Player: " + user.userName, 100, 60, Color.white, 30);
		RestartButton.Draw(renderer);
	}

}
