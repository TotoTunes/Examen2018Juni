package junit;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;

import model.User;
import utilities.AchternaamEnum;
import utilities.Generator;
import utilities.VoornaamEnum;

/**
 * @author Bart Taelemans & Thomas Vanden Bossche
 * @date 1 jan. 2018
 * @project Afstandsbediening
 * @purpose test Generator
 *
 */

public class GeneratorTest
{
	private static ArrayList<String> voornamen;
	private static ArrayList<String> achternamen;

	@BeforeClass
	public static void setUpBeforeClass()
	{
		voornamen = new ArrayList<>();
		achternamen = new ArrayList<>();
		for (Object obj : VoornaamEnum.values())
		{
			voornamen.add(obj.toString());
		}
		for (Object obj : AchternaamEnum.values())
		{
			achternamen.add(obj.toString());
		}
	}

	@Test
	public void testRandomFrequency()
	{
		Double frequency = Generator.Randomfrequency();
		assertTrue("Frequentie niet correct",
				Generator.getFrequencyList().contains(frequency) && (frequency > 0 && frequency < 900));
	}

	@Test
	public void testGenerateUsers()
	{
		User user = Generator.GenerateUsers(Generator.Randomfrequency());
		assertTrue("Gebruiker incorrect", voornamen.contains(user.getFirstName())
				&& achternamen.contains(user.getLastName()) && user.getFrequency() > 0 && user.getFrequency() < 900);
	}

}
