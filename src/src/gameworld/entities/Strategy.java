package gameworld.entities;

import java.util.List;

import gameworld.entities.Item.Action;

/**
 *
 * @author yangcarr
 *
 */
public interface Strategy {
	public List<String> getActions();			// uses of the item that user can perform on it
	public String getDescription();		// description of the item when user 'examines' it
	public String getName(); // the name of the item
	public String performAction(Action action);	// performs specified action based on user choice
}
