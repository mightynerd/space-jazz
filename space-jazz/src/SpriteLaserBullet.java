
public class SpriteLaserBullet extends Sprite {

	public SpriteLaserBullet(int startX, int startY) {
		super(startX, startY);
		AddTexture("shootything.png");
		SetVelocity(new Vector2D(0f, 500f));
		SetDirection(new Vector2D(0, -1f));
	}

	@Override
	public void Update(float delta) {
		super.Update(delta);
	}

	@Override
	public void Draw(Renderer renderer) {
		super.Draw(renderer);
	}

}
