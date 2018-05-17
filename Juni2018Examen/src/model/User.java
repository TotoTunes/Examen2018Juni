package model;

/**
 * @author Thomas Vanden Bossche
 * @date 14 mei. 2018
 * @project Afstandsbediening
 * @purpose Class voor observer in observer pattern
 *
 */

public class User implements IObserver, Runnable
{
	//Voorzie een random generator die iedere gebruiker een aantal keren aanbiedt aan de poort. 
	//Nadat het aantal verstreken is, beëindigt de gebruiker zijn activiteiten. Dus de thread sterft.
	
	private boolean acces;
	private double frequency;
	private String lastName;
	private String firstName;

	/**
	 * @return the firstName
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * @return the name
	 */

	/**
	 * @return the acces
	 */
	public boolean isAcces()
	{
		return acces;
	}

	/**
	 * @param acces
	 *            the acces to set
	 */
	public void setAcces(boolean acces)
	{
		this.acces = acces;
	}

	/**
	 * @return the frequency
	 */
	public double getFrequency()
	{
		return frequency;
	}

	/**
	 * @param frequency
	 *            the frequency to set
	 */
	private void setFrequency(double frequency)
	{
		this.frequency = frequency;
	}

	/**
	 * @param acces
	 * @param frequency
	 * @param lastName
	 * @param firstName
	 */
	public User(boolean acces, double frequency, String lastName, String firstName)
	{
		super();
		this.acces = acces;
		this.frequency = frequency;
		this.lastName = lastName.toUpperCase();
		this.firstName = firstName.toUpperCase();
	}

	@Override
	public void handleNotification(double frequency)
	{
		setFrequency(frequency);

	}

	public String toString()
	{
		String beschrijving = firstName + " " + lastName + " " + frequency +" " +isAcces() + "\n";
		return beschrijving;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
