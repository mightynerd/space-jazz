
public class SpriteBackOverlay extends Sprite {

	public SpriteBackOverlay(int startX, int startY) {
		super(startX, startY);
		
		SetVelocity(new Vector2D(300f, 0f));
		LoadTexture("content\\nebula-repeat-v1.png");
		SetDirection(new Vector2D(-1f, 0f));
	}

	@Override
	public void Update(float delta) {
		
		if (GetPosition().X() < -2560)
		{
			SetPosition(new Vector2D(2560, 0));
		}
		
		super.Update(delta);
	}

}
