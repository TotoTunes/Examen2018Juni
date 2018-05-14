package junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Bart Taelemans & Thomas Vanden Bossche
 * @date 1 jan. 2018
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
