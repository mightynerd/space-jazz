import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class MainWindow extends JFrame implements Runnable{

	public static final int WIN_WIDTH = 1280;
	public static final int WIN_HEIGHT = 720;
	
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
	
	//Game objects
	AsteroidManager astManager;
	Sprite backGround;
	SpriteBackOverlay backOverlay;
	SpriteBackOverlay backOverlay2;
	
	SpriteBackOverlay backStars1;
	SpriteBackOverlay backStars2;
	SpriteShip ship;
	
	public MainWindow()
	{
		this.setSize(WIN_WIDTH, WIN_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocation(40, 40);
		
		inputManager = new InputManager();
		this.addKeyListener(inputManager);
		
		this.setVisible(true);
		
		renderer = new Renderer(WIN_WIDTH, WIN_HEIGHT);
		
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
		stateManager.SetState(StateManager.State.Game);
		
		astManager = new AsteroidManager();
		
		backGround = new Sprite(0, 0);
		backGround.AddTexture("content\\Space pixel.png");
		
		backOverlay = new SpriteBackOverlay(0, -720);
		backOverlay2 = new SpriteBackOverlay(0, -2160);
		backOverlay.AddTexture("content\\nebula-repeat-v2.png");
		backOverlay2.AddTexture("content\\nebula-repeat-v2.png");
		
		backStars1 = new SpriteBackOverlay(0, -720);
		backStars2 = new SpriteBackOverlay(0, -2160);
		backStars1.AddTexture("content\\Transparant-star.png");
		backStars2.AddTexture("content\\Transparant-star.png");
		backStars1.SetVelocity(new Vector2D(0f, 100f));
		backStars2.SetVelocity(new Vector2D(0f, 100f));
		
		ship = new SpriteShip(500, 600);
		
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
			ship.Update(delta, inputManager);
			backOverlay.Update(delta);
			backOverlay2.Update(delta);
			
			backStars1.Update(delta);
			backStars2.Update(delta);
			
			astManager.Update(delta);	
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
		}
		
		
		renderer.DrawString("Framerate: " + framerate, 20, 700, Color.GREEN);
		//--- End draw
		
		//Draw backbuffer
		this.getGraphics().drawImage(renderer.GetBackBuffer(), 0, 0, this);
	}
}
