import java.awt.Color;

import javax.swing.JFrame;


public class MainWindow extends JFrame implements Runnable{

	public static final int WIN_WIDTH = 1280;
	public static final int WIN_HEIGHT = 720;
	
	private InputManager inputManager;
	private RenderPanel renderPanel;
	private Thread gameThread;
	public MainWindow()
	{
		this.setSize(WIN_WIDTH, WIN_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocation(40, 40);
		
		inputManager = new InputManager();
		this.addKeyListener(inputManager);
		
		renderPanel = new RenderPanel(WIN_WIDTH, WIN_HEIGHT);
		this.add(renderPanel);
		
		this.setVisible(true);
		
		gameThread = new Thread(this);
		gameThread.start();
		renderPanel.Clear();
	}

	@Override
	public void run() {
		//GAME LOOP
		while(true)
		{
			renderPanel.repaint();
			renderPanel.Clear();
		}
	}
}
