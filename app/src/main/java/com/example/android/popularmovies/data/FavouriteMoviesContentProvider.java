package com.example.android.popularmovies.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class FavouriteMoviesContentProvider extends ContentProvider {

    public static final int FAVOURITE_MOVIES = 100;
    public static final int FAVOURITE_MOVIES_WITH_ID = 101;
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private FavouriteMoviesDbHelper favouriteMoviesDbHelper;

    public static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(FavouriteMoviesContract.AUTHORITY, FavouriteMoviesContract.PATH_FAVOURITE_MOVIES, FAVOURITE_MOVIES);

        uriMatcher.addURI(FavouriteMoviesContract.AUTHORITY, FavouriteMoviesContract.PATH_FAVOURITE_MOVIES + "/#", FAVOURITE_MOVIES_WITH_ID);

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {

        Context context = getContext();
        favouriteMoviesDbHelper = new FavouriteMoviesDbHelper(context);

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projections, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        final SQLiteDatabase favouriteMoviesDB = favouriteMoviesDbHelper.getReadableDatabase();

        int match = sUriMatcher.match(uri);

        Cursor returnCursor;

        switch (match) {
            case FAVOURITE_MOVIES:
                returnCursor = favouriteMoviesDB.query(
                        FavouriteMoviesContract.FavouriteMovieEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        FavouriteMoviesContract.FavouriteMovieEntry.COLUMN_VOTE_AVERAGE
                );
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        returnCursor.setNotificationUri(getContext().getContentResolver(), uri);

        return returnCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        final SQLiteDatabase favouriteMoviesDB = favouriteMoviesDbHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);

        Uri returnUri;

        switch (match) {
            case FAVOURITE_MOVIES:
                long id = favouriteMoviesDB.insert(FavouriteMoviesContract.FavouriteMovieEntry.TABLE_NAME, null, contentValues);
                if (id > 0) {
                    returnUri = ContentUris.withAppendedId(FavouriteMoviesContract.FavouriteMovieEntry.CONTENT_URI, id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into: " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
