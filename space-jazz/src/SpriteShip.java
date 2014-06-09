import java.util.ArrayList;
import java.util.List;



public class SpriteShip extends Sprite {

	private final int SHIP_VELOCITY = 200;
	private List<SpriteLaserBullet> bulletList;
	private SoundPlayer soundPlayerShoot;
	private SoundPlayer soundPlayerDamage;
	private SoundPlayer soundAsteroidDamage;
	private StateManager stateManager;
	private User user;
	
	public SpriteShip(int startX, int startY, StateManager stateManager, User user) {
		super(startX, startY);
		this.user = user;
		this.stateManager = stateManager;
		bulletList = new ArrayList<SpriteLaserBullet>();
		soundPlayerShoot = new SoundPlayer("shoot-1.wav");
		soundPlayerDamage = new SoundPlayer("damage-1.wav");
		soundAsteroidDamage = new SoundPlayer("asteroid-damage-1.wav");
		AddTexture("spaceship-v1.png");
		AddTexture("spaceship-v1-dmg1.png");
		AddTexture("spaceship-v1-dmg2.png");
	}
	
	public void RemoveHealth(int rm)
	{
		user.currentHealth -= rm;
	}
	
	public int GetHeath()
	{
		return user.currentHealth;
	}

	public void Update(float delta, InputManager input, List<SpriteAsteroid> asteroidList) {
		
		//List of asteroids to be removed
		List<SpriteAsteroid> toRemoveAst = new ArrayList<SpriteAsteroid>();
		
		//Check for asteroid collision with ship
		for (SpriteAsteroid spriteAsteroid : asteroidList) {
			if (this.Collides(spriteAsteroid))
			{
				toRemoveAst.add(spriteAsteroid);
				soundPlayerDamage.Reset();
				soundPlayerDamage.Play();
				// y = 33 * 0,9^x where x is the weapon level and y is the damage
				RemoveHealth((int)(StatTrack.ASTEROID_CRASH_DAMAGE * (Math.pow(0.9, user.weaponLevel))));
			}
		}
		
		//Remove asteroids
		for (SpriteAsteroid spriteAsteroid : toRemoveAst) {
			asteroidList.remove(spriteAsteroid);
		}
		
		//Shoot
		if (input.IsKeyPressed(InputManager.DEFAULT_SHOOT))
		{
			soundPlayerShoot.Reset();
			soundPlayerShoot.Play();
			SpriteLaserBullet newBullet = new SpriteLaserBullet((int)GetPosition().X() - 5 + GetWidth() / 2, (int)GetPosition().Y() - 20);
			bulletList.add(newBullet);
		}
		
		//List of laser sprites that should be removed
		List<SpriteLaserBullet> toRemove = new ArrayList<SpriteLaserBullet>();
		
		//Check for asteroidcollision with bullets
		for (SpriteLaserBullet laser : bulletList) {
			laser.Update(delta);
			
			for (SpriteAsteroid asteroid : asteroidList) {
				if (asteroid.Collides(laser))
				{
					asteroid.RemoveHealth(20 * user.weaponLevel); //20 * weaponlevel (1 default)
					toRemove.add(laser);
					//Play sound effect
					soundAsteroidDamage.Reset();
					soundAsteroidDamage.Play();
					//Stats:
					user.points += 1; 
					user.money += 1;
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
		
		//End game if health equal or below 0
		if (GetHeath() <= 0)
		{
			stateManager.SetState(StateManager.State.EndGame);
		}
		
		HandleInput(input);
		
		//Change textures depending on health
		if (GetHeath() < 66 && GetHeath() > 33)
		{
			SetCurrentTextureIndex(1);
		}else if (GetHeath() < 33)
		{
			SetCurrentTextureIndex(2);
		}else
		{
			SetCurrentTextureIndex(0);
		}
		
		super.Update(delta);
		
		//Stats:
		user.currentHealth = GetHeath();
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
		if (input.IsKeyDown(InputManager.DEFAULT_LEFT) && GetPosition().X() > 0)
		{
			SetVelocity(new Vector2D(SHIP_VELOCITY, GetVelocity().Y()));
			SetDirection(new Vector2D(-1f, GetDirection().Y()));
		}
		else if (input.IsKeyDown(InputManager.DEFAULT_RIGHT) && GetPosition().X() < (MainWindow.WIN_WIDTH - GetWidth()))
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
		
		if (input.IsKeyDown(InputManager.DEFAULT_DOWN) && GetPosition().Y() < (MainWindow.WIN_HEIGHT - GetHeight()))
		{
			SetVelocity(new Vector2D(GetVelocity().X(), SHIP_VELOCITY));
			SetDirection(new Vector2D(GetDirection().X(), 1f));
		}
		else if (input.IsKeyDown(InputManager.DEFAULT_UP) && GetPosition().Y() > 24)
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
