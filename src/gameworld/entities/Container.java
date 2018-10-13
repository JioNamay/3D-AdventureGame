package gameworld.entities;

public interface Container {
	public void placeItem(PickUpAbleStrategy item);		// place an item inside
	public PickUpAbleStrategy takeItem();		// get the item from inside the container
}
