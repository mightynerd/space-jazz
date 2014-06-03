import java.awt.Color;


public class SpriteHud extends Sprite {
	
	private User user;

	public SpriteHud(int startX, int startY, User user) {
		super(startX, startY);
		this.user = user;
		SetVelocity(Vector2D.Zero());
		SetDirection(Vector2D.Zero());
	}

	@Override
	public void Update(float delta) {
		
		super.Update(delta);
	}

	@Override
	public void Draw(Renderer renderer) {
		renderer.DrawString("Player: " + user.userName, 20, 60, Color.WHITE, 30);
		renderer.DrawString("" + user.money, 20, 100, Color.YELLOW, 25);
		renderer.DrawString("" + user.currentHealth, MainWindow.WIN_WIDTH - 100, 60, Color.RED, 25);
		renderer.DrawString("" + user.points, 20, 130, Color.BLUE, 25);
		
		super.Draw(renderer);
	}

}
