import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


public class MainMenu {

	//List to manage buttons
	private List<MenuButton> listButtons;
	private int selectedIndex = 0;
	
	//Sound effects/music
	SoundPlayer soundGameMusic;
	SoundPlayer soundMenuMusic;
	SoundPlayer soundMenuClick;
	
	StateManager stateManager;
	
	//Background
	Sprite backGround;
	Sprite logo;
	
	User currentUser;
	
	public MainMenu(StateManager stateMan, User user, SoundPlayer gameMusic)
	{
		this.stateManager = stateMan;
		currentUser = user;
		
		//Load sounds
		soundGameMusic = gameMusic;
		soundMenuMusic = new SoundPlayer("menu.wav");
		soundMenuClick = new SoundPlayer("menu-click.wav");
		
		//List of buttons
		listButtons = new ArrayList<MenuButton>();
		
		listButtons.add(new MenuButton(500, 200, "Play", 40));
		listButtons.get(0).SetTextPos(new Vector2D(75, 60));
		
		listButtons.add(new MenuButton(500, 300, "Store", 40));
		listButtons.get(1).SetTextPos(new Vector2D(62, 60));
		
		listButtons.add(new MenuButton(500, 400, "Scoreboard", 30));
		listButtons.get(2).SetTextPos(new Vector2D(32, 60));
		
		listButtons.add(new MenuButton(500, 500, "Exit", 40));
		
		//Play button should be active
		listButtons.get(0).SetActive(true);
		
		//Background sprite setup
		backGround = new Sprite(0, 0);
		backGround.AddTexture("Space pixel.png");
		
		//Logo sprite setup
		logo = new Sprite(290, 70);
		logo.AddTexture("Logo.png");
		
		//Play menu music
		soundMenuMusic.Play();
	}
	
	public void Update(InputManager inputManager)
	{
		//Loop music
		if (soundMenuMusic.IsPlaying() == false)
		{
			soundMenuMusic.Reset();
			soundMenuMusic.Play();
		}
		
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
			soundMenuClick.Stop();
			soundMenuClick.Reset();
			soundMenuClick.Play();
			
			//Action for each button
			if (listButtons.get(selectedIndex).GetText() == "Play")
			{
				soundMenuMusic.Stop();
				soundGameMusic.Play();
				
				//Reset if health equal or below 0
				if (currentUser.currentHealth <= 0)
				{
					//Reset user
					new FileManager().readFile().resetUser(currentUser);
				}
				
				stateManager.SetState(StateManager.State.Game);
			}
			else if (listButtons.get(selectedIndex).GetText() == "Store")
			{
				stateManager.SetState(StateManager.State.ShopMenu);
			}
			else if (listButtons.get(selectedIndex).GetText() == "Exit")
			{
				//Save stats before exit
				FileManager f = new FileManager();
				Users users = f.readFile();
				users.updateUser(currentUser);
				f.writeFile(users);
				
				System.exit(0);
			}
			else if (listButtons.get(selectedIndex).GetText() == "Scoreboard")
			{
				stateManager.SetState(StateManager.State.ScoreBoard);
			}
		}
		
		for (MenuButton menuButton : listButtons) {
			menuButton.Update(0);
		}
	}
	
	private void UpdateActive()
	{
		//Updates all the active property for all buttons
		for (MenuButton menuButton : listButtons) {
			menuButton.SetActive(false);
		}
		
		listButtons.get(selectedIndex).SetActive(true);
	}
	
	public void Draw(Renderer renderer)
	{
		backGround.Draw(renderer);
		logo.Draw(renderer);
		
		for (MenuButton menuButton : listButtons) {
			menuButton.Draw(renderer);
		}
	}
	
}
