package pe.jaav.sistemas.messageFlowApi.controller.api;


import io.reactivex.Maybe;
import io.reactivex.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.jaav.sistemas.messageFlowApi.client.service.MessageFlowService;
import pe.jaav.sistemas.messageFlowApi.model.general.SysUsuarioJson;
import pe.jaav.sistemas.messageFlowApi.util.UtilesRest;

import java.io.IOException;

@RestController
@RequestMapping("api/msgflow")
public class MessageFlowController {


    @Autowired
    MessageFlowService  service;


    @GetMapping(  value = "validuser"  , produces = MediaType.APPLICATION_JSON_VALUE)
    public Maybe<SysUsuarioJson> validUser() throws IOException {

        String username = UtilesRest.getVariableEntornoSystemServer("PAR_USERNAME");
        String password = UtilesRest.getVariableEntornoSystemServer("PAR_PASSWORD");
        return service.validUser(username,password);

    }
}
