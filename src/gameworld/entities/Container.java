package gameworld.entities;

public interface Container {
	public void place(Entity item);		// place an item inside
	public Entity get();		// get the item from inside the container
}
