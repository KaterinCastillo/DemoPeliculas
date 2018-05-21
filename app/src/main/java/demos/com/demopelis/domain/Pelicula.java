package demos.com.demopelis.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by autor on 18/05/2018.
 */

// TODO 1. Modelo Pelicula: Se utiliza para recibir los datos de la pelicula desde el web service

public class Pelicula {

    @SerializedName("title")
    private String title;
    @SerializedName("release_date")
    private String publishDate;
    @SerializedName("original_language")
    private String lenguage;
    @SerializedName("poster_path")
    private String poster;
    @SerializedName("backdrop_path")
    private String back_poster;
    @SerializedName("overview")
    private String resume;

    protected static final String TITLE_PREFIX = "Pelicula_";
    protected static final String LANG_PREFIX = "es";
    protected static final String PUBLISH_DATE_PREFIX = "2018-04-25";
    protected static final String IMAGE_PREFIX = "/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg";
    protected static final String BASE_URL_IMAGES = "http://image.tmdb.org/t/p/w185/";
    protected static final String BASE_URL_ORIGINAL_SIZE_IMAGES = "http://image.tmdb.org/t/p/original/";

    public Pelicula() {

    }

    public Pelicula(String title, String publishDate, String poster) {
        this.title = title;
        this.publishDate = publishDate;
        this.poster = this.BASE_URL_IMAGES + poster;
        this.lenguage = this.LANG_PREFIX;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getLenguage() {
        return lenguage;
    }

    public void setLenguage(String lenguage) {
        this.lenguage = lenguage;
    }

    public String getPoster() {
        return BASE_URL_IMAGES + poster;
    }

    public void setPoster(String poster) {
        this.poster = this.BASE_URL_IMAGES + poster;
    }

    public String getBack_poster() {
        return this.BASE_URL_ORIGINAL_SIZE_IMAGES + back_poster;
    }

    public void setBack_poster(String back_poster) {
        this.back_poster = this.BASE_URL_ORIGINAL_SIZE_IMAGES + back_poster;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }
}
