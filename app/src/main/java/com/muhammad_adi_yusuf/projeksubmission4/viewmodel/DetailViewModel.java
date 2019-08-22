package com.muhammad_adi_yusuf.projeksubmission4.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.muhammad_adi_yusuf.projeksubmission4.model.localdatabase.pojo.FavoriteTable;
import com.muhammad_adi_yusuf.projeksubmission4.model.repository.ApiRepository;
import com.muhammad_adi_yusuf.projeksubmission4.model.repository.LocalRepository;
import com.muhammad_adi_yusuf.projeksubmission4.model.restwebservice.pojo.ApisItem;

import java.util.List;

public class DetailViewModel extends AndroidViewModel {

    private MutableLiveData<List<ApisItem>> dataDetail;
    private LiveData<List<FavoriteTable>> fav;
    private LocalRepository repository;

    public DetailViewModel(@NonNull Application application) {
        super(application);
        repository = new LocalRepository(application);
    }

    //set data to check data
    public void checkFav(int id, String type) {
        if (fav != null) {
            return;
        }
        repository.checkFav(id, type);
    }

    //get data check
    public LiveData<List<FavoriteTable>> dataCheck() {
        fav = repository.dataCheck();
        return fav;
    }

    //add favorite
    public void addFav(FavoriteTable fav) {
        repository.addFavorite(fav);
    }

    //delete favorite
    public void delFav(FavoriteTable fav) {
        repository.delFavorite(fav);
    }

    //set detail data
    public void setDetail(int idItem, String laNguage, String typeItem) {
        if (dataDetail != null) {
            return;
        }
        ApiRepository rePository = ApiRepository.getApiRepo();
        if (typeItem.equals("movie")) {
            dataDetail = rePository.getMovieDetail(laNguage, idItem);
        } else {
            dataDetail = rePository.getTvDetail(laNguage, idItem);
        }
    }

    //get detail data
    public LiveData<List<ApisItem>> getDetail() {
        return dataDetail;
    }


}
