package demos.com.demopelis.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by autor on 19/05/2018.
 */

// TODO 4. creamos una clase cliente de Retrofit para iniciar los parametros necesarios.
public class RetrofitClient {
    private static Retrofit mRetrofit = null;

    // TODO 4.1. devolvemos una instancia unica (Patrón->Singletón) del cliente de retrofit
    public static Retrofit getClient(String baseUrl) {
        if (mRetrofit == null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return mRetrofit;
    }
}
