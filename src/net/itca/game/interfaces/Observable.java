package net.itca.game.interfaces;

public interface Observable
{
	/**
	 * Add an observer to recieve updates from the observer
	 * @param o
	 */
	public void registerObserver(Observer o);
	/**
	 * Remove an observer from recieving updates from the observer
	 * @param o
	 */
	public void removeObserver(Observer o);
	/**
	 * Notify all listening observers. Calls their .update() method
	 */
	public void notifyObservers();
}
