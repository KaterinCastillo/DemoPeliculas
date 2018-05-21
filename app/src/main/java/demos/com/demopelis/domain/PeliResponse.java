package demos.com.demopelis.domain;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import demos.com.demopelis.domain.Pelicula;

/**
 * Created by autor on 19/05/2018.
 */

// TODO 2. Modelo PeliResponse: Se utiliza para recuperar los datos del web service
public class PeliResponse {
    @SerializedName("id")
    private int id;
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<Pelicula> peliculaArrayList = new ArrayList();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Pelicula> getPeliculaArrayList() {
        return peliculaArrayList;
    }

    public void setPeliculaArrayList(List<Pelicula> peliculaArrayList) {
        this.peliculaArrayList = peliculaArrayList;
    }
}
