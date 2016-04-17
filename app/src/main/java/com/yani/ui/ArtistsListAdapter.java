package com.yani.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yani.R;
import com.yani.content.Musician;

import java.util.List;

public class ArtistsListAdapter extends BaseAdapter{

    private Context context;
    private LayoutInflater inflater;
    private List<Musician> musicians;

    public ArtistsListAdapter(Context context, List<Musician> musicians) {
        this.context = context;
        this.musicians = musicians;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return musicians.size();
    }

    @Override
    public Object getItem(int position) {
        return musicians.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (null == view) {
            view = inflater.inflate(R.layout.list_view_item, parent, false);
        }

        Musician musician = (Musician) getItem(position);
        String albumsTracksNumText = musician.getNumberOfAlbums() + " альбомов, "
                + musician.getNumberOfTracks() + " песен";

        ((ImageView) view.findViewById(R.id.smallArtistImgView)).setImageResource(R.drawable.si);
        ((TextView) view.findViewById(R.id.musicianNameTxt)).setText(musician.getName());
        ((TextView) view.findViewById(R.id.genresTxt)).setText(String.valueOf(musician.getGenres()));
        ((TextView) view.findViewById(R.id.albumsGenresNumTxt)).setText(albumsTracksNumText);

        return view;
    }
}
