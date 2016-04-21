package com.yani.ui;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yani.R;
import com.yani.Tags;
import com.yani.async_tasks.MusicianLoader;
import com.yani.content.Musician;
import com.yani.database.MusiciansTable;


import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private List<Musician> musicians;
    private ArtistsListAdapter artistsListAdapter;

    private ImageView smallArtistImg;
    private TextView artistNameText;
    private TextView genresText;
    private TextView albumsTracksText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smallArtistImg = (ImageView) findViewById(R.id.smallArtistImgView);
        artistNameText = (TextView) findViewById(R.id.mainArtistNameTxt);
        genresText = (TextView) findViewById(R.id.mainGenresTxt);
        albumsTracksText = (TextView) findViewById(R.id.mainAlbumsTracksTxt);

        getLoaderManager().initLoader(R.id.musicians_loader, Bundle.EMPTY, this);

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

            artistsListAdapter = new ArtistsListAdapter(this, musicians);
            ListView listView = (ListView) findViewById(R.id.mainLayout);
            if (listView != null) {
                listView.setAdapter(artistsListAdapter);
            }

            if (listView != null) {
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        /*Log.i(Tags.INFO_TAG, musicians.get(position).getName());
                        Log.i(Tags.INFO_TAG, String.valueOf(musicians.get(position).getGenres()));
                        Log.i(Tags.INFO_TAG, musicians.get(position).getNumberOfAlbums() + " альбомов, "
                                + musicians.get(position).getNumberOfTracks() + " песен");*/

                        String albumsTracksNumText = StringConverter.assignDeclination(musicians.get(position).getNumberOfAlbums(), "альбом")
                                + "  ·  "
                                + StringConverter.assignDeclination(musicians.get(position).getNumberOfTracks(), "трэк");

                        Intent intent = new Intent(MainActivity.this, ArtistDetailActivity.class);
                        intent.putExtra(ExtrasKeys.NAME, musicians.get(position).getName());
                        intent.putExtra(ExtrasKeys.GENRES, StringConverter.makeStringFromList(musicians.get(position).getGenres()));
                        intent.putExtra(ExtrasKeys.ALBUMS_TRACKS, albumsTracksNumText);
                        intent.putExtra(ExtrasKeys.BIOGRAPHY, musicians.get(position).getDescription());
                        startActivity(intent);

                    }
                });
            }

        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

}