package gameworld.entities;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import gameworld.entities.Item.Action;

/**
 * The treasure chest either contains a potion or a key or nothing. If it doesnt contain an item,
 * it gives the player coins when take is performed on it.
 * @author Deanne Alabastro 300346210
 */
public class TreasureChest extends LockableStrategy implements Container {

	/** The item. */
	private PickUpAbleStrategy item;


	/**
	 * Instantiates a new treasure chest.
	 */
	public TreasureChest() {
		this.coinBank = 5;
		this.description = "mysterious chest";
		this.name = "Chest";
		Random rand = new Random();
		int probability = rand.nextInt(3 + 1) + 1;

		if (probability == 1)
			item = new Potion();
		else if (probability == 2)
			item = new Key();
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.LockableStrategy#getActions()
	 */
	@Override
	public List<String> getActions() {
		if (isLocked) {
			if (Player.getInstance().getInventory().hasKey())
				return actions;
			else
				return Arrays.asList(Action.EXAMINE.toString());
		} else {
			if (isOpen)
				return Arrays.asList(Action.EXAMINE.toString(), Action.TAKE.toString(), Action.PLACE.toString(),
						Action.CLOSE.toString());
			else
				return Arrays.asList(Action.EXAMINE.toString(), Action.OPEN.toString());
		}
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Strategy#isSolid()
	 */
	@Override
	public boolean isSolid() {
		return true;
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Strategy#isDoor()
	 */
	@Override
	public boolean isDoor() {
		return false;
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.LockableStrategy#performAction(gameworld.entities.Item.Action)
	 */
	@Override
	public String performAction(Action action) {
		switch (action) {
		case EXAMINE:
			return givePlayerCoins(1);
		case TAKE:
			if(Player.getInstance().getInventory().isFull()) return "Can't take anything, inventory is full";
			if(item == null) {
				if(coinBank > 0) {
					Player.getInstance().addCoins(1);
					return "You got a coin from the chest";
				}else {
					return "The chest is empty, there is nothing to take";
				}
			}
			Player.getInstance().getInventory().add(item);
			String itemName = item.getName();
			item = null;
			return "You got a " + itemName + " from the chest!";
		case PLACE:
			if(item != null) return "Can't place anything in the chest. It is full";
			PickUpAbleStrategy itemToPlace = Player.getInstance().getSelectedItem();
			placeItem(itemToPlace);
			if(itemToPlace == null) return "Player placed nothing in the chest.";
			return "Player placed " + itemToPlace.getName() + " in the chest.";
		default:
			return super.performAction(action);
		}
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Container#placeItem(gameworld.entities.PickUpAbleStrategy)
	 */
	@Override
	public void placeItem(PickUpAbleStrategy item) {
		if(item == null) this.item = item;
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Container#takeItem()
	 */
	@Override
	public PickUpAbleStrategy takeItem() {
		if(!Player.getInstance().getInventory().isFull()) return item;
		return null;
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.CoinBank#getDescription()
	 */
	@Override
	public String getDescription() {
		if (isLocked)
			return "A locked " + description;
		if (!isLocked && isOpen) {
			String containsStr = null;
			if(item == null) {
				if(coinBank == 0) containsStr = "nothing.";
				else containsStr = "coins.";
			}else containsStr = item.getName();

			return "An open " + description + " containing " + containsStr;
		}
		return "A closed " + description;
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Container#hasItem()
	 */
	@Override
	public boolean hasItem() {
		return item != null;
	}
}
