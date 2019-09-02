package pe.jaav.sistemas.messageFlowApi.client.service;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.jaav.common.util.model.ResultTx;
import pe.jaav.sistemas.messageFlowApi.client.RepositoryFactory;
import pe.jaav.sistemas.messageFlowApi.client.repository.MessageFLowRepositoryInterface;
import pe.jaav.sistemas.messageFlowApi.model.general.GenMensajeAccionContextJson;
import pe.jaav.sistemas.messageFlowApi.model.general.GenMensajeAccionJson;
import pe.jaav.sistemas.messageFlowApi.model.general.SysUsuarioJson;
import pe.jaav.sistemas.messageFlowApi.security.UserCredentials;
import pe.jaav.sistemas.messageFlowApi.util.UtilesRest;
import pe.jaav.sistemas.utiles.Constant;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

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
        return Maybe.just(service.validUser(filtro,headContentType))
                .map(Call::execute)
                .filter( res ->  res.isSuccessful())
                .map(res -> res.body())
            .doOnError( System.out::println);

        /*
        Call<SysUsuarioJson> retrofitCall = service.validUser(filtro,headContentType);
        Response<SysUsuarioJson> response = retrofitCall.execute();
        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null
                    ? response.errorBody().string() : "Unknown error");
        }
        //return response.body();
        return null;
        */
    }

    public Flowable<GenMensajeAccionContextJson> listMsgFLow() throws IOException {

        String username = UtilesRest.getVariableEntornoSystemServer("PAR_USERNAME");
        String password = UtilesRest.getVariableEntornoSystemServer("PAR_PASSWORD");
        String headContentType =  "application/json;charset=UTF-8";

        //Maybe<SysUsuarioJson>  mbUser =   validUser(username,password);
        //String token = mbUser.blockingGet().getTokenSecurity();
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBRE1JTiBZSU1QVS1BRE1JTiIsImp0aSI6IjEiLCJleHAiOjE1NjgyNTcyMDN9.hxGNLqpIzYLf0-_ROvlA0eGkOAx8cmyerDPtKx5sphOHdu-8Y0LGOB9e0QSDyUodPyp5J3sxIeIRzf_b7WBU2w";

        GenMensajeAccionJson filtro = new GenMensajeAccionJson();
        String tokenFullHead = Constant.JWT_TOKEN_PREFIX+" "+token;

        Call<List<GenMensajeAccionJson>> listaMsg =  service.listFlowMsg(filtro,headContentType,tokenFullHead);


        Observable<List<GenMensajeAccionJson>> obsSOurce =
                Observable.fromArray(Maybe.just(listaMsg)
                        //.switchIfEmpty(Observable.empty())
                        .map( res -> res.execute())
                        .filter(res -> res.isSuccessful())
                        .map(res -> res.body()).blockingGet());

        Flowable<GenMensajeAccionJson>  flowMsg =
                obsSOurce.flatMap( list ->
                    Observable.fromIterable(list)
                ).toFlowable(BackpressureStrategy.BUFFER).subscribeOn(Schedulers.computation());

        flowMsg.blockingNext().forEach(
                elemFlowMsg -> {
                    System.out.println("[TEST FLOW::]:asunto:"+elemFlowMsg.getMsgAsunto());
                    System.out.println("[TEST FLOW::]:fecha:"+elemFlowMsg.getMsgFechaRegistro());
                }
        );

        return null;
    }
}
