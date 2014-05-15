import java.awt.Image;
import javax.swing.ImageIcon;


public class Sprite {

	ImageIcon texture;
	Vector2D pos;
	Vector2D velocity;
	Vector2D direction;
	
	public Sprite(int startX, int startY)
	{
		pos = new Vector2D(startX, startY);
		velocity = new Vector2D(5, 5);
		direction = new Vector2D(1, 0);
	}
	
	public Image GetTexture()
	{
		return texture.getImage();
	}
	
	public Vector2D GetPosition()
	{
		return pos;
	}
	
	public void LoadTexture(String path)
	{
		texture = new ImageIcon(path);
	}
	
	public void Update(float delta)
	{
		Vector2D newPos = pos;
		newPos.Multiply(direction);
		newPos.Multiply(velocity);
		newPos.Multiply(delta);
		
		pos.Add(newPos);
		
		System.out.println("X: " + pos.X() + " Y: " + pos.Y()); 
		
	}
	
	public void Draw(Renderer renderer)
	{
		renderer.DrawSprite(this);
	}
	
}
