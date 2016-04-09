package com.yani;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.yani.async_tasks.MusicianAsyncTask;
import com.yani.content.Musician;
import com.yani.database.MusiciansTable;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private List<Musician> musicians;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            musicians = MusiciansTable.listFromCursor(new MusicianAsyncTask(getApplicationContext()).execute().get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

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
