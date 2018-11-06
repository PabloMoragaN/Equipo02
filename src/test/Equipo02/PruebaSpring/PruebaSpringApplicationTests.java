package Equipo02.PruebaSpring;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import junit.framework.TestCase;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)	
public class PruebaSpringApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void TestHolaMundo() {
		
		assertEquals("Hola Mundo",HolaMundo());
	}
	
	public String HolaMundo() {
		return "Hola Mundo";
	}

}
