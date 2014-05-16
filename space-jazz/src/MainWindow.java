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
	
	//Framerate and timing
	long previousTime;
	long currentTime;
	int framerate = 0;
	
	//Game objects
	Sprite test;
	
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
			currentTime = System.nanoTime();
			
			Update();
			Draw();
			
			try {
				System.out.println("");
				//Thread.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			previousTime = currentTime;
		}
	}
	
	public void Init()
	{
		test = new Sprite(20, 20);
		test.LoadTexture("content\\eclipse.png");
	}
	
	public void Update()
	{
		framerate = (int) (1000 / ((currentTime - previousTime) / 1000000));
		test.Update(currentTime - previousTime);
	}
	
	public void Draw()
	{
		//Clear backbuffer:
		renderer.Clear();
		
		//Draw objects:
		renderer.DrawString("Framerate: " + framerate, 20, 700, Color.GREEN);
		
		test.Draw(renderer);
		
		//--- End draw
		
		//Draw backbuffer
		this.getGraphics().drawImage(renderer.GetBackBuffer(), 0, 0, this);
	}
}
