package demos.com.demopelis.activities.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import demos.com.demopelis.R;
import demos.com.demopelis.activities.DetailActivity;
import demos.com.demopelis.domain.Pelicula;

/**
 * Created by autor on 18/05/2018.
 */

// TODO 5. Adapter es utilizado para poder dar el formato de los datos a nuestro recyclerView (es el puente entre los datos y la vista)
public class PelisAdapter extends RecyclerView.Adapter<PelisAdapter.PelisViewHolder> {
    private final String TAG = "PelisAdapter";
    private List<Pelicula> mPeliculaList;
    private Context mContext;

    // TODO 5.1. Constructor del adapter @context, @listaPeliculas
    public PelisAdapter(Context context, List<Pelicula> list) {
        this.mPeliculaList = list;
        this.mContext = context;
    }

    @Override
    public PelisViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mPeliView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler, parent, false);
        return new PelisViewHolder(mPeliView);
    }

    @Override
    public void onBindViewHolder(PelisViewHolder pPeliViewHolder, int position) {
        // TODO 5.5. Rcuperamos una pelicula del array de pelculas
        final Pelicula mPelicula = mPeliculaList.get(position);
        // TODO 5.6. Ingreamos los datos de la pelicula para ser mostrado en la vista
        pPeliViewHolder.vTitle.setText(mPelicula.getTitle());
        pPeliViewHolder.vPublishDate.setText(mPelicula.getPublishDate());
        pPeliViewHolder.vLanguagePeli.setText(mPelicula.getLenguage());
        pPeliViewHolder.vImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
        // TODO 5.7. Cargamos la imagen con la libreria Glide
        Glide.with(mContext).load(mPelicula.getPoster())
                .placeholder(R.drawable.sharp_insert_photo_24)
                .thumbnail(0.5f)
                .crossFade()
                .override(200, 200)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(pPeliViewHolder.vImage);
        // TODO 5.8. Listener cuando hacen click a una pelicula para mostrar los detalles en otra activity
        pPeliViewHolder.getmView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(), "Hello", Toast.LENGTH_LONG).show();
                // TODO 5.9. Creamos un intent (ActivityOrigen (context), ActivityDestino(context))
                Intent mIntent = new Intent(view.getContext(), DetailActivity.class);
                // TODO 5.10. Ingremos los parametros extra que enviamos a la otra pantalla
                mIntent.putExtra("title", mPelicula.getTitle());
                mIntent.putExtra("image", mPelicula.getBack_poster());
                mIntent.putExtra("date", mPelicula.getPublishDate());
                mIntent.putExtra("resume", mPelicula.getResume());
                // TODO 5.11. iniciamos el intent hacia la otra pantalla
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPeliculaList.size();
    }

    // TODO 5.2. Creamos el ViewHolder
    class PelisViewHolder extends RecyclerView.ViewHolder {
        // TODO 5.3. Creamos las referencias a nuestra vista con ButterKnife
        private final View mView;
        protected @BindView(R.id.title_peli)
        TextView vTitle;
        protected @BindView(R.id.publish_date)
        TextView vPublishDate;
        protected @BindView(R.id.image)
        ImageView vImage;
        protected @BindView(R.id.language_peli)
        TextView vLanguagePeli;

        public PelisViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            ButterKnife.bind(this, itemView);
        }

        public View getmView() {
            return mView;
        }
    }
}
