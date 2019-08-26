package pe.jaav.sistemas.messageFlowApi.client.service;

import io.reactivex.Maybe;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.jaav.common.util.model.ResultTx;
import pe.jaav.sistemas.messageFlowApi.client.RepositoryFactory;
import pe.jaav.sistemas.messageFlowApi.client.repository.MessageFLowRepositoryInterface;
import pe.jaav.sistemas.messageFlowApi.model.general.SysUsuarioJson;
import pe.jaav.sistemas.messageFlowApi.security.UserCredentials;
import pe.jaav.sistemas.messageFlowApi.util.UtilesRest;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Service
public class MessageFlowService {


    private MessageFLowRepositoryInterface service =
            RepositoryFactory.createService(MessageFLowRepositoryInterface.class);

    public Maybe<SysUsuarioJson> validUser( String username, String password) throws IOException {

        UserCredentials filtro = new UserCredentials();
        filtro.setUsername(username);
        filtro.setPassword(password);

        /*
        return Maybe.just(
                service.validUser(filtro,RepositoryFactory.API_VERSION_SPEC)
                .execute().body()
        )
        .doOnError( e -> {  System.out.println("ERROR::" +e); });
        */
        /*
        Maybe<SysUsuarioJson> result = Maybe.create(maybeEmitter -> {
            maybeEmitter.onSuccess(
                    service.validUser(filtro,RepositoryFactory.API_VERSION_SPEC)
                            .execute().body()
            );
        });
        */


        //Maybe.zip( null, null );


        String headContentType =  "application/json;charset=UTF-8";
        Call<SysUsuarioJson> retrofitCall = service.validUser(filtro,headContentType);
        Response<SysUsuarioJson> response = retrofitCall.execute();
        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null
                    ? response.errorBody().string() : "Unknown error");
        }
        //return response.body();
        return null;

    }

}
