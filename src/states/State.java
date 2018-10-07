package states;

public interface State {
	
	public void update(StateManager state);
	public void render();
	
}
