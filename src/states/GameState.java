package states;

import gameworld.GameWorld;

public class GameState implements State{
	private GameWorld world;

	public GameState() {
		this.world = new GameWorld();
	}

	@Override
	public void update(StateManager state) {
		world.update();
		
	}

	@Override
	public void render() {
		world.render();
	}

}
