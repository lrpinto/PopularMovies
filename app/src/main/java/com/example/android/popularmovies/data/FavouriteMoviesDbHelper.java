package com.example.android.popularmovies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FavouriteMoviesDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "favouriteMovies.db";

    private static final int DATABASE_VERSION = 1;

    public FavouriteMoviesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_FAVOURITE_MOVIES_TABLE = "CREATE TABLE " +
                FavouriteMoviesContract.FavouriteMovieEntry.TABLE_NAME + " (" +
                FavouriteMoviesContract.FavouriteMovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FavouriteMoviesContract.FavouriteMovieEntry.COLUMN_MOVIE_ID + " INTEGER NOT NULL, " +
                FavouriteMoviesContract.FavouriteMovieEntry.COLUMN_ORIGINAL_TITLE + " TEXT NOT NULL, " +
                FavouriteMoviesContract.FavouriteMovieEntry.COLUMN_POSTER_PATH + " TEXT NOT NULL, " +
                FavouriteMoviesContract.FavouriteMovieEntry.COLUMN_BACKDROP_PATH + " TEXT NOT NULL, " +
                FavouriteMoviesContract.FavouriteMovieEntry.COLUMN_RELEASE_DATE + " TEXT NOT NULL, " +
                FavouriteMoviesContract.FavouriteMovieEntry.COLUMN_OVERVIEW + " TEXT NOT NULL, " +
                FavouriteMoviesContract.FavouriteMovieEntry.COLUMN_VOTE_AVERAGE + " REAL NOT NULL, " +
                " UNIQUE (" + FavouriteMoviesContract.FavouriteMovieEntry.COLUMN_MOVIE_ID + ") ON CONFLICT REPLACE);";

        sqLiteDatabase.execSQL(SQL_CREATE_FAVOURITE_MOVIES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FavouriteMoviesContract.FavouriteMovieEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
}
