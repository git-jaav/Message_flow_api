package pe.jaav.sistemas.messageFlowApi.client.repository;

import pe.jaav.sistemas.messageFlow.service.GenMensajeAccionService;
import pe.jaav.sistemas.messageFlowApi.client.RepositoryFactory;
import pe.jaav.sistemas.messageFlowApi.model.general.SysUsuarioJson;
import pe.jaav.sistemas.messageFlowApi.security.UserCredentials;
import retrofit2.Call;
import retrofit2.http.*;


public interface MessageFLowRepositoryInterface {




    @GET(RepositoryFactory.API_BASE_PATH + GenMensajeAccionService.URI_AUTH_BUSCAR_USUARIO_ID+"{idUser}")
    Call<SysUsuarioJson> getUser(@Header("Authorization") String accessToken,
                                 @Header("Accept") String apiVersionSpec,
                                 @Path("idUser") String idUser);


    @POST(RepositoryFactory.API_BASE_PATH + GenMensajeAccionService.URI_AUTH_AUTORIZAR)
    Call<SysUsuarioJson> validUser(@Body UserCredentials user,
                                   @Header("content-type") String apiVersionSpec);


}
