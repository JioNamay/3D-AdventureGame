package gameworld.entities;

import gameworld.Location;

/**
 * The coinbank class is extended by every entity who is able to give player coins when a player interacts with it.
 */
public abstract class CoinBank implements Strategy{

	/** A long description of the entity. */
	protected String description;

	/** The name of the entity (short description) */
	protected String name;

	/** The coin bank. */
	protected int coinBank;

	/**
	 * Give player coins.
	 *
	 * @param amount the amount
	 * @return the string
	 */
	protected String givePlayerCoins(int amount) {
		if(coinBank == 0) return description;

		int givenAmount = amount; // the amount of coins actually given to player
		// add the rest of coinbank to player if amount exceeds coinbank
		if(((coinBank - amount) < 0) && coinBank != 0){
			Player.getInstance().addCoins(coinBank);
			givenAmount = coinBank;
			coinBank = 0;
		}else {
			Player.getInstance().addCoins(amount);
			coinBank -= amount;
		}

		return "You found " + givenAmount + " coins\n" + description;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the coin bank.
	 *
	 * @return the coinBank
	 */
	public int getCoinBank() {
		return coinBank;
	}

	/**
	 * Sets the coin bank.
	 *
	 * @param coinBank the coinBank to set
	 */
	public void setCoinBank(int coinBank) {
		this.coinBank = coinBank;
	}

}
