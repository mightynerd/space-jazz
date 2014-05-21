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
		velocity = new Vector2D(150.0f, 40.0f);
		direction = new Vector2D(1.0f, 1.0f);
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
		Vector2D newPos = pos.GetVector();
		Vector2D modPos = direction.GetVector();
		modPos.Multiply(velocity);
		modPos.Multiply(delta / 1000);
		
		newPos.Add(modPos);
		pos = newPos;
		
		System.out.println("X: " + pos.X() + " Y: " + pos.Y() + " Delta: " + delta); 
		
	}
	
	public void Draw(Renderer renderer)
	{
		renderer.DrawSprite(this);
	}
	
}
