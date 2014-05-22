

public class SpriteShip extends Sprite {

	private final int SHIP_VELOCITY = 200;
	
	public SpriteShip(int startX, int startY) {
		super(startX, startY);
		LoadTexture("content\\spaceship-v1.png");
	}

	public void Update(float delta, InputManager input) {
		
		if (input.IsKeyDown(InputManager.Key.ArrowLeft))
		{
			SetVelocity(new Vector2D(SHIP_VELOCITY, GetVelocity().Y()));
			SetDirection(new Vector2D(-1f, GetDirection().Y()));
		}
		else if (input.IsKeyDown(InputManager.Key.ArrowRight))
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
		
		if (input.IsKeyDown(InputManager.Key.ArrowDown))
		{
			SetVelocity(new Vector2D(GetVelocity().X(), SHIP_VELOCITY));
			SetDirection(new Vector2D(GetDirection().X(), 1f));
		}
		else if (input.IsKeyDown(InputManager.Key.ArrowUp))
		{
			SetVelocity(new Vector2D(GetVelocity().X(), SHIP_VELOCITY));
			SetDirection(new Vector2D(GetDirection().X(), -1f));
		}
		else
		{
			SetVelocity(new Vector2D(GetVelocity().X(), 0));
			SetDirection(new Vector2D(GetDirection().X(), 0f));
		}
		
		//System.out.println("DIRECTION:" + GetDirection().X() + ", " + GetDirection().Y());
		
		super.Update(delta);
	}
	
	

}
