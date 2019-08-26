package pe.jaav.sistemas.messageFlowApi.client;

import com.google.gson.*;
import okhttp3.OkHttpClient;
import pe.jaav.sistemas.messageFlowApi.util.UtilesRest;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.lang.reflect.Type;
import java.util.Date;

public class RepositoryFactory {

    public static final String API_VERSION_SPEC = "application/yimpu-1.0";
    public static final String JSON_CONTENT_TYPE = "application/json";
    public static final String API_BASE_PATH = "prod/api";

    private static final String BASE_URL =
      "https://shzsnfrzoi.execute-api.us-east-1.amazonaws.com/";
    //private static final String BASE_URL = UtilesRest.getParamUrlBaseiAPIConsulta();


    private static Gson builderGson = new GsonBuilder()
            .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                public Date deserialize(JsonElement jsonElement, Type type,
                                        JsonDeserializationContext context) throws JsonParseException {
                    return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
                }
            })
            .create();

    /*
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Date.class, getUnixEpochDateTypeAdapter()).create();
    */

    private static Retrofit.Builder builder
            = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(builderGson));

    private static Retrofit retrofit = builder.build();

    private static OkHttpClient.Builder httpClient
            = new OkHttpClient.Builder();

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
