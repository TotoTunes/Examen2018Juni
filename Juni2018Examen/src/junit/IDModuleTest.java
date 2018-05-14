package junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

import model.IDModule;
import model.User;

/**
 * @author Bart Taelemans & Thomas Vanden Bossche
 * @date 2 jan. 2018
 * @project Afstandsbediening
 * @purpose Run test on class IDModule
 *
 */

public class IDModuleTest
{

	private static IDModule iDModule;
	private static User user;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		iDModule = new IDModule();
		new Random();
		user = new User(true, 802.11, "Vanden bossche", "Thomas");
	}

	@Test
	public void testRemoveObserver() throws Exception
	{
		iDModule.removeObserver(user);
		assertTrue("Gebruiker heeft nog toegang", user.isAcces() == false);
	}

	@Test
	public void testAddObserver() throws Exception
	{
		iDModule.addObserver(user);
		assertTrue("Gebruikersnaam is leeg", user.getLastName() != null && user.isAcces() == true);
	}

	@Test
	public void testUpdateObserver() throws Exception
	{
		iDModule.updateObserver(802.11, user);
		assertEquals(802.11, user.getFrequency(), 0);
	}

	 @Test
	 public void testNotifyAllArrayListOfUser() throws Exception
	 {
		 iDModule.notifyAll(iDModule.getUserList());
		 for (User user : iDModule.getUserList())
		{
			if (user.isAcces())
			{
				assertEquals(iDModule.getPermittedFrequency(), user.getFrequency(), 0);
			}
		}
	 }

	@Test
	public void testGetSpecificUserIntArrayListOfUser()
	{
		iDModule.GetSpecificUser(iDModule.getUserList().indexOf(user), iDModule.getUserList());
		assertEquals(user, user);
	}

	@Test
	public void testGetSpecificUserString()
	{
		iDModule.GetSpecificUser(user.getLastName());
		assertEquals(user, user);
	}

	 @Test
	 public void testOpenGate() throws Exception
	 {
		 iDModule.openGate(user);
		 if (user.isAcces())
		{
			assertEquals(iDModule.getPermittedFrequency(), user.getFrequency(), 0);
		}
	 }

}
