package com.yani;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yani.async_tasks.MusicianAsyncTask;
import com.yani.content.Musician;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private List<Musician> musicians;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            musicians = new MusicianAsyncTask().execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        
    }
}
