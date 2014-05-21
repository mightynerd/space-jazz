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
	Sprite test;
	Sprite backGround;
	Sprite backOverlay;
	Sprite backOverlay2;
	
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
		
		Init();
		
		//SwingUtilities.invokeLater(this);
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		//GAME LOOP
		while(true)
		{
			totalFrames++;
			currentTime = System.nanoTime();
			
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
				System.out.println("");
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
		
		backGround = new Sprite(0, 0);
		backGround.LoadTexture("content\\Space pixel.png");
		
		backOverlay = new Sprite(0, 0);
		backOverlay.LoadTexture("content\\nebula-repeat-v1.png");
		backOverlay2 = new Sprite(2560, 0);
		backOverlay2.LoadTexture("content\\nebula-repeat-v1.png");
		backOverlay2.SetPosition(new Vector2D(2560, 0));
		
		test = new Sprite(20, 20);
		test.LoadTexture("content\\spaceship-v1.png");
	}
	
	public void Update()
	{
		//Regardless of state:
		float delta = (currentTime - previousTime) / 1000000;
		framerate = (int) (1000 / ((currentTime - previousTime) / 1000000));
		StateManager.State currentState = stateManager.GetState();
		
		//State specific:
		if (currentState == StateManager.State.Game)
		{
			test.Update(delta);
			
			//Background
			backOverlay.SetDirection(new Vector2D(-1f, 0f));
			backOverlay.SetVelocity(new Vector2D(500f, 0f));
			
			backOverlay2.SetDirection(new Vector2D(-1f, 0f));
			backOverlay2.SetVelocity(new Vector2D(500f, 0f));
			
			if (backOverlay.GetPosition().X() < -2560)
			{
				backOverlay.SetPosition(new Vector2D(2560, 0));
			}
			
			if (backOverlay2.GetPosition().X() < -2560)
			{
				backOverlay2.SetPosition(new Vector2D(2560, 0));
			}
			
			backOverlay.Update(delta);
			backOverlay2.Update(delta);
			
			System.out.println("BACK1: (" + backOverlay.GetPosition().X() + ", " + backOverlay.GetPosition().Y());
			System.out.println("BACK1: (" + backOverlay2.GetPosition().X() + ", " + backOverlay2.GetPosition().Y());
		}
	}
	
	public void Draw()
	{
		//Clear backbuffer:
		renderer.Clear();
		
		StateManager.State currentState = stateManager.GetState();
		
		if (currentState == StateManager.State.Game)
		{
			//Draw Game
			backGround.Draw(renderer);
			
			backOverlay.Draw(renderer);
			backOverlay2.Draw(renderer);
			
			test.Draw(renderer);
		}
		
		
		renderer.DrawString("Framerate: " + framerate, 20, 700, Color.GREEN);
		//--- End draw
		
		//Draw backbuffer
		this.getGraphics().drawImage(renderer.GetBackBuffer(), 0, 0, this);
	}
}
