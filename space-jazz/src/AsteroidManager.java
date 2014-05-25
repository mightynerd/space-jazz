import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class AsteroidManager {

	private final int START_Y = -100;
	
	private Random r;
	private long currentTime;
	private long previousTime;
	
	private List<SpriteAsteroid> astList;
	
	public AsteroidManager()
	{
		astList = new ArrayList<SpriteAsteroid>();
		r = new Random(1337);
	}
	
	public void Update(float delta)
	{
		currentTime = System.currentTimeMillis();
		
		//Spawn evert 500ms
		if (currentTime - previousTime > 500)
		{
			//Spawn new
			previousTime = currentTime;
			
			SpriteAsteroid newAst = new SpriteAsteroid(r.nextInt(1280), START_Y);
			astList.add(newAst);
			
		}
		
		//List of asteroids that should be removed
		List<SpriteAsteroid> toRemove = new ArrayList<SpriteAsteroid>();
		
		for (SpriteAsteroid asteroid : astList) {
			asteroid.Update(delta);
			
			//Add to remove list if out of view
			if (asteroid.GetPosition().Y() > MainWindow.WIN_HEIGHT)
			{
				toRemove.add(asteroid);
			}
		}
		
		//Remove out of view asteroids
		for (SpriteAsteroid asteroid : toRemove) {
			astList.remove(asteroid);
		}
		
	}
	
	public void Draw(Renderer renderer)
	{
		for (SpriteAsteroid asteroid : astList) {
			asteroid.Draw(renderer);
		}
	}
	
}
