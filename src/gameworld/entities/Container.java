package gameworld.entities;

public interface Container {
	public void place(Item item);		// place an item inside
	public Item get();		// get the item from inside the container
}
