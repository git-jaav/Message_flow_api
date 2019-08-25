package pe.jaav.sistemas.messageFlowApi.controller.api;


import io.reactivex.Maybe;
import io.reactivex.Observable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.jaav.sistemas.messageFlowApi.model.general.SysUsuarioJson;

@RestController
@RequestMapping("api/msgflow")
public class MessageFlowController {



    @GetMapping(  value = ""  , produces = MediaType.APPLICATION_JSON_VALUE)
    public Maybe<String> validarUser(){


        Observable<SysUsuarioJson>  obj;
        return null;
    }
}
