package gameworld.entities;

/**
 * The Interface Container.
 * @author yangcarr 300368805
 */
public interface Container {
	
	/**
	 * Place item.
	 *
	 * @param item the item
	 */
	public void placeItem(PickUpAbleStrategy item);		// place an item inside
	
	/**
	 * Take item.
	 *
	 * @return the pick up able strategy
	 */
	public PickUpAbleStrategy takeItem();		// get the item from inside the container
	
	/**
	 * Checks for item.
	 *
	 * @return true, if successful
	 */
	public boolean hasItem();
}
