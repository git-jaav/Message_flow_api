package pe.jaav.sistemas.messageFlowApi.remote;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import pe.jaav.sistemas.messageFlowApi.client.service.MessageFlowService;
import pe.jaav.sistemas.messageFlowApi.model.general.SysUsuarioJson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest
@RunWith(SpringRunner.class)
public class SendEmailRemoteTest {


    @Autowired
    MessageFlowService service;

    @Test
    public void validaUserTest() throws IOException {

        //String username = UtilesRest.getVariableEntornoSystemServer("PAR_USERNAME");
        //String password = UtilesRest.getVariableEntornoSystemServer("PAR_PASSWORD");
        List<SysUsuarioJson> results = new ArrayList<SysUsuarioJson>();

        String username = "ADMIN";
        String password = "xx";
        Maybe<SysUsuarioJson> observable = service.validUser(username,password);

        observable.subscribe(user -> {
            results.add(user);
            System.out.println("DONE::"+user.getUsuaNombre());
        }).dispose();

        Assert.assertNotNull(results);
        Assert.assertTrue("USER LOADES",results.size() == 1);
        //assertThat(results, notNullValue());
        //assertThat(results, hasSize(5));

    }

}
