package demos.com.demopelis.data;

import demos.com.demopelis.domain.PeliResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by autor on 19/05/2018.
 */

// TODO 3. Declara la interface: Permite mapear los endpoints del web service, y hacer las llamadas
public interface ApiService {

    // TODO 3.1. Obtiene la lista de peliculas (/movies) por @pagina y @lenguaje
    @Headers("Content-Type: application/json")
    //@GET("movies?api_key=661424889d695d5fdf1be718a737602e")
    @GET("movies")
    Call<PeliResponse> getPelis(@Query("api_key") String api_key, @Query("page") int page, @Query("language") String lang);


}
