import java.util.ArrayList;
import java.util.List;



public class SpriteShip extends Sprite {

	private final int SHIP_VELOCITY = 200;
	private List<SpriteLaserBullet> bulletList;
	private SoundPlayer soundPlayer;
	
	public SpriteShip(int startX, int startY) {
		super(startX, startY);
		bulletList = new ArrayList<SpriteLaserBullet>();
		soundPlayer = new SoundPlayer("content\\shoot-1.wav");
		AddTexture("content\\spaceship-v1.png");
		AddTexture("content\\spaceship-v1-dmg1.png");
		AddTexture("content\\spaceship-v1-dmg2.png");
	}

	public void Update(float delta, InputManager input, List<SpriteAsteroid> asteroidList) {
		
		for (SpriteAsteroid spriteAsteroid : asteroidList) {
			
			if (this.Collides(spriteAsteroid))
			{
				SetCurrentTextureIndex(GetCurrentTextureIndex() + 1);
			}
			
		}
		
		if (input.IsKeyPressed(InputManager.Key.SpaceBar))
		{
			soundPlayer.Reset();
			soundPlayer.Play();
			SpriteLaserBullet newBullet = new SpriteLaserBullet((int)GetPosition().X() - 5 + GetWidth() / 2, (int)GetPosition().Y() - 20);
			bulletList.add(newBullet);
		}
		
		//List of laser sprites that should be removed
		List<SpriteLaserBullet> toRemove = new ArrayList<SpriteLaserBullet>();
		
		for (SpriteLaserBullet laser : bulletList) {
			laser.Update(delta);
			
			//Doesnt work??
			for (SpriteAsteroid asteroid : asteroidList) {
				if (asteroid.Collides(laser))
				{
					toRemove.add(laser);
				}
			}
			
			//Remove all sprites that are not visible
			if (laser.GetPosition().Y() < -50)
			{
				toRemove.add(laser);
			}
		}
		
		for (SpriteLaserBullet spriteLaserBullet : toRemove) {
			bulletList.remove(spriteLaserBullet);
		}
		
		
		
		HandleInput(input);
		super.Update(delta);
	}
	
	@Override
	public void Draw(Renderer renderer) {
		
		for (SpriteLaserBullet laser : bulletList) {
			laser.Draw(renderer);
		}
		
		super.Draw(renderer);
	}

	private void HandleInput(InputManager input)
	{
		if (input.IsKeyDown(InputManager.Key.ArrowLeft) && GetPosition().X() > 0)
		{
			SetVelocity(new Vector2D(SHIP_VELOCITY, GetVelocity().Y()));
			SetDirection(new Vector2D(-1f, GetDirection().Y()));
		}
		else if (input.IsKeyDown(InputManager.Key.ArrowRight) && GetPosition().X() < (MainWindow.WIN_WIDTH - GetWidth()))
		{
			SetVelocity(new Vector2D(SHIP_VELOCITY, GetVelocity().Y()));
			SetDirection(new Vector2D(1f, GetDirection().Y()));
		}
		else
		{
			SetVelocity(new Vector2D(0, GetVelocity().Y()));
			SetDirection(new Vector2D(0f, GetDirection().Y()));
		}
		//-----
		
		if (input.IsKeyDown(InputManager.Key.ArrowDown) && GetPosition().Y() < (MainWindow.WIN_HEIGHT - GetHeight()))
		{
			SetVelocity(new Vector2D(GetVelocity().X(), SHIP_VELOCITY));
			SetDirection(new Vector2D(GetDirection().X(), 1f));
		}
		else if (input.IsKeyDown(InputManager.Key.ArrowUp) && GetPosition().Y() > 24)
		{
			SetVelocity(new Vector2D(GetVelocity().X(), SHIP_VELOCITY));
			SetDirection(new Vector2D(GetDirection().X(), -1f));
		}
		else
		{
			SetVelocity(new Vector2D(GetVelocity().X(), 0));
			SetDirection(new Vector2D(GetDirection().X(), 0f));
		}
	}
}
