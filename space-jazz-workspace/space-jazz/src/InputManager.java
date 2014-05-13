import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager implements KeyListener{

	private boolean rightArrow = false;
	private boolean leftArrow = false;
	private boolean upArrow = false;
	private boolean downArrow = false;
	private boolean spaceBar = false;
	
	//Supported keys:
	public static enum Key
	{
		ArrowRight,
		ArrowLeft,
		ArrowUp,
		ArrowDown,
		SpaceBar
	}
	
	public InputManager()
	{
		
	}
	
	public boolean IsKeyDown(Key key)
	{
		switch (key) {
		case ArrowRight:
			return rightArrow;
			
		case ArrowLeft:
			return leftArrow;
		
		case ArrowUp:
			return upArrow;
			
		case ArrowDown:
			return downArrow;
			
		case SpaceBar:
			return spaceBar;
			
		default:
			return false;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			upArrow = true;
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			downArrow = true;
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			rightArrow = true;
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			leftArrow = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			spaceBar = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			upArrow = false;
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			downArrow = false;
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			rightArrow = false;
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			leftArrow = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			spaceBar = false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
