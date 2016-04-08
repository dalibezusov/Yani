package com.yani.async_tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.yani.api.APIFactory;
import com.yani.api.APIService;
import com.yani.content.Musician;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit.Call;

public class MusicianAsyncTask extends AsyncTask<Void, Void, List<Musician>> {

    private static final String INFO_TAG = "INFO_TAG";
    private static final String ERR_TAG = "ERR_TAG";

    private List<Musician> musicians = null;

    @Override
    protected List<Musician> doInBackground(Void... params) {

        APIService apiService = APIFactory.getWidgetService();
        Call<List<Musician>> call = apiService.getListOfMusicians();

        try {
            musicians = call.execute().body();
        } catch (IOException e) {
            Log.e(ERR_TAG, "I don't get musicians");
            e.printStackTrace();
        }

        Log.i(INFO_TAG, "musicians: " + musicians);

        return musicians;
    }
}
