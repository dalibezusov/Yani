package com.yani.async_tasks;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.yani.Tags;
import com.yani.api.APIFactory;
import com.yani.api.APIService;
import com.yani.content.Musician;
import com.yani.database.MusiciansTable;

import java.io.IOException;
import java.util.List;

import retrofit.Call;

public class MusicianLoader extends android.content.AsyncTaskLoader<Cursor> {

    private List<Musician> musicians = null;

    public MusicianLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public Cursor loadInBackground() {

        APIService apiService = APIFactory.getWidgetService();
        Call<List<Musician>> call = apiService.getListOfMusicians();

        try {
            musicians = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MusiciansTable.save(getContext(), musicians);
        Log.i(Tags.INFO_TAG, "I saved data in DB!");
        return getContext().getContentResolver().query(MusiciansTable.URI, null, null, null, null);

    }

}
