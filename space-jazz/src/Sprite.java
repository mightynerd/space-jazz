import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;


public class Sprite {

	private List<ImageIcon> textures;
	private int currentTexture = 0;
	private int width;
	private int height;
	private Vector2D pos;
	private Vector2D velocity;
	private Vector2D direction;
	
	public Sprite(int startX, int startY)
	{
		textures = new ArrayList<ImageIcon>();
		pos = new Vector2D(startX, startY);
		velocity = new Vector2D(150.0f, 40.0f);
		direction = new Vector2D(1.0f, 1.0f);
	}
	
	public int GetWidth()
	{
		return width;
	}
	
	public int GetHeight()
	{
		return height;
	}
	
	public Image GetTexture()
	{
		return textures.get(currentTexture).getImage();
	}
	
	public void SetCurrentTextureIndex(int i)
	{
		currentTexture = i;
	}
	
	public int GetCurrentTextureIndex()
	{
		return currentTexture;
	}
	
	public Vector2D GetPosition()
	{
		return pos;
	}
	
	public void SetPosition(Vector2D pos)
	{
		this.pos = pos;
	}
	
	public Vector2D GetVelocity()
	{
		return this.velocity;
	}
	
	public void SetVelocity(Vector2D vel)
	{
		this.velocity = vel;
	}
	
	public Vector2D GetDirection()
	{
		return this.direction;
	}
	
	public void SetDirection(Vector2D dir)
	{
		this.direction = dir;
	}

	
	public void AddTexture(String path)
	{
		textures.add(new ImageIcon(path));
		width = textures.get(0).getIconWidth();
		width = textures.get(0).getIconHeight();
	}
	
	public void Update(float delta)
	{
		Vector2D newPos = pos.GetVector();
		Vector2D modPos = direction.GetVector();
		modPos.Multiply(velocity);
		modPos.Multiply(delta / 1000);
		
		newPos.Add(modPos);
		pos = newPos;
		
		//System.out.println("X: " + pos.X() + " Y: " + pos.Y() + " Delta: " + delta); 
		
	}
	
	public boolean Collides(Sprite sprite)
	{
		return false; //^^
	}
	
	public void Draw(Renderer renderer)
	{
		renderer.DrawSprite(this);
	}
	
}
