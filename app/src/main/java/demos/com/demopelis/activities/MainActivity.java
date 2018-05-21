package demos.com.demopelis.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import demos.com.demopelis.activities.adapters.PelisAdapter;
import demos.com.demopelis.domain.PeliResponse;
import demos.com.demopelis.domain.Pelicula;
import demos.com.demopelis.R;
import demos.com.demopelis.data.RetrofitClient;
import demos.com.demopelis.data.ApiService;
import retrofit2.Callback;
import retrofit2.Response;

// TODO 6. Actitividad principal: Muestra la lista de peliculas de manera aleatoria
public class MainActivity extends AppCompatActivity {
    // TODO 6.1. Variables necesarias: se usa ButterKnife para enlazar la vista (recyclerview XML) con una instancia de clase RecyclerView
    private String TAG = MainActivity.class.getSimpleName();
    private String endpoint = "https://api.themoviedb.org/3/genre/28/";
    private String api_key = "661424889d695d5fdf1be718a737602e";
    protected @BindView(R.id.listPelis)
    RecyclerView mRecyclerView;
    ApiService mApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // TODO 6.2. Se envia a ButterKnife la activity para que genere el codigo de las referencias
        // TODO 6.2.1 Con el uso de ButterKnife ya no es necesario hacer esta sentencia: mRecyclerView = (RecyclerView) findViewById(R.id.listPelis);
        ButterKnife.bind(this);
        // TODO 6.2.2. Creamos la instancia de la interface de retrofit, se hace uso de la Interface para acceder a sus metodos mas adelante
        mApiService = RetrofitClient.getClient(endpoint).create(ApiService.class);
        // TODO 6.2.3 Todo RecyclerView necesita un LayoutManager, en mi caso como voy a mostrar una lista se crea una instacia de LinearLayoutMaager
        // TODO 6.2.4. La orienteacion del LayoutManager es VERTICAL, y luego setteamos el linearLayoutManager al recyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        // TODO 6.2.5. Llamamos al metodo getDATA para obtener la lista de peliculas del webService con la libreria Retrofit
        getDATA(4);
    }

    void getDATA(int size) {
        // TODO 6.3. Como queremos que la lista de peliculas que nos devuelva el servicio sea aleatoria con respecto a la pagina
        // TODO 6.3.1. Creamos una variable que almacene un numero aleatorio que este representara la pagina
        // TODO 6.3.2. tambien necesitamos qeue los datos devueltos sea en leanguaje Español para eso enviamos el parametro corespondiente
        int page = 1;
        Random mRandom = new Random();
        page = mRandom.nextInt(5 - 1) + 1;
        Log.w(TAG, "RESULTS FOR PAGE: " + page);
        retrofit2.Call<PeliResponse> mPeliculaCall = mApiService.getPelis(api_key, page, "es");
        mPeliculaCall.enqueue(new Callback<PeliResponse>() {
            @Override
            public void onResponse(retrofit2.Call<PeliResponse> call, Response<PeliResponse> response) {
                // TODO 6.4. Es el callback de la respuesta, esta contiene ya la lista de peliculas
                Log.d(TAG, response.code() + "");
                // TODO 6.5. recuperamos el body del response (datos) en una instancia de nuestro modelo (PeliResponse)
                PeliResponse mPeliResponse = response.body();
                List<Pelicula> result = new ArrayList<Pelicula>();
                // TODO 6.6. Recuperamos la lista de peliculas para enviarselo al adaptador de nuestro recuclerView
                result = mPeliResponse.getPeliculaArrayList();
                // TODO 6.7. Setteamos el Adapter al recyclerView enviandole el @context y la @listaPeliculas
                mRecyclerView.setAdapter(new PelisAdapter(getApplicationContext(), result));

            }

            @Override
            public void onFailure(retrofit2.Call<PeliResponse> call, Throwable t) {
                // TODO 6.8. Callback cuando ocurre un error con el servicio web |NO_RESPONSE|DEVUELE_ESTADO_500|
                call.cancel();
            }
        });
    }
}
