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
			listButtons.add(new MenuButton(100, 150, "Upgrade weapon", 22));
			listButtons.get(0).SetTextPos(new Vector2D(25, 54));
			
			listButtons.add(new MenuButton(100, 250, "Upgrade armor", 22));
			listButtons.get(1).SetTextPos(new Vector2D(32, 54));
			
			listButtons.add(new MenuButton(100, 350, "Buy health", 22));
			listButtons.get(2).SetTextPos(new Vector2D(55, 54));
			
			listButtons.add(new MenuButton(100, 450, "Back", 30));
			
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
				if (listButtons.get(selectedIndex).GetText() == "Back")
				{
					stateManager.SetState(StateManager.State.MainMenu);
				}
				else if (listButtons.get(selectedIndex).GetText() == "Upgrade weapon")
				{
					if (user.money >= StatTrack.STORE_WEAPON_COST)
					{
						user.money -= StatTrack.STORE_ARMOR_COST;
						user.weaponLevel++;
					}
				}
				else if (listButtons.get(selectedIndex).GetText() == "Upgrade armor")
				{
					if (user.money >= StatTrack.STORE_ARMOR_COST)
					{
						user.money -= StatTrack.STORE_ARMOR_COST;
						user.armorLevel++;
					}
				}
				else if (listButtons.get(selectedIndex).GetText() == "Buy health")
				{
					if (user.money >= StatTrack.STORE_HEALTH_COST)
					{
						user.money -= StatTrack.STORE_HEALTH_COST;
						user.currentHealth += 25;
					}
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
			
			renderer.DrawString("" + StatTrack.STORE_WEAPON_COST, 350, 210, Color.white, 30);
			renderer.DrawString("" + StatTrack.STORE_ARMOR_COST, 350, 310, Color.white, 30);
			renderer.DrawString("" + StatTrack.STORE_HEALTH_COST, 350, 410, Color.white, 30);
			
			//Draw stats
			renderer.DrawString("Weapon level: " + user.weaponLevel, 600, 150, Color.WHITE, 30);
			renderer.DrawString("Armor level: " + user.armorLevel, 600, 180, Color.WHITE, 30);
			renderer.DrawString("Current health: " + user.currentHealth, 600, 210, Color.RED, 30);
			renderer.DrawString("Money: " + user.money, 600, 250, Color.YELLOW, 30);
			
			for (MenuButton menuButton : listButtons) {
				menuButton.Draw(renderer);
			}
		}
}
