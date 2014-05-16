import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class MainWindow extends JFrame implements Runnable{

	public static final int WIN_WIDTH = 1280;
	public static final int WIN_HEIGHT = 720;
	
	private InputManager inputManager;
	private Thread gameThread;
	
	BufferedImage backBuffer;
	
	long previousTime;
	long currentTime;
	int framerate = 0;
	
	public MainWindow()
	{
		this.setSize(WIN_WIDTH, WIN_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocation(40, 40);
		
		inputManager = new InputManager();
		this.addKeyListener(inputManager);
		
		
		this.setVisible(true);
		
		backBuffer = new BufferedImage(WIN_WIDTH, WIN_HEIGHT, BufferedImage.TYPE_INT_RGB);
		
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
	
	public void Update()
	{
		framerate = (int) (1000 / ((currentTime - previousTime) / 1000000));
	}
	
	public void Draw()
	{
		Graphics g = this.getGraphics();
		Graphics b = backBuffer.getGraphics();
		
		b.setColor(Color.BLACK);
		b.fillRect(0, 0, WIN_WIDTH, WIN_HEIGHT);
		
		//Draw objects:
		b.setColor(Color.GREEN);
		b.fillOval(50, 50, 300, 300);
		
		b.drawString("hej" + framerate, 50, 700);
		
		//End draw
		g.drawImage(backBuffer, 0, 0, this);
	}
}
