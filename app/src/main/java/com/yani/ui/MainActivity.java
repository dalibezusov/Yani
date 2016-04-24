package com.yani.ui;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yani.R;
import com.yani.async_tasks.MusicianLoader;
import com.yani.content.Musician;
import com.yani.database.MusiciansTable;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private List<Musician> musicians;
    private ArtistsListAdapter artistsListAdapter;

    private ListView listView;
    private Parcelable state;

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
    protected void onPause() {
        state = listView.onSaveInstanceState();
        super.onPause();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case R.id.musicians_loader:
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

            artistsListAdapter = new ArtistsListAdapter(this, musicians);
            listView = (ListView) findViewById(R.id.mainLayout);

            if (listView != null) {
                /*state = listView.onSaveInstanceState();*/
                listView.setAdapter(artistsListAdapter);
                /*listView.onRestoreInstanceState(state);*/
                if (state != null) {
                    listView.onRestoreInstanceState(state);
                }



                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String albumsTracksNumText = StringConverter.assignDeclination(musicians.get(position).getNumberOfAlbums(), "альбом")
                                + "  ·  "
                                + StringConverter.assignDeclination(musicians.get(position).getNumberOfTracks(), "трэк");

                        Intent detailActivityIntent = new Intent(MainActivity.this, ArtistDetailActivity.class);
                        detailActivityIntent.putExtra(ExtrasKeys.LINK_TO_BIG_IMG, musicians.get(position).getCover().getLinkToBigCover());
                        detailActivityIntent.putExtra(ExtrasKeys.NAME, musicians.get(position).getName());
                        detailActivityIntent.putExtra(ExtrasKeys.GENRES, StringConverter.makeStringFromList(musicians.get(position).getGenres()));
                        detailActivityIntent.putExtra(ExtrasKeys.ALBUMS_TRACKS, albumsTracksNumText);
                        detailActivityIntent.putExtra(ExtrasKeys.BIOGRAPHY, musicians.get(position).getDescription());
                        startActivity(detailActivityIntent);

                    }
                });
            }

        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

}