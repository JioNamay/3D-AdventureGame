package states;

import controller.Controller;

/**
 * Handles threads and game state.
 */
public class AdventureGame implements Runnable{

	/** The controller. */
	private Controller controller;
	
	/** The thread. */
	private Thread thread;
	
	/** Is thread running. */
	private boolean isThreadRunning;

	/**
	 * Instantiates a new adventure game.
	 */
	public AdventureGame() {
		
	}
	
	/**
	 * Inits the States and controller.
	 */
	private void init(){
		controller = new Controller(this);
		//gameState = new GameState(handler);
		//menuState = new MenuState(handler);
		//State.setState(menuState);
	}
	
	/**
	 * Update.
	 */
	private void update(){

	}
	
	/**
	 * Render.
	 */
	private void render(){
		
	}

	/* (non-Javadoc).
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		init();
		
		while(isThreadRunning){
			update();
			render();
		}
		
		stop();
		
	}
	
	/**
	 * Starts the thread.
	 */
	public synchronized void start(){
		// check if there is already a thread running, do nothing if that is the case
		if(isThreadRunning)
			return;
		
		// create new thread and start running
		isThreadRunning = true;
		thread = new Thread(this); // set up the game class to run on thread
		thread.start(); // run game (calls run())
	}
	
	/**
	 * Stops the thread.
	 */
	public synchronized void stop(){
		// check if thread has already stopped, do nothing if that is the case
		if(!isThreadRunning)
			return;
		
		// stop the thread
		isThreadRunning = false;
		try {
			thread.join(); // waits for thread to stop and then closes
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
