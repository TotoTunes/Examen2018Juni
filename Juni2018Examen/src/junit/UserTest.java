package junit;

import static org.junit.Assert.assertEquals;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

import model.User;

/**
 * @author Bart Taelemans & Thomas Vanden Bossche
 * @date 2 jan. 2018
 * @project Afstandsbediening
 * @purpose Run test on class User
 *
 */

public class UserTest
{
	private static User user;
	private static Random random;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		random = new Random();
		user = new User(random.nextBoolean(), 205.13, "Taelemans", "Bart");
	}

	@Test
	public void testHandleNotification()
	{
		user.handleNotification(205.13);
		assertEquals(205.13, user.getFrequency(), 0);
	}

}
