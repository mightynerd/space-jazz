
public class SpriteBackOverlay extends Sprite {

	public SpriteBackOverlay(int startX, int startY) {
		super(startX, startY);
		
		SetVelocity(new Vector2D(0f, 300f));
		SetDirection(new Vector2D(0f, 1f));
	}

	@Override
	public void Update(float delta) {
		
		if (GetPosition().Y() > 720)
		{
			System.out.println("SWAP");
			SetPosition(new Vector2D(0, -2160));
		}
		
		super.Update(delta);
	}

}
