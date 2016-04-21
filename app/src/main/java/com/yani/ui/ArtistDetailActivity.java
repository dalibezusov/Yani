package com.yani.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.yani.R;

public class ArtistDetailActivity extends AppCompatActivity {

    private ImageView bigArtistImg;
    private TextView genresText;
    private TextView albumsTracksText;
    private TextView biographyHeaderText;
    private TextView biographyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artists_detail_layout);

        bigArtistImg = (ImageView) findViewById(R.id.bigArtistImgView);
        genresText = (TextView) findViewById(R.id.detailGenresTxt);
        albumsTracksText = (TextView) findViewById(R.id.detailAlbumsTracksTxt);
        biographyHeaderText = (TextView) findViewById(R.id.biographyHeaderTxt);
        biographyText = (TextView) findViewById(R.id.biographyTxt);

        setTitle(getIntent().getExtras().getString(ExtrasKeys.NAME));
        genresText.setText(getIntent().getExtras().getString(ExtrasKeys.GENRES));
        albumsTracksText.setText(getIntent().getExtras().getString(ExtrasKeys.ALBUMS_TRACKS));
        biographyText.setText(getIntent().getExtras().getString(ExtrasKeys.BIOGRAPHY));
    }
}
