package junit;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;

import model.IDModule;
import model.User;

/**
 * @author Thomas Vanden Bossche
 * @date 14 mei. 2018
 * @project Afstandsbediening
 * @purpose Run test on class User
 *
 */

public class UserTest {
	private static User user;
	private static Random random;
	private static IDModule module;
	private static ExecutorService executor;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		random = new Random();
		module = new IDModule();
		user = new User(random.nextBoolean(), 205.13, "Taelemans", "Bart", module);
		executor = Executors.newCachedThreadPool();
	}

	@Test
	public void testHandleNotification() {
		user.handleNotification(205.13);
		assertEquals(205.13, user.getFrequency(), 0);
	}

	@Test
	public void testRun() throws IOException, SQLException {
		executor.execute(user);

		executor.shutdown();
		try {
			if (executor.awaitTermination(60, TimeUnit.SECONDS)) {
				System.out.println("All threads have finished");
			}
		} catch (InterruptedException e) {
			System.out.println("Thread Interrupted");
			e.printStackTrace();
		}

	}



}
