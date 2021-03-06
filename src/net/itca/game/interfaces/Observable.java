package net.itca.game.interfaces;

/**
 * Interface defining a class as Observable in the Observer-Pattern.
 * @author Dylan
 *
 */
public interface Observable
{
	/**
	 * Add an observer to recieve updates from the observer
	 * @param Observer
	 */
	public void registerObserver(Observer o);
	/**
	 * Remove an observer from recieving updates from the observer
	 * @param Observer
	 */
	public void removeObserver(Observer o);
	/**
	 * Notify all listening observers. Calls their .update() method
	 */
	public void notifyObservers();
}
