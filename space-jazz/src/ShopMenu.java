import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


public class ShopMenu {
	//List to manage buttons
		private List<MenuButton> listButtons;
		private int selectedIndex = 0;
		
		StateManager stateManager;
		private User user;
		
		//Background
		Sprite backGround;
		
		public ShopMenu(StateManager stateMan, User user)
		{
			this.stateManager = stateMan;
			this.user = user;
			
			listButtons = new ArrayList<MenuButton>();
			listButtons.add(new MenuButton(100, 100, "Upgrade weapon"));
			listButtons.add(new MenuButton(100, 200, "Upgrade armor"));
			listButtons.add(new MenuButton(100, 300, "Buy health"));
			listButtons.add(new MenuButton(100, 400, "Back"));
			
			listButtons.get(0).SetActive(true);
			
			backGround = new Sprite(0, 0);
			backGround.AddTexture("Space pixel.png");
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
					stateManager.SetState(StateManager.State.Game);
				}
				else if (listButtons.get(selectedIndex).GetText() == "Exit")
				{
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
			
			renderer.DrawString("Player: " + user.userName, 20, 60, Color.WHITE, 30); 
			
			for (MenuButton menuButton : listButtons) {
				menuButton.Draw(renderer);
			}
		}
}