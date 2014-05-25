import java.util.Random;


public class SpriteAsteroid extends Sprite{

	private int health = 100;
	
	public SpriteAsteroid(int startX, int startY) {
		super(startX, startY);

		Random r = new Random();
		SetVelocity(new Vector2D(0, r.nextInt(100) + 100));
		SetDirection(new Vector2D(0, 1f));
		AddTexture("content\\asteroid-v1.png");
		
	}
	
	public int GetHealth()
	{
		return health;
	}
	
	public void RemoveHealth(int h)
	{
		health -= h;
	}

	@Override
	public void Update(float delta) {
		// TODO Auto-generated method stub
		super.Update(delta);
	}

	@Override
	public void Draw(Renderer renderer) {
		// TODO Auto-generated method stub
		super.Draw(renderer);
	}

}
