package com.yani.async_tasks;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;

import com.yani.MainActivity;
import com.yani.api.APIFactory;
import com.yani.api.APIService;
import com.yani.content.Musician;
import com.yani.database.MusiciansTable;

import java.io.IOException;
import java.util.List;

import retrofit.Call;

public class MusicianAsyncTask extends AsyncTask<Void, Void, Cursor> {

    private List<Musician> musicians = null;

    private Context context;

    public MusicianAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected Cursor doInBackground(Void... params) {

        APIService apiService = APIFactory.getWidgetService();
        Call<List<Musician>> call = apiService.getListOfMusicians();

        try {
            musicians = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MusiciansTable.save(context, musicians);

        return context.getContentResolver().query(MusiciansTable.URI, null, null, null, null);
    }
}
