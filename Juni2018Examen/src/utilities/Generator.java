
package utilities;

import java.util.ArrayList;
import java.util.Random;

import model.User;

/**
 * @author Bart Taelemans & Thomas Vanden Bossche
 * @date 1 jan. 2018
 * @project Afstandsbediening
 * @purpose Generator voor aanmaken users en frequenties
 *
 */

public class Generator
{

	private static Random rand = new Random();
	private static ArrayList<Double> FrequencyList = new ArrayList<Double>();

	private static String GenerateVoornaam()
	{

		VoornaamEnum voornaam = VoornaamEnum.values()[rand.nextInt(VoornaamEnum.values().length)];
		String result = voornaam.toString();
		return result;
	}

	private static String GenerateAchternaam()
	{
		AchternaamEnum achternaam = AchternaamEnum.values()[rand.nextInt(AchternaamEnum.values().length)];
		String result = achternaam.toString();
		return result;
	}

	public static double Randomfrequency()
	{
		int a = rand.nextInt(900);
		double b = rand.nextDouble();
		if (FrequencyList.contains(a + b) == true)
		{
			Randomfrequency();
		} else
		{
			AddFrequency(a + b);
		}

		return a + b;
	}

	private static void AddFrequency(double a)
	{
		FrequencyList.add(a);
	}

	// alles random gemaakt zodat we daarna kunnen zien of het programma de juiste
	// handelingen uitvoert

	public static User GenerateUsers(double frequency)
	{
		User aUser = new User(rand.nextBoolean(), Randomfrequency(), GenerateAchternaam(), GenerateVoornaam());
		return aUser;
	}

	/**
	 * @return the frequencyList
	 */
	public static ArrayList<Double> getFrequencyList()
	{
		return FrequencyList;
	}

	/**
	 * @param frequencyList the frequencyList to set
	 */
	public static void setFrequencyList(ArrayList<Double> frequencyList)
	{
		FrequencyList = frequencyList;
	}
	
	
}
