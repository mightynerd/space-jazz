
public class StateManager {

	public enum State
	{
		Intro,
		MainMenu,
		Tutorial,
		ShopMenu,
		Game,
		EndGame,
		Exit
	}
	
	private State state;
	
	public StateManager()
	{
		state = State.Intro;
	}
	
	public void SetState(State state)
	{
		this.state = state;
	}
	
	public State GetState()
	{
		return this.state;
	}
	
}
