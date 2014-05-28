import java.awt.Color;
import java.awt.Font;


public class MenuButton extends Sprite {

	private boolean selected;
	private String text;
	
	public MenuButton(int x, int y, String text)
	{
		super(x, y);
		SetVelocity(new Vector2D(0, 0));
		SetDirection(new Vector2D(0, 0));
		this.text = text;
		AddTexture("menu-button-deactive.png");
		AddTexture("menu-button-active.png");
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
		renderer.DrawString(text, new Font("04b03", 10, 40), 
				new Vector2D(GetPosition().X() + 80, GetPosition().Y() + 60), Color.WHITE);
	}
	
}
