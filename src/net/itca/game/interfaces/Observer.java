package net.itca.game.interfaces;

/**
 * Observer which listens to updates of an observable
 * (Observer as defined in the Observer-Pattern)
 * @author Dylan
 *
 */
public interface Observer
{
	/**
	 * This method gets called once the Observable calls notify();
	 */
	public void update();
}
