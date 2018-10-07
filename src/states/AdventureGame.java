package states;

import application.GameWorldApp;
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

	private GameState gameState;

	private StateManager stateManager;

	private GameWorldApp application;

	/**
	 * Instantiates a new adventure game.
	 */
	public AdventureGame() {
		
	}
	
	/**
	 * Inits the States and controller.
	 */
	private void init(){
		application = new GameWorldApp();
		controller = new Controller(this);
		
		// set states
		stateManager = new StateManager();
		gameState = new GameState();
		//menuState = new MenuState(handler);
		stateManager.setState(gameState);
	}
	
	/**
	 * Update.
	 */
	private void update(){
		stateManager.update();
	}
	
	/**
	 * Render.
	 */
	private void render(){
		stateManager.render();
	}

	/* (non-Javadoc).
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		init();
		while(isThreadRunning) {
			update();
			render();
		}
		
//		// CLOCK VARIABLES
//		int fps = 60;
//		double timePerUpdate = 1_000_000_000 / fps; 
//		double targetTime = 0.0;
//		long start = 0;
//		long elapsed = System.nanoTime();
//		
//		// testing variables
//		long timer = 0;
//		int ticks = 0;
//		
//		while(isThreadRunning){
//			start = System.nanoTime();
//			targetTime += (start - elapsed);
//			timer += start - elapsed; // for testing
//			elapsed = start; // for testing
//			
//			if(targetTime >= timePerUpdate) {
//				update();
//				render();
//				ticks++; // for testing if we have the correct frame rate
//				targetTime -= timePerUpdate;
//			}
//			
//			// tests to see if our clock and fps are correct
//			if(timer >= 1_000_000_000){
//				System.out.println("Ticks and Frames: " + ticks);
//				ticks = 0;
//				timer = 0;
//			}
//		}
		
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
