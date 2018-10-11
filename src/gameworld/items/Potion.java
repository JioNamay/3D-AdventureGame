package gameworld.items;

import java.util.Arrays;
import java.util.List;

import gameworld.Location;
import gameworld.entities.Player;

public class Potion extends PickupableItem {

	public Potion(Location location) {
		super(location);
		this.uses = 1;
		this.description = "A magical potion that restores 10 points of health";
		this.name = "Health Potion";
		this.coinBank = 3;
	}

	@Override
	public Location getLocation() {
		return location;
	}

	@Override
	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

	@Override
	protected String examine() {
		return givePlayerCoins(3);
	}

	@Override
	protected List<String> getActions() {
		if (!this.isInPlayerInventory)
			return Arrays.asList(Action.EXAMINE.toString(), Action.PICKUP.toString() );
		else
			return Arrays.asList(Action.EXAMINE.toString(), Action.PICKUP.toString(), Action.DROP.toString(),
					Action.USE.toString());
	}

	@Override
	protected String makeUnusable() {
		this.setLocation(null);
		this.setInPlayerInventory(false);
		return "It seems that the " + this.name + " has been used too many times. It broke.";
	}

	@Override
	protected String performAction(Action action, Player player) {
		switch (action) {
		case EXAMINE:
			return examine();
		case PICKUP:
			return pickUp(player, this);
		case DROP:
			return drop(player, this);
		case USE:
			return use();
		default:
			throw new IllegalArgumentException("Unknown action: " + action);
		}
	}

}
