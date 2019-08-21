package pe.jaav.sistemas.messageFlowApi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.CoreMatchers.containsString;

@WebMvcTest
@RunWith(SpringRunner.class)
public class GreetingControllerTest {

    @Autowired
    MockMvc  mockMvc;

    @Test
    public void maleGreeting200OK()  throws Exception{
        String name = "Rafa";
        String gender = "male";

        mockMvc.perform(
            get("/api/greeting")
            .param("name",name)
            .param("gender",gender)
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().string(containsString(
                        String.format("Hello Mr. %s. How are you?", name)
                ))
        );
    }

    @Test
    public void femaleGreeting200OK()  throws Exception{
        String name = "Mary";
        String gender = "female";

        mockMvc.perform(
                get("/api/greeting")
                        .param("name",name)
                        .param("gender",gender)
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().string(containsString(
                        String.format("Hello Mrs. %s. How are you?", name)
                ))
        );
    }
}
