package model;

/**
 * @author Thomas Vanden Bossche
 * @date 14 mei. 2018
 * @project Afstandsbediening
 * @purpose Interface voor class User
 *
 */

public interface IObserver
{

	void handleNotification(double frequency);
}
