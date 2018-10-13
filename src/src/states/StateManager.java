package states;

public class StateManager{
	
	private State currentState;
	
	public void update() {
		if(currentState != null) currentState.update(this);
	}
	
	public void render() {
		if(currentState != null) currentState.render();
	}

	public void setState(State state){
		currentState = state;
	}
	
	public State getState(){
		return currentState;
	}

}
