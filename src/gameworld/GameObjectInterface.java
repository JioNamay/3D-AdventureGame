package gameworld;

/**
 * Interface used for every object within the world - entities and items
 */
public interface GameObjectInterface {
	
	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public Location getLocation();
	
	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	public void setLocation(Location location);
	
	/**
	 * Update.
	 */
	public void update();
	
	/**
	 * Render.
	 */
	public void render();
}
