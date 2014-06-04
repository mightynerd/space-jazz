import java.awt.Color;
import java.awt.Font;


public class MenuButton extends Sprite {

	private boolean selected = false;
	private String text;
	private int size;
	private Vector2D textPos = new Vector2D(80, 60);
	
	public MenuButton(int x, int y, String text, int size)
	{
		super(x, y);
		SetVelocity(new Vector2D(0, 0));
		SetDirection(new Vector2D(0, 0));
		this.text = text;
		this.size = size;
		AddTexture("menu-button-deactive2.png");
		AddTexture("menu-button-active.png");
	}
	
	public String GetText()
	{
		return text;
	}
	
	public void SetTextPos(Vector2D textPos)
	{
		this.textPos = textPos;
	}

	public void SetActive(boolean active)
	{
		selected = active;
	}
	
	@Override
	public void Update(float delta) {
		
		if (selected == true)
		{
			SetCurrentTextureIndex(1);
		}
		else
		{
			SetCurrentTextureIndex(0);
		}
		
		super.Update(delta);
	}

	@Override
	public void Draw(Renderer renderer) {
		super.Draw(renderer);
		renderer.DrawString(text, new Font("04b03", 10, size), 
				new Vector2D(GetPosition().X() + textPos.X(), GetPosition().Y() + textPos.Y()), Color.WHITE);
	}
	
}
