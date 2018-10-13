package gameworld.entities;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import gameworld.entities.Item.Action;

public class TreasureChest extends LockableStrategy implements Container {

	private PickUpAbleStrategy item;


	public TreasureChest() {
		this.coinBank = 5;
		this.description = "a mysterious chest";
		this.name = "Chest";
		Random rand = new Random();
		int probability = rand.nextInt(3 + 1) + 1;

		if (probability == 1)
			item = new Potion();
		else if (probability == 2)
			item = new Key();
	}

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

	@Override
	public boolean isSolid() {
		return true;
	}

	@Override
	public boolean isDoor() {
		return false;
	}

	@Override
	public String performAction(Action action) {
		switch (action) {
		case EXAMINE:
			return givePlayerCoins(1);
		case TAKE:
			if(Player.getInstance().getInventory().isFull()) return "Can't take anything, inventory is full";
			if(item == null && coinBank > 0) {
				Player.getInstance().addCoins(1);
				return "You got a coin from the chest";
			}else if(item == null && coinBank == 0) {
				return "The chest is empty, there is nothing to take";
			}else {
				Player.getInstance().getInventory().add(item);
				String itemName = item.getName();
				item = null;
				return "You got a " + itemName + " from the chest!";
			}
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

	@Override
	public void placeItem(PickUpAbleStrategy item) {
		this.item = item;
	}

	@Override
	public PickUpAbleStrategy takeItem() {
		return item;
	}

	@Override
	public String getDescription() {
		if (isLocked)
			return "A locked " + description;
		if (!isLocked && isOpen) {
			String containsStr = null;
			if(item == null && coinBank == 0) containsStr = "nothing.";
			else if(item == null && coinBank > 0) containsStr = "coins.";
			else containsStr = item.getName();
				
			return "An open " + description + "containing " + containsStr;
		}
		return "A closed " + description;
	}
}
