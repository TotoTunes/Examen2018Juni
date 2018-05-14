package model;

/**
 * @author Bart Taelemans & Thomas Vanden Bossche
 * @date 1 jan. 2018
 * @project Afstandsbediening
 * @purpose Interface voor class User
 *
 */

public interface IObserver
{

	void handleNotification(double frequency);
}
