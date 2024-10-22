
public class Vector2D {

	private float x;
	private float y;
	
	public Vector2D(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public float X()
	{
		return x;
	}
	
	public int IntX()
	{
		return (int)x;
	}
	
	public float Y()
	{
		return y;
	}
	
	public int IntY()
	{
		return (int)y;
	}
	
	public void Add(Vector2D vectorToAdd)
	{
		this.x += vectorToAdd.X();
		this.y += vectorToAdd.Y();
	}
	
	public void Multiply(Vector2D vectorToMul)
	{
		this.x *= vectorToMul.X();
		this.y *= vectorToMul.Y();
	}
	
	public void Multiply(float val)
	{
		this.x *= val;
		this.y *= val;
	}
	
	public Vector2D GetVector()
	{
		return new Vector2D(this.x,  this.y);
	}
	
	public static Vector2D Zero()
	{
		return new Vector2D(0, 0);
	}
	
}
