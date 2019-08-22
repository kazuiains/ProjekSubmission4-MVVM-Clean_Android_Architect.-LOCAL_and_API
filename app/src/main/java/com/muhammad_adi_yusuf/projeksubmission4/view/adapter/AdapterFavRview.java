package com.muhammad_adi_yusuf.projeksubmission4.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.muhammad_adi_yusuf.projeksubmission4.R;
import com.muhammad_adi_yusuf.projeksubmission4.model.localdatabase.pojo.FavoriteTable;

import java.util.List;

public class AdapterFavRview extends RecyclerView.Adapter<AdapterFavRview.gViewholder> {
    private Context conText;
    private List<FavoriteTable> resultsItem;

    public void setData(List<FavoriteTable> iTems) {
        resultsItem.addAll(iTems);
        notifyDataSetChanged();
    }

    public AdapterFavRview(Context conText, List<FavoriteTable> dataList) {
        this.conText = conText;
        this.resultsItem = dataList;
    }

    @NonNull
    @Override
    public AdapterFavRview.gViewholder onCreateViewHolder(@NonNull ViewGroup paRent, int viewType) {
        View itemRow = LayoutInflater.from(paRent.getContext()).inflate(R.layout.rv_item, paRent, false);
        return new AdapterFavRview.gViewholder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterFavRview.gViewholder ghoLder, final int poSition) {

        FavoriteTable fav = resultsItem.get(poSition);
        final String tiTle = fav.getTitle();
        final String reLease = fav.getRelease_date();
        final double raTe = fav.getVoteAverage();
        final String imaGe = "https://image.tmdb.org/t/p/w185" + fav.getPath();

        ghoLder.dataTitle.setText(tiTle);
        ghoLder.dataRelease.setText(reLease);
        ghoLder.dataRate.setText(String.valueOf(raTe));

        Glide.with(conText)
                .load(imaGe)
                .apply(new RequestOptions().override(185, 278))
                .into(ghoLder.dataImage);
    }

    @Override
    public int getItemCount() {
        return resultsItem.size();
    }

    class gViewholder extends RecyclerView.ViewHolder {
        ImageView dataImage;
        TextView dataTitle, dataRelease, dataRate;

        gViewholder(@NonNull View itemView) {
            super(itemView);
            dataImage = itemView.findViewById(R.id.iv_image);
            dataTitle = itemView.findViewById(R.id.tv_title);
            dataRelease = itemView.findViewById(R.id.tv_release);
            dataRate = itemView.findViewById(R.id.tv_rating);
        }
    }
}
