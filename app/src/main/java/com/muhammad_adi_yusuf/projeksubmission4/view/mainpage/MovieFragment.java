package com.muhammad_adi_yusuf.projeksubmission4.view.mainpage;

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
import com.muhammad_adi_yusuf.projeksubmission4.model.restwebservice.pojo.ApisItem;
import com.muhammad_adi_yusuf.projeksubmission4.view.adapter.AdapterRview;
import com.muhammad_adi_yusuf.projeksubmission4.view.detailpage.DetailActivity;
import com.muhammad_adi_yusuf.projeksubmission4.view.listener.IcsRecyclerView;
import com.muhammad_adi_yusuf.projeksubmission4.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MovieFragment extends Fragment {

    private AdapterRview adaPter;
    private ProgressBar progressBar;
    private MainViewModel viewModel;
    private ArrayList<ApisItem> moviesItem = new ArrayList<>();

    public MovieFragment() {
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

        viewModel.getMovie().observe((LifecycleOwner) Objects.requireNonNull(getContext()), getData);

        adaPter = new AdapterRview(getContext(), moviesItem);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(adaPter);

        IcsRecyclerView.addTo(recyclerView).setOnItemClickListener(new IcsRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int poSition, View v) {

                ApisItem iTem = moviesItem.get(poSition);
                int idItem = iTem.getId();
                Intent intentJump = new Intent(getContext(), DetailActivity.class);
                intentJump.putExtra("idItem", idItem);
                intentJump.putExtra("type", getString(R.string.type1));
                startActivity(intentJump);

            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MainViewModel.class);
        viewModel.setMovie(getString(R.string.idlanguage));
    }

    private Observer<List<ApisItem>> getData = new Observer<List<ApisItem>>() {
        @Override
        public void onChanged(List<ApisItem> moviesItem) {
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
