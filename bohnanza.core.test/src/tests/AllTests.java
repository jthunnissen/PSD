package tests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	BeanFieldTest.class, CardTest.class, GameFactoryTest.class, 
	GameTest.class, PlayerTest.class, ActionPlantBeanCardTest.class,
	ActionPlantBeanTest.class, ActionDrawCardsTest.class})
public class AllTests {

}
