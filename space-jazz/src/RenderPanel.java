import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class RenderPanel extends JPanel{

	Graphics g;
	private final Color CLEAR_COLOR = Color.BLACK;
	
	public RenderPanel(int width, int height)
	{
		//this.setIgnoreRepaint(true);
		this.setSize(width, height);
		this.setVisible(true);
		g = this.getGraphics();
	}

	public void DrawSprite()
	{
		g.fillRect(20, 20, 20, 20);
	}

	
	public void Clear()
	{
		if (g == null)
		{
			return;
		}
		//super.paint(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	public void DrawString()
	{
		
	}
	
}
