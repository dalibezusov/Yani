package com.yani.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yani.R;

public class ArtistDetailActivity extends AppCompatActivity {

    private ImageView bigArtistImg;
    private TextView genresText;
    private TextView albumsTracksText;
    private TextView biographyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artists_detail_layout);

        bigArtistImg = (ImageView) findViewById(R.id.bigArtistImgView);
        genresText = (TextView) findViewById(R.id.detailGenresTxt);
        albumsTracksText = (TextView) findViewById(R.id.detailAlbumsTracksTxt);
        biographyText = (TextView) findViewById(R.id.biographyTxt);

        /*Picasso.with(this).load(getIntent().getExtras().getString(ExtrasKeys.LINK_TO_BIG_IMG)).into(bigArtistImg);*/
        Picasso.with(this).load(getIntent().getExtras().getString(ExtrasKeys.LINK_TO_BIG_IMG)).into(bigArtistImg);
        setTitle(getIntent().getExtras().getString(ExtrasKeys.NAME));
        genresText.setText(getIntent().getExtras().getString(ExtrasKeys.GENRES));
        albumsTracksText.setText(getIntent().getExtras().getString(ExtrasKeys.ALBUMS_TRACKS));
        biographyText.setText(getIntent().getExtras().getString(ExtrasKeys.BIOGRAPHY));
    }
}
