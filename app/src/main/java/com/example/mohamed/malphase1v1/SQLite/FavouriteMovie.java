package com.example.mohamed.malphase1v1.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.mohamed.malphase1v1.HelperClasses.MovieInfo;

import java.util.ArrayList;

/**
 * Created by mohamed on 4/16/2016.
 */
public class FavouriteMovie extends SQLiteOpenHelper {
    private  static  FavouriteMovie favouriteMovieObject;
    private    static  String TABLE_FavouriteFilms="MYFavouriteFilms";
    public  static  String Column_FilmTitle="FilmTitle";
    public  static  String Column_FilmID="ID";
    public  static  String Column_FilmReleaseDate="FilmReleaseDate";
    public  static  String Column_PosterPath="PosterPath";
    public  static  String Column_VoteAverage="VoteAverage";
    public  static  String Column_PlotSynopsis="PlotSynopsis";
    public static  int MYID=0;
    private static final int DATABASE_VERSION = 1;

    private  static  String CREATE_TABLE_FAVOURITE_FILM="CREATE TABLE "+TABLE_FavouriteFilms+
            " ("+Column_FilmID+" INT  PRIMARY KEY  ,"
           + Column_FilmTitle+" VARCHAR(100),"
            +Column_FilmReleaseDate+" VARCHAR(100),"
            +Column_PosterPath+" VARCHAR(200) ,"
            +Column_VoteAverage+" VARCHAR(100),"
            +Column_PlotSynopsis+" Text"


            +")";// PRIMARY KEY AUTO_INCREMENT

    //use Sigletoon design pattern
    public static synchronized FavouriteMovie getFavouriteMovieObject(Context context) {
        if(favouriteMovieObject==null){
            favouriteMovieObject=new FavouriteMovie(context,"MyFavouriteMovieDataBase",null,DATABASE_VERSION);
        }
        return favouriteMovieObject;
    }

    private FavouriteMovie(Context context, String DBname, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DBname, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FAVOURITE_FILM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public long addFavouriteFilm(long ImageID,String FilmTitle,String FilmReleaseDate,String PlotSynopsis,String PosterPath,String VoteAverage){
        ContentValues contentValues=new ContentValues();

        MYID++;
        contentValues.put(Column_FilmReleaseDate,FilmReleaseDate);
        contentValues.put(Column_FilmTitle,FilmTitle);
        contentValues.put(Column_PlotSynopsis,PlotSynopsis);
        contentValues.put(Column_PosterPath,PosterPath);
        contentValues.put(Column_VoteAverage,VoteAverage);
        contentValues.put(Column_FilmID,ImageID);

        SQLiteDatabase sqLiteDatabaseAddRow=getWritableDatabase();
       long rowAffected=0;
        try {
            rowAffected=sqLiteDatabaseAddRow.insert(TABLE_FavouriteFilms, null, contentValues);
        }
        catch (Exception ex){

            Log.e("InsertRow",ex.getMessage());
        }
        return rowAffected;
    }



    public Cursor ShowFavouriteMovies(){// ArrayList<MovieInfo>
        SQLiteDatabase sqLiteDatabaseShowUser=getReadableDatabase();
        String sql="select * from "+TABLE_FavouriteFilms+" Order By "+Column_FilmID+" desc ";
        Cursor cursor=sqLiteDatabaseShowUser.rawQuery(sql, null);
/*
        int Column_FilmTitleIndex;
        int Column_FilmIDIndex;
        int Column_FilmReleaseDateIndex;
        int Column_PosterPathIndex;
        int Column_VoteAverageIndex;
        int Column_PlotSynopsisIndex;
        ArrayList<MovieInfo> movieInfoArrayListMyAdaptor=null;
//    public MovieInfo(String poster_path,
// Boolean adult, String release_date,
// double id, String original_title,
// String title, String backdrop_path, String popularity, double vote_count, boolean video, String vote_average) {

        while(cursor.moveToNext()){
            Column_FilmReleaseDateIndex= cursor.getColumnIndex(Column_FilmReleaseDate);
            String Column_FilmReleaseDateName=cursor.getString(Column_FilmReleaseDateIndex);

            Column_FilmTitleIndex= cursor.getColumnIndex(Column_FilmTitle);
            String Column_FilmTitleIndexName=cursor.getString(Column_FilmTitleIndex);

            Column_PosterPathIndex= cursor.getColumnIndex(Column_PosterPath);
            String Column_PosterPathIndexName=cursor.getString(Column_PosterPathIndex);

            Column_PlotSynopsisIndex= cursor.getColumnIndex(Column_PlotSynopsis);
            String Column_PlotSynopsisIndexName=cursor.getString(Column_PlotSynopsisIndex);

            Column_VoteAverageIndex= cursor.getColumnIndex(Column_VoteAverage);
            String Column_VoteAverageIndexName=cursor.getString(Column_VoteAverageIndex);

            movieInfoArrayListMyAdaptor.add(new MovieInfo(Column_PosterPathIndexName,true,Column_FilmReleaseDateName,
             1,Column_FilmTitleIndexName,Column_FilmTitleIndexName,"",Column_PlotSynopsisIndexName,1,true,Column_VoteAverageIndexName));


        }*/
  return  cursor;
       // cursor.close();
        //sqLiteDatabaseShowUser.close();
//return movieInfoArrayListMyAdaptor;
    }


    public int deleteFavouriteFilm(String PosterPath,long ID){
        String where = Column_FilmID+" = "+""+ID+"";
        String whereArgs[] = null;
        SQLiteDatabase sqLiteDatabaseDeleteRow=getWritableDatabase();
       int rowFfected= sqLiteDatabaseDeleteRow.delete(TABLE_FavouriteFilms,where,whereArgs);
return rowFfected;
    }
}
