package junit;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;

import model.IDModule;
import model.User;
import utilities.AchternaamEnum;
import utilities.Generator;
import utilities.VoornaamEnum;

/**
 * @author Thomas Vanden Bossche
 * @date 14 mei. 2018
 * @project Afstandsbediening
 * @purpose test Generator
 *
 */

public class GeneratorTest
{
	private static ArrayList<String> voornamen;
	private static ArrayList<String> achternamen;
	private static IDModule module;
	@BeforeClass
	public static void setUpBeforeClass() throws IOException, SQLException
	{
		voornamen = new ArrayList<>();
		achternamen = new ArrayList<>();
		module = new IDModule();
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
		User user = Generator.GenerateUsers(Generator.Randomfrequency(), module);
		assertTrue("Gebruiker incorrect", voornamen.contains(user.getFirstName())
				&& achternamen.contains(user.getLastName()) && user.getFrequency() > 0 && user.getFrequency() < 900);
	}

}
