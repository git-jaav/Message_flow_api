package pe.jaav.sistemas.messageFlowApi;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageFlowApiApplicationTests {

	@Test
	public void contextLoads() {
	}


	@Test
	public void whenValidName_thenEmployeeShouldBeFound() {
		String name = "alex";

		//Assert.assertEquals();
		//assertThat("6".equals(""+name.length());
		Assert.assertTrue("SS" , "6".equals(""+name.length()) );

	}
}
