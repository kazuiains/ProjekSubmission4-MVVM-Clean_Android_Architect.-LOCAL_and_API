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
import com.muhammad_adi_yusuf.projeksubmission4.model.restwebservice.pojo.ApisItem;

import java.util.List;

public class AdapterRview extends RecyclerView.Adapter<AdapterRview.gViewholder> {
    private Context conText;
    private List<ApisItem> resultsItem;

    public void setData(List<ApisItem> iTems) {
        resultsItem.addAll(iTems);
        notifyDataSetChanged();
    }

    public AdapterRview(Context conText, List<ApisItem> dataList) {
        this.conText = conText;
        this.resultsItem = dataList;
    }

    @NonNull
    @Override
    public AdapterRview.gViewholder onCreateViewHolder(@NonNull ViewGroup paRent, int viewType) {
        View itemRow = LayoutInflater.from(paRent.getContext()).inflate(R.layout.rv_item, paRent, false);
        return new AdapterRview.gViewholder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterRview.gViewholder ghoLder, final int poSition) {

        ApisItem item = resultsItem.get(poSition);
        final String tiTle = item.getName();
        final String reLease = item.getFirstAirDate();
        final double raTe = item.getVoteAverage();
        final String imaGe = "https://image.tmdb.org/t/p/w185" + item.getPosterPath();

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
