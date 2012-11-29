import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@Suite.SuiteClasses({ 
		GameFactoryTest.class, 
		GameTest.class, 
		TestField.class, 
		TestPlayer.class, 
		ActionPlantBeanTest.class,
		ActionDrawCardsTest.class} )
public class AllTests {

}
