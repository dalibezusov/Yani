package com.yani.async_tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.yani.api.APIFactory;
import com.yani.api.APIService;
import com.yani.content.Musician;
import com.yani.database.MusiciansTable;

import java.io.IOException;
import java.util.List;

import retrofit.Call;

public class MusicianAsyncTask extends AsyncTask<Void, Void, Void> {

    private List<Musician> musicians = null;

    private Context context;

    public MusicianAsyncTask(Context context) {
        this.context = context;
    }


    @Override
    protected Void doInBackground(Void... params) {

        APIService apiService = APIFactory.getWidgetService();
        Call<List<Musician>> call = apiService.getListOfMusicians();

        try {
            musicians = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MusiciansTable.save(context, musicians);

        return null;
    }
}
