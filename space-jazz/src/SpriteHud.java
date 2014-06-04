import java.awt.Color;

public class SpriteHud extends Sprite {
	
	Sprite coin;
	private User user;

	public SpriteHud(int startX, int startY, User user) {
		super(startX, startY);
		coin = new Sprite(20, 84);
		coin.AddTexture("icon-coin-2.png");
		coin.SetDirection(Vector2D.Zero());
		
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
		renderer.DrawString("" + user.money, 45, 100, Color.YELLOW, 25);
		renderer.DrawSprite(coin);
		renderer.DrawString("" + user.currentHealth, MainWindow.WIN_WIDTH - 100, 60, Color.RED, 25);
		renderer.DrawString("" + user.points, 20, 130, Color.BLUE, 25);
		
		super.Draw(renderer);
	}

}
