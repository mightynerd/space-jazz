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

	//Controls
	public static final Key DEFAULT_ACCEPT = Key.Enter;
	public static final Key DEFAULT_UP = Key.W;
	public static final Key DEFAULT_DOWN = Key.S;
	public static final Key DEFAULT_RIGHT = Key.D;
	public static final Key DEFAULT_LEFT = Key.A;
	public static final Key DEFAULT_SHOOT = Key.ArrowUp;
	public static final Key DEFAULT_BACK = Key.Escape;
	
	//Boolean values for each supported key
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
	
	private boolean preEscape = false;
	private boolean escape = false;
	
	private boolean preEnter = false;
	private boolean enter = false;
	
	private boolean preW = false;
	private boolean w = false;
	
	private boolean preA = false;
	private boolean a = false;
	
	private boolean preS = false;
	private boolean s = false;
	
	private boolean preD = false;
	private boolean d = false;
	
	//Supported keys:
	public static enum Key
	{
		ArrowRight,
		ArrowLeft,
		ArrowUp,
		ArrowDown,
		SpaceBar,
		Escape,
		Enter,
		W,
		A,
		S,
		D
	}
	
	public InputManager()
	{

	}
	
	//Note: doesn't work yet!
	public boolean IsKeyPressed(Key key)
	{
		switch (key) {
		case ArrowRight:
			if (rightArrow == true && preRightArrow == false)
			{
				preRightArrow = true;
				return true;
			}
			else
			{
				return false;
			}
			
		case ArrowLeft:
			if (leftArrow == true && preLeftArrow == false)
			{
				preLeftArrow = true;
				return true;
			}
			else
			{
				return false;
			}
		
		case ArrowUp:
			if (upArrow == true && preUpArrow == false)
			{
				preUpArrow = true;
				return true;
			}
			else
			{
				return false;
			}
			
		case ArrowDown:
			if (downArrow == true && preDownArrow == false)
			{
				preDownArrow = true;
				return true;
			}
			else
			{
				return false;
			}
			
		case SpaceBar:
			if (spaceBar == true && preSpaceBar == false)
			{
				preSpaceBar = true;
				return true;
			}
			else
			{
				return false;
			}
			
		case Enter:
			if (enter == true && preEnter == false)
			{
				preEnter = true;
				return true;
			}
			else
			{
				return false;
			}
			
		case Escape:
			if (escape == true && preEscape == false)
			{
				preEscape = true;
				return true;
			}
			else
			{
				return false;
			}
			
		case W:
			if (w == true && preW == false)
			{
				preW = true;
				return true;
			}
			else
			{
				return false;
			}
			
		case A:
			if (a == true && preA == false)
			{
				preA = true;
				return true;
			}
			else
			{
				return false;
			}
			
		case S:
			if (s == true && preS == false)
			{
				preS = true;
				return true;
			}
			else
			{
				return false;
			}
			
		case D:
			if (d == true && preD == false)
			{
				preD = true;
				return true;
			}
			else
			{
				return false;
			}
			
		default:
			return false;
		}
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
			
		case Enter:
			return enter;
			
		case Escape:
			return escape;
			
		case W:
			return w;
			
		case A:
			return a;
			
		case S:
			return s;
			
		case D:
			return d;
			
		default:
			return false;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		/*preDownArrow = downArrow;
		preUpArrow = upArrow;
		preLeftArrow = leftArrow;
		preRightArrow = rightArrow;
		preSpaceBar = spaceBar; */
		
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
		else if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			enter = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			escape = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_W)
		{
			w = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_A)
		{
			a = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_S)
		{
			s = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_D)
		{
			d = true;
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		/*preDownArrow = downArrow;
		preUpArrow = upArrow;
		preLeftArrow = leftArrow;
		preRightArrow = rightArrow;
		preSpaceBar = spaceBar;*/
		
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			upArrow = false;
			preUpArrow = false;
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			downArrow = false;
			preDownArrow = false;
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			rightArrow = false;
			preRightArrow = false;
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			leftArrow = false;
			preLeftArrow = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			spaceBar = false;
			preSpaceBar = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			escape = false;
			preEscape = false;
		}else if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			enter = false;
			preEnter = false;
		}else if(e.getKeyCode() == KeyEvent.VK_W)
		{
			w = false;
			preW = false;
		}else if(e.getKeyCode() == KeyEvent.VK_A)
		{
			a = false;
			preA = false;
		}else if(e.getKeyCode() == KeyEvent.VK_S)
		{
			s = false;
			preS = false;
		}else if(e.getKeyCode() == KeyEvent.VK_D)
		{
			d = false;
			preD = false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
