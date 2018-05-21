package demos.com.demopelis.activities;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import demos.com.demopelis.R;

// TODO 7. Activity de detalle de la pelicula
public class DetailActivity extends AppCompatActivity {
    // TODO 7.1 Referencia hacia la vista ButterKnife
    protected @BindView(R.id.title_detail)
    TextView mTitleDetail;
    protected @BindView(R.id.resume_detail)
    TextView mResumeDetail;
    protected @BindView(R.id.date_detail)
    TextView mDateDetail;
    protected @BindView(R.id.imagen_detail)
    ImageView mImageDetail;
    protected @BindView(R.id.resume_text)
    TextView mResumeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        // TODO 7.2. Creamos la Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initCollapsingToolbar();
        // TODO 7.3. Recuperamos los datos enviamos desde la activity 1 y mostramos en la vista de detalle
        mTitleDetail.setText(getIntent().getExtras().getString("title") + "");
        mDateDetail.setText(getIntent().getExtras().getString("date"));
        mResumeDetail.setText(getIntent().getExtras().getString("resume"));
        // TODO 7.3.1. Cargamos la imagen con Glide
        Glide.with(this)
                .load(getIntent().getExtras().getString("image"))
                .crossFade()
                .into(mImageDetail);
    }

    // TODO 7.4. Metodo que me permite manejar la Toolbar y el Collapsing
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);
        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getIntent().getExtras().getString("title"));
                    isShow = true;
                    // TODO 7.4.1. Ocultamos el texto Resumen
                    mResumeText.setVisibility(View.GONE);
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                    // TODO 7.4.2. Mostramos el texto Resumen
                    mResumeText.setVisibility(View.VISIBLE);
                }
            }
        });
    }


}
