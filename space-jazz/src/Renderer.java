import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Observer;


public class Renderer {

	BufferedImage backBuffer;
	Graphics g;
	
	private final Color CLEAR_COLOR = Color.BLACK;
	private final Font DEFAULT_FONT = new Font("04b03", 10, 30);
	
	public Renderer(int width, int height)
	{
		backBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		g = backBuffer.getGraphics();
	}
	
	public void Clear()
	{
		g.setColor(CLEAR_COLOR);
		g.fillRect(0, 0, backBuffer.getWidth(), backBuffer.getHeight());
	}
	
	public void DrawSprite(Sprite sprite)
	{
		g.setColor(Color.WHITE);
		if (sprite.HasTexture())
		{
			g.drawImage(sprite.GetTexture(), sprite.GetPosition().IntX(), sprite.GetPosition().IntY(), null);
		}
	}
	
	public void DrawString(String text, Font font, Vector2D pos, Color color)
	{
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, pos.IntX(), pos.IntY());
	}
	
	public void DrawString(String text, Vector2D pos, Color color)
	{
		DrawString(text, DEFAULT_FONT, pos, color);
	}
	
	public void DrawString(String text, int x, int y, Color color)
	{
		DrawString(text, DEFAULT_FONT, new Vector2D(x, y), color);
	}
	
	public void DrawString(String text, int x, int y, Color color, int size)
	{
		DrawString(text, new Font(DEFAULT_FONT.getName(), 10, size), new Vector2D(x, y), color);
	}
	
	public BufferedImage GetBackBuffer()
	{
		return backBuffer;
	}
	
	
	
}
