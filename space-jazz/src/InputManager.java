//
//	Project space-jazz
//	Licenced under the Genereal Public Licence v2
//
//	InputManager.java
//	Registers keyboard input for supported keys, supports multiple keys at once.
//

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager implements KeyListener{

	private boolean preRightArrow = false;
	private boolean rightArrow = false;
	private boolean preLeftArrow = false;
	private boolean leftArrow = false;
	private boolean preUpArrow = false;
	private boolean upArrow = false;
	private boolean preDownArrow = false;
	private boolean downArrow = false;
	private boolean preSpaceBar = false;
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
	
	//Note: doesn't work yet!
	public boolean IsKeyPressed(Key key)
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
	
	public boolean IsKeyDown(Key key)
	{
		switch (key) {
		case ArrowRight:
			if (rightArrow == true && preRightArrow == false)
			{
				return true;
			} else {return false;}
			
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
		preDownArrow = downArrow;
		preUpArrow = upArrow;
		preLeftArrow = leftArrow;
		preRightArrow = rightArrow;
		preSpaceBar = spaceBar;
		
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
		preDownArrow = downArrow;
		preUpArrow = upArrow;
		preLeftArrow = leftArrow;
		preRightArrow = rightArrow;
		preSpaceBar = spaceBar;
		
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
