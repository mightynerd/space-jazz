import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;



public class MainWindow extends JFrame implements Runnable{

	public static final int WIN_WIDTH = 1280;
	public static final int WIN_HEIGHT = 720;
	
	public static final StateManager.State DEFAULT_STATE = StateManager.State.MainMenu;
	
	//Used components
	private InputManager inputManager;
	private Thread gameThread;
	private Renderer renderer;
	private StateManager stateManager;
	
	//Framerate and timing
	long previousTime;
	long currentTime;
	int framerate = 0;
	long totalFrames = 0;
	
	//Current user
	User currentUser;
	
	//Game objects
	AsteroidManager astManager;
	Sprite backGround;
	SpriteBackOverlay backOverlay;
	SpriteBackOverlay backOverlay2;
	
	SpriteBackOverlay backStars1;
	SpriteBackOverlay backStars2;
	SpriteShip ship;
	
	MainMenu mainMenu;
	ShopMenu shopMenu;
	SpriteHud hud;
	
	public MainWindow(User user)
	{
		//Load font
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("content" + File.separator + "04B_03__.TTF")));
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		//-----
		
		this.setSize(WIN_WIDTH, WIN_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocation(40, 40);
		this.setTitle("space-jazz");
		
		inputManager = new InputManager();
		this.addKeyListener(inputManager);
		
		this.setVisible(true);
		
		renderer = new Renderer(WIN_WIDTH, WIN_HEIGHT);
		currentUser = user;
		
		//SwingUtilities.invokeLater(this);
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		//GAME LOOP
		
		Init();
		
		while(true)
		{
			totalFrames++;
			currentTime = System.nanoTime();
			
			//Hotfix for division by zero
			if (totalFrames > 5)
			{
				Update();
				Draw();
			}
			else
			{
				try
				{
					Thread.sleep(10);
				} catch (Exception ex) {}
			}
			
			try {
				//System.out.println("");
				//Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			previousTime = currentTime;
		}
	}
	
	public void Init()
	{		
		stateManager = new StateManager();
		stateManager.SetState(DEFAULT_STATE);
		
		mainMenu = new MainMenu(stateManager);
		shopMenu = new ShopMenu(stateManager, currentUser);
		
		astManager = new AsteroidManager(currentUser);
		
		backGround = new Sprite(0, 0);
		backGround.AddTexture("Space pixel.png");
		
		backOverlay = new SpriteBackOverlay(0, -720);
		backOverlay2 = new SpriteBackOverlay(0, -2160);
		backOverlay.AddTexture("nebula-repeat-v2.png");
		backOverlay2.AddTexture("nebula-repeat-v2.png");
		
		backStars1 = new SpriteBackOverlay(0, -720);
		backStars2 = new SpriteBackOverlay(0, -2160);
		backStars1.AddTexture("Transparant-star.png");
		backStars2.AddTexture("Transparant-star.png");
		backStars1.SetVelocity(new Vector2D(0f, 100f));
		backStars2.SetVelocity(new Vector2D(0f, 100f));
		
		ship = new SpriteShip(500, 600, stateManager, currentUser);
		hud = new SpriteHud(0, 0, currentUser);
		
	}
	
	public void Update()
	{
		//Regardless of state:
		float delta = (currentTime - previousTime) / 1000000;
		framerate = (int) (1000 / ((currentTime - previousTime) / 1000000));
		StateManager.State currentState = stateManager.GetState();
		//----
		
		//State specific:
		if (currentState == StateManager.State.Game)
		{
			ship.Update(delta, inputManager, astManager.GetAsteroidList());
			backOverlay.Update(delta);
			backOverlay2.Update(delta);
			
			backStars1.Update(delta);
			backStars2.Update(delta);
			
			astManager.Update(delta);	
			
			if (inputManager.IsKeyPressed(inputManager.DEFAULT_BACK))
			{
				stateManager.SetState(StateManager.State.MainMenu);
			}
		}
		else if (currentState == StateManager.State.MainMenu)
		{
			mainMenu.Update(inputManager);
		}
		else if (currentState == StateManager.State.ShopMenu)
		{
			shopMenu.Update(inputManager);
		}
	}
	
	public void Draw()
	{
		//Clear backbuffer:
		renderer.Clear();
		
		StateManager.State currentState = stateManager.GetState();
		
		//State specific:
		if (currentState == StateManager.State.Game)
		{
			//Draw Game
			backGround.Draw(renderer);
			
			backStars1.Draw(renderer);
			backStars2.Draw(renderer);
			
			ship.Draw(renderer);
			
			astManager.Draw(renderer);
			
			backOverlay.Draw(renderer);
			backOverlay2.Draw(renderer);
			
			hud.Draw(renderer);
		}
		else if (currentState == StateManager.State.MainMenu)
		{
			mainMenu.Draw(renderer);
		}
		else if (currentState == StateManager.State.ShopMenu)
		{
			shopMenu.Draw(renderer);
		}
		
		
		renderer.DrawString("Framerate: " + framerate, 20, 700, Color.GREEN, 20);
		//--- End draw
		
		//Draw backbuffer
		this.getGraphics().drawImage(renderer.GetBackBuffer(), 0, 0, this);
	}
}
