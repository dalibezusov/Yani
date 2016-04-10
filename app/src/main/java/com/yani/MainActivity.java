package com.yani;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.yani.async_tasks.MusicianLoader;
import com.yani.content.Musician;
import com.yani.database.MusiciansTable;


import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private List<Musician> musicians;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLoaderManager().initLoader(R.id.musicians_loader, Bundle.EMPTY, this);

        /*MusiciansTable.clear(getApplicationContext());
        new MusicianAsyncTask(getApplicationContext()).execute();
        musicians = MusiciansTable.listFromCursor(this.getContentResolver().query(MusiciansTable.URI, null, null, null, null));

        Log.i(Tags.INFO_TAG, "some result: " + musicians.get(0).getName());
        Log.i(Tags.INFO_TAG, "some result: " + musicians.get(1).getName());
        Log.i(Tags.INFO_TAG, "some result: " + musicians.get(2).getName());
        Log.i(Tags.INFO_TAG, "some result: " + musicians.get(3).getName());
        Log.i(Tags.INFO_TAG, "some result: " + musicians.get(0).getGenres());
        Log.i(Tags.INFO_TAG, "some result: " + musicians.get(1).getGenres());
        Log.i(Tags.INFO_TAG, "some result: " + musicians.get(2).getGenres());
        Log.i(Tags.INFO_TAG, "some result: " + musicians.get(3).getGenres());*/

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case R.id.musicians_loader:
                Log.i(Tags.INFO_TAG, "I called MusicianLoader!");
                return new MusicianLoader(this);
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        int loader_id = loader.getId();
        if (loader_id == R.id.musicians_loader) {
            musicians = MusiciansTable.listFromCursor(data);

            Log.i(Tags.INFO_TAG, "musicians from onLoadFinished: " + musicians);

            Log.i(Tags.INFO_TAG, "some result: " + musicians.get(0).getName());
            Log.i(Tags.INFO_TAG, "some result: " + musicians.get(1).getName());
            Log.i(Tags.INFO_TAG, "some result: " + musicians.get(2).getName());
            Log.i(Tags.INFO_TAG, "some result: " + musicians.get(3).getName());
            Log.i(Tags.INFO_TAG, "some result: " + musicians.get(0).getGenres());
            Log.i(Tags.INFO_TAG, "some result: " + musicians.get(1).getGenres());
            Log.i(Tags.INFO_TAG, "some result: " + musicians.get(2).getGenres());
            Log.i(Tags.INFO_TAG, "some result: " + musicians.get(3).getGenres());
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

}