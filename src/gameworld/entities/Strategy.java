package gameworld.entities;

import java.util.List;

/**
 *
 * @author yangcarr
 *
 */
public interface Strategy {
	public List<String> getActions();			// uses of the item that user can perform on it
	public String description();		// description of the item when user 'examines' it
	public void performAction(String action);	// performs specified action based on user choice
	// coin bank related stuff: default method?
}
