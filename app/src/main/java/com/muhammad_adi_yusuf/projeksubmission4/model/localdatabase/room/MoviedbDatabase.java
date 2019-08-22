package com.muhammad_adi_yusuf.projeksubmission4.model.localdatabase.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.muhammad_adi_yusuf.projeksubmission4.model.localdatabase.pojo.FavoriteTable;

@Database(entities = {FavoriteTable.class}, version = 1, exportSchema = false)
public abstract class MoviedbDatabase extends RoomDatabase {
    private static MoviedbDatabase movieDb;

    public abstract FavoriteDao favDao();

    public static synchronized MoviedbDatabase getMoviedb(Context context) {
        if (movieDb == null) {
            movieDb = Room.databaseBuilder(context.getApplicationContext(),
                    MoviedbDatabase.class, "moviedb_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return movieDb;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new dbAsyncTask(movieDb).execute();
        }
    };

    private static class dbAsyncTask extends AsyncTask<Void, Void, Void>{
        FavoriteDao favDao;
        private dbAsyncTask(MoviedbDatabase db){
            favDao =  db.favDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

    }
}
