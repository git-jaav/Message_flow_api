package pe.jaav.sistemas.messageFlowApi.client;

import pe.jaav.sistemas.messageFlowApi.model.general.SysUsuarioJson;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

import java.util.List;

public interface MessageFLowRepositoryInterface {


    @GET("user/repos")
    Call<List<SysUsuarioJson>> listRepos(@Header("Authorization") String accessToken,
                                         @Header("Accept") String apiVersionSpec);
}
