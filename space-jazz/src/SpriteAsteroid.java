import java.util.Random;


public class SpriteAsteroid extends Sprite{

	private int health = 100;
	private int asteroidType = 0;
	
	public SpriteAsteroid(int startX, int startY) {
		super(startX, startY);

		Random r = new Random();
		SetVelocity(new Vector2D(0, r.nextInt(100) + 100));
		SetDirection(new Vector2D(0, 1f));
		AddTexture("asteroid-v1.png");
		AddTexture("Asteroid-v1-dmg1.png");
		AddTexture("Asteroid-v1-dmg2.png");
		
		AddTexture("Asteroid-v2.png");
		AddTexture("Asteroid-v2-dmg1.png");
		AddTexture("Asteroid-v2-dmg2.png");
		
		AddTexture("Asteroid-v3.png");
		AddTexture("Asteroid-v3-dmg1.png");
		AddTexture("Asteroid-v3-dmg2.png");
		
		Random random = new Random();
		asteroidType = random.nextInt(3);
		if (asteroidType == 0 ){
			SetCurrentTextureIndex(0);
			
		} else if (asteroidType == 1){
			SetCurrentTextureIndex(3);
			
		}else if (asteroidType == 2){
			SetCurrentTextureIndex(6);
		}
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
		if (GetHealth() < 66 && GetHealth() > 33)
		{
			if (asteroidType == 0 ){
				SetCurrentTextureIndex(1);
				
			} else if (asteroidType == 1){
				SetCurrentTextureIndex(4);
				
			}else if (asteroidType == 2){
				SetCurrentTextureIndex(7);
			}
		}
		else if (GetHealth() < 33)
		{
			if (asteroidType == 0 ){
				SetCurrentTextureIndex(2);
				
			} else if (asteroidType == 1){
				SetCurrentTextureIndex(5);
				
			}else if (asteroidType == 2){
				SetCurrentTextureIndex(8);
			}
		}
		super.Update(delta);
	}

	@Override
	public void Draw(Renderer renderer) {
		// TODO Auto-generated method stub
		super.Draw(renderer);
	}

}
