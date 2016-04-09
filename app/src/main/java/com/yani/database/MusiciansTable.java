package com.yani.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.yani.content.Cover;
import com.yani.content.Musician;

import java.util.ArrayList;
import java.util.List;

public class MusiciansTable {

    public static final Uri URI = SqliteHelper.BASE_CONTENT_URI.buildUpon().appendPath(Requests.TABLE_NAME).build();

    public static void save(Context context, @NonNull Musician musician) {
        context.getContentResolver().insert(URI, toContentValues(musician));
    }

    public static void save(Context context, List<Musician> musicians) {
        ContentValues[] values = new ContentValues[musicians.size()];
        for (int i = 0; i < musicians.size(); i++) {
            values[i] = toContentValues(musicians.get(i));
        }
        context.getContentResolver().bulkInsert(URI, values);
    }

    @NonNull
    public static ContentValues toContentValues(@NonNull Musician musician) {
        ContentValues values = new ContentValues();

        values.put(Columns.MUS_ID, musician.getId());
        values.put(Columns.NAME, musician.getName());

        String genres = "";
        if (musician.getGenres() != null) {
            for (int i = 0; i < musician.getGenres().size(); i++) {
                genres = genres + musician.getGenres().get(i) + "#";
            }
        }
        values.put(Columns.GENRES, genres);

        values.put(Columns.NUM_OF_TRACKS, musician.getNumberOfTracks());
        values.put(Columns.NUM_OF_ALBUMS, musician.getNumberOfAlbums());
        values.put(Columns.LINK, musician.getLinkToWebPage());
        values.put(Columns.DESCRIPTION, musician.getDescription());
        values.put(Columns.SMALL_COVER, musician.getCover().getLinkToSmallCover());
        values.put(Columns.BIG_COVER, musician.getCover().getLinkToBigCover());

        return values;
    }

    @NonNull
    public static Musician fromCursor(@NonNull Cursor cursor) {

        int mus_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Columns.MUS_ID)));
        String name = cursor.getString(cursor.getColumnIndex(Columns.NAME));

        List<String> genresList = makeStringList(cursor.getString(cursor.getColumnIndex(Columns.GENRES)));

        int numOfTracks = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Columns.NUM_OF_TRACKS)));
        int numOfAlbums = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Columns.NUM_OF_ALBUMS)));
        String link = cursor.getString(cursor.getColumnIndex(Columns.LINK));
        String description = cursor.getString(cursor.getColumnIndex(Columns.DESCRIPTION));

        String small_cover = cursor.getString(cursor.getColumnIndex(Columns.SMALL_COVER));
        String big_cover = cursor.getString(cursor.getColumnIndex(Columns.BIG_COVER));
        Cover cover = new Cover(small_cover, big_cover);

        return new Musician(mus_id, name, genresList, numOfTracks, numOfAlbums, link, description, cover );
    }

    public static List<String> makeStringList(String genresStr) {
        List<String> genresList = new ArrayList<>();
        String genre = "";

        /*Log.i(Tags.INFO_TAG, "genresStr.toCharArray().length: " + genresStr.toCharArray().length);
        Log.i(Tags.INFO_TAG, "genresStr.length(): " + genresStr.length());*/
        if (genresStr != null) {
            for (int i = 0; i < genresStr.length(); i++) {
                if ('#' == genresStr.charAt(i)) {
                /*Log.i(Tags.INFO_TAG, "-");*/
                    genresList.add(genre);
                    genre = "";
                } else {
                    /*Log.i(Tags.INFO_TAG, "+");*/
                    /*genre = genre.concat(String.valueOf(genresStr.charAt(i)));*/
                    genre = genre + String.valueOf(genresStr.charAt(i));
                }
            }
        }

        return genresList;
    }

    @NonNull
    public static List<Musician> listFromCursor(@NonNull Cursor cursor) {
        List<Musician> musicians = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return musicians;
        }
        try {
            do {
                musicians.add(fromCursor(cursor));
            } while (cursor.moveToNext());
            return musicians;
        } finally {
            cursor.close();
        }
    }

    public static void clear(Context context) {
        context.getContentResolver().delete(URI, null, null);
    }

    public interface Columns {

        String MUS_ID = "mus_id";
        String NAME = "name";
        String GENRES = "genres";
        String NUM_OF_TRACKS = "num_of_tracks";
        String NUM_OF_ALBUMS = "num_of_albums";
        String LINK = "link";
        String DESCRIPTION = "description";
        String SMALL_COVER = "small_cover";
        String BIG_COVER = "big_cover";

    }

    public interface Requests {

        String TABLE_NAME = MusiciansTable.class.getSimpleName();

        String CREATION_REQUEST = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                Columns.MUS_ID + " INTEGER, " +
                Columns.NAME + " TEXT, " +
                Columns.GENRES + " TEXT, " +
                Columns.NUM_OF_TRACKS + " INTEGER, " +
                Columns.NUM_OF_ALBUMS + " INTEGER, " +
                Columns.LINK + " TEXT, " +
                Columns.DESCRIPTION + " TEXT, " +
                Columns.SMALL_COVER + " TEXT, " +
                Columns.BIG_COVER + " TEXT" + ");";

        String DROP_REQUEST = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
