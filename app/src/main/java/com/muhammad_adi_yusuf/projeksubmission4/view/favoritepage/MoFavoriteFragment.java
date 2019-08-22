package com.muhammad_adi_yusuf.projeksubmission4.view.favoritepage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.muhammad_adi_yusuf.projeksubmission4.R;
import com.muhammad_adi_yusuf.projeksubmission4.model.localdatabase.pojo.FavoriteTable;
import com.muhammad_adi_yusuf.projeksubmission4.view.adapter.AdapterFavRview;
import com.muhammad_adi_yusuf.projeksubmission4.view.detailpage.DetailActivity;
import com.muhammad_adi_yusuf.projeksubmission4.view.listener.IcsRecyclerView;
import com.muhammad_adi_yusuf.projeksubmission4.viewmodel.FavoriteViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MoFavoriteFragment extends Fragment {

    private AdapterFavRview adaPter;
    private ProgressBar progressBar;
    private FavoriteViewModel viewModel;
    private ArrayList<FavoriteTable> moviesItem = new ArrayList<>();

    public MoFavoriteFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup conTainer, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler_view, conTainer, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progressBar);
        RecyclerView recyclerView = view.findViewById(R.id.rv_home);

        viewModel.getAllMov().observe((LifecycleOwner) Objects.requireNonNull(getContext()), getData);

        adaPter = new AdapterFavRview(getContext(), moviesItem);

        recyclerView.setAdapter(adaPter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        IcsRecyclerView.addTo(recyclerView).setOnItemClickListener(new IcsRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int poSition, View v) {

                FavoriteTable iTem = moviesItem.get(poSition);
                int idItem = iTem.getId();
                String type = iTem.getType();
                Intent intentJump = new Intent(getContext(), DetailActivity.class);
                intentJump.putExtra("idItem", idItem);
                intentJump.putExtra("type", type);
                startActivity(intentJump);

            }
        });

        IcsRecyclerView.addTo(recyclerView).setOnItemLongClickListener(new IcsRecyclerView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
                FavoriteTable iTem = moviesItem.get(position);
                viewModel.delFav(iTem);
                Toast.makeText(getActivity(), "Favorite Deleted", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(FavoriteViewModel.class);
        viewModel.setMov();
    }

    private Observer<List<FavoriteTable>> getData = new Observer<List<FavoriteTable>>() {
        @Override
        public void onChanged(List<FavoriteTable> moviesItem) {
            if (moviesItem != null) {
                progressBar.setVisibility(View.GONE);
                adaPter.setData(moviesItem);
            } else {
                Toast.makeText(getContext(), getString(R.string.error_ms), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        }

    };


}