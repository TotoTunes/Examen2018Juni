package junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Thomas Vanden Bossche
 * @date 14 mei. 2018
 * @project Afstandsbediening
 * @purpose Run all tests
 *
 */
@RunWith(Suite.class)
@SuiteClasses(
{ GeneratorTest.class, UserTest.class, IDModuleTest.class })
public class AllTests
{

}
