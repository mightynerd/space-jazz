import java.util.ArrayList;
import java.util.List;


public class MainMenu {

	//List to manage buttons
	private List<MenuButton> listButtons;
	private int selectedIndex = 0;
	
	SoundPlayer soundGameMusic;
	SoundPlayer soundMenuMusic;
	
	StateManager stateManager;
	
	//Background
	Sprite backGround;
	
	User currentUser;
	
	public MainMenu(StateManager stateMan, User user)
	{
		this.stateManager = stateMan;
		currentUser = user;
		
		soundGameMusic = new SoundPlayer("game.wav");
		soundMenuMusic = new SoundPlayer("menu.wav");
		
		listButtons = new ArrayList<MenuButton>();
		listButtons.add(new MenuButton(100, 100, "Play"));
		listButtons.add(new MenuButton(100, 200, "Store"));
		listButtons.add(new MenuButton(100, 300, "Scoreboard"));
		listButtons.add(new MenuButton(100, 400, "Exit"));
		
		listButtons.get(0).SetActive(true);
		
		backGround = new Sprite(0, 0);
		backGround.AddTexture("Space pixel.png");
		
		soundMenuMusic.Play();
	}
	
	public void Update(InputManager inputManager)
	{
		//Move selected down
		if (inputManager.IsKeyPressed(InputManager.Key.ArrowDown) | inputManager.IsKeyPressed(InputManager.DEFAULT_DOWN))
		{
			if (selectedIndex < listButtons.size() - 1)
			{
				selectedIndex++;
				UpdateActive();
			}
		}
		//Move selected up
		else if (inputManager.IsKeyPressed(InputManager.Key.ArrowUp) | inputManager.IsKeyPressed(InputManager.DEFAULT_UP))
		{
			if (selectedIndex > 0)
			{
				selectedIndex--;
				UpdateActive();
			}
		}
		//Accept button
		else if (inputManager.IsKeyPressed(InputManager.Key.SpaceBar) | inputManager.IsKeyPressed(InputManager.DEFAULT_ACCEPT))
		{
			//Action for each button
			if (listButtons.get(selectedIndex).GetText() == "Play")
			{
				soundMenuMusic.Stop();
				soundGameMusic.Play();
				stateManager.SetState(StateManager.State.Game);
			}
			else if (listButtons.get(selectedIndex).GetText() == "Store")
			{
				stateManager.SetState(StateManager.State.ShopMenu);
			}
			else if (listButtons.get(selectedIndex).GetText() == "Exit")
			{
				FileManager f = new FileManager();
				
				Users users = f.readFile();
				users.updateUser(currentUser);
				f.writeFile(users);
				System.exit(0);
			}
		}
		
		for (MenuButton menuButton : listButtons) {
			menuButton.Update(0);
		}
	}
	
	private void UpdateActive()
	{
		for (MenuButton menuButton : listButtons) {
			menuButton.SetActive(false);
		}
		
		listButtons.get(selectedIndex).SetActive(true);
	}
	
	public void Draw(Renderer renderer)
	{
		backGround.Draw(renderer);
		
		for (MenuButton menuButton : listButtons) {
			menuButton.Draw(renderer);
		}
	}
	
}
