package com.muhammad_adi_yusuf.projeksubmission4.view.detailpage;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.muhammad_adi_yusuf.projeksubmission4.R;
import com.muhammad_adi_yusuf.projeksubmission4.model.localdatabase.pojo.FavoriteTable;
import com.muhammad_adi_yusuf.projeksubmission4.model.restwebservice.pojo.ApisItem;
import com.muhammad_adi_yusuf.projeksubmission4.view.adapter.AdapterCoToolbar;
import com.muhammad_adi_yusuf.projeksubmission4.viewmodel.DetailViewModel;

import java.util.List;
import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    //variable
    private int idItem, idFav;
    private Double voteItem;
    private String type, pathItem, titleItem, releaseItem ;

    //Not in Activity
    private Menu menu;
    private DetailViewModel viewModel;
    private AdapterCoToolbar collapsingToolbar;

    //in Activity
    private Toolbar toolBar;
    private ProgressBar progressBar;
    private LinearLayout linearLayout;
    private ConstraintLayout constraintLayout;
    ImageView dataImage, dataBackimage;
    TextView dataTitle, dataTitle2, dataRelease, dataOverview, dataLanguage, dataRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //initialization
        iniTialize();

        collapsingToolbar = new AdapterCoToolbar(this);
        setSupportActionBar(toolBar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        viewModel.checkFav(idItem, type);
        viewModel.dataCheck().observe(Objects.requireNonNull(this), getCheck);
        viewModel.setDetail(idItem, getString(R.string.idlanguage), type);
        viewModel.getDetail().observe(Objects.requireNonNull(this), getData);

    }

    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private Observer<List<ApisItem>> getData = new Observer<List<ApisItem>>() {
        @Override
        public void onChanged(List<ApisItem> deTail) {
            if (deTail != null) {

                ApisItem item = deTail.get(0);

                titleItem = item.getName();
                voteItem = item.getVoteAverage();
                releaseItem = item.getFirstAirDate();
                pathItem = item.getPosterPath();

                dataTitle.setText(titleItem);
                dataTitle2.setText(titleItem);
                dataRelease.setText(releaseItem);
                dataOverview.setText(item.getOverview());
                dataLanguage.setText(item.getOriginalLanguage());
                dataRate.setText(String.valueOf(voteItem));

                String linkImage1 = "https://image.tmdb.org/t/p/w185" + pathItem;
                Glide.with(getBaseContext())
                        .load(linkImage1)
                        .apply(new RequestOptions().override(185, 278))
                        .into(dataImage);

                String linkImage2 = "https://image.tmdb.org/t/p/w500" + pathItem;
                Glide.with(getBaseContext())
                        .load(linkImage2)
                        .apply(new RequestOptions().override(185, 278))
                        .into(dataBackimage);

                String titleToolbar = titleItem;

                collapsingToolbar.setColl(titleToolbar);
                progressBar.setVisibility(View.GONE);
                constraintLayout.setVisibility(View.GONE);
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.error_ms), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
            }
        }

    };

    private Observer<List<FavoriteTable>> getCheck = new Observer<List<FavoriteTable>>() {
        @Override
        public void onChanged(List<FavoriteTable> dataFav) {
            if (dataFav.isEmpty()) {
//                menu.getItem(0  ).setIcon(R.drawable.favorite_no_active);
                idFav = 0;
            } else {
//                menu.getItem(0).setIcon(R.drawable.favorite_active);
                idFav = 1;

            }
        }

    };

    private void iniTialize() {
        viewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        progressBar = findViewById(R.id.progressBar);
        linearLayout = findViewById(R.id.ll_error);
        constraintLayout = findViewById(R.id.cl_progressbar);
        toolBar = findViewById(R.id.tb_activity_detail);

        idItem = getIntent().getIntExtra("idItem", 0);
        type = getIntent().getStringExtra("type");

        dataTitle = findViewById(R.id.tv_detail_title);
        dataTitle2 = findViewById(R.id.tv_Dtitle);
        dataRelease = findViewById(R.id.tv_Drelease);
        dataOverview = findViewById(R.id.tv_Doverview);
        dataLanguage = findViewById(R.id.tv_Dlanguage);
        dataRate = findViewById(R.id.tv_detail_rate);

        dataImage = findViewById(R.id.iv_detail_image);
        dataBackimage = findViewById(R.id.iv_backimage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_favorite, menu);
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        Handle presses on the action bar items
        if (item.getItemId() == R.id.itmFavorite) {//                composeMessage();
            if (idFav == 0){
//                FavoriteTable itemFav = new FavoriteTable(idItem, type, titleItem, voteItem, releaseItem, pathItem);
//                viewModel.addFav(itemFav);
                menu.getItem(0).setIcon(R.drawable.favorite_active);
                idFav = 1;
            }else {
                menu.getItem(0).setIcon(R.drawable.favorite_no_active);
                idFav = 0;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
