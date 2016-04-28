package com.example.mohamed.malphase1v1.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohamed.malphase1v1.Activities.FavouriteFilmsActivityShowOneImage;
import com.example.mohamed.malphase1v1.Activities.Main2Activity;
import com.example.mohamed.malphase1v1.HelperClasses.*;
import com.example.mohamed.malphase1v1.R;
import com.example.mohamed.malphase1v1.SQLite.FavouriteMovie;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class FavouriteFilmsActivityFragment extends Fragment {
    static MyImageViewAdapter myAdapterObjectFavouriteFilms;
    GridView mygridviewFavouriteFilms;
    TextView errorDBDisplayFavouriteFilms;
    ArrayList<MovieInfo> movieInfoArrayListFavouriteFilms;
    MovieListenerClass movieListenerClass;

    public FavouriteFilmsActivityFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Call XML Fragment
        View rootView=null;
        try {
            rootView = inflater.inflate(R.layout.fragment_favourite_films, container, false);
            Log.e("SuccessCallingXML", "Success Calling XML(fragment_favourite_films)");
            //Call Grid View
            mygridviewFavouriteFilms = (GridView) rootView.findViewById(R.id.gridViewFavouriteFilms);
            errorDBDisplayFavouriteFilms= (TextView) rootView.findViewById(R.id.ErrorFavouriteFilms);
            movieInfoArrayListFavouriteFilms=new ArrayList<MovieInfo>();
        }catch(Exception ex){
            Log.e("ErrorCallingFragmentXML","ErrorCallingFragmentXML(fragment_favourite_films)"+ex.getMessage());
        }

        //Call DB Object
       // ArrayList<MovieInfo> movieInfoArrayListFavouriteFilms = null;
        FavouriteMovie favouriteMovieShow=null;
        Cursor cursor=null;
        try{//use Sigletoon design pattern
            favouriteMovieShow=FavouriteMovie.getFavouriteMovieObject(getActivity());
            //new FavouriteMovie(getActivity(),"FavouriteMovieDataBase",null,1);
           // Toast.makeText(getActivity(), "SuccesgetDBObject", Toast.LENGTH_LONG).show();
            Log.e("SuccesgetDBObject", ":SuccesgetDBObject" );

       }

       catch(Exception ex){
            Toast.makeText(getActivity(),"FailedShowFF"+ex.getMessage(),Toast.LENGTH_LONG).show();
            Log.e("FailedShowFF", "FailedShowFF:" + ex.getMessage());
            errorDBDisplayFavouriteFilms.setText(ex.getMessage());

        }

        try {
            cursor = favouriteMovieShow.ShowFavouriteMovies();
            int i = 0;


            int Column_FilmTitleIndex;
            int Column_FilmReleaseDateIndex;
            int Column_PosterPathIndex;
            int Column_VoteAverageIndex;
            int Column_PlotSynopsisIndex;
            int ColumnIDIndex;
//    public MovieInfo(String poster_path,
// Boolean adult, String release_date,
// double id, String original_title,
// String title, String backdrop_path, String popularity, double vote_count, boolean video, String vote_average) {
if(!cursor.moveToFirst() || cursor.getCount() == 0){
    errorDBDisplayFavouriteFilms.setWidth(50);
    errorDBDisplayFavouriteFilms.setHeight(50);

    errorDBDisplayFavouriteFilms.setText("There is No Favourite Films till Now  ^_^ ");
    Toast.makeText(getActivity(),"There is No Favourite Films tii Now  ^_^ ",Toast.LENGTH_LONG).show();


}else{
            while (cursor.moveToNext()) {
                i++;
                Log.e("", i + cursor.getColumnName(0));
                Log.e("", "ID:" + cursor.getInt(0));

                Log.e("", i + cursor.getColumnName(1));
                Log.e("", "FilmTitle" + cursor.getString(1));

                Log.e("", i + cursor.getColumnName(2));
                Log.e("", "FilmReleaseDate:" + cursor.getString(2));


                Log.e("", i + cursor.getColumnName(3));
                Log.e("", "PosterPath:" + cursor.getString(3));

                Log.e("", i + cursor.getColumnName(4));
                Log.e("", "VoteAverag:" + cursor.getString(4));
                Log.e("", i + cursor.getColumnName(5));
                Log.e("", "PlotSynopsis:" + cursor.getString(5));
                try {
                    movieInfoArrayListFavouriteFilms.add(new MovieInfo(cursor.getString(3), true, cursor.getString(2),
                            cursor.getLong(0), cursor.getString(1), cursor.getString(1), "", cursor.getString(5), 1, true, cursor.getString(4)));
                    Log.e("", "movieInfoArrayListFavouriteFilms:Success");

                } catch (Exception ex) {
                    Log.e("", "movieInfoArrayListFavouriteFilms:" + ex.getMessage());
                    errorDBDisplayFavouriteFilms.setText(ex.getMessage());

                }


  /*              ColumnIDIndex= cursor.getColumnIndex(FavouriteMovie.Column_FilmID);
                int Column_FilmIDIndexName=cursor.getInt(ColumnIDIndex);
               Log.e("", "ColumnIDIndex:"+ColumnIDIndex);
                Log.e("", "Column_FilmIDIndexName:"+i+Column_FilmIDIndexName);

                Column_FilmTitleIndex= cursor.getColumnIndex(FavouriteMovie.Column_FilmTitle);
                String Column_FilmTitleIndexName=cursor.getString(Column_FilmTitleIndex);
                Log.e("", "Column_FilmTitleIndex:"+Column_FilmTitleIndex);
                Log.e("", "Column_FilmTitleIndexName:"+i+Column_FilmTitleIndexName);

                Column_FilmReleaseDateIndex= cursor.getColumnIndex(FavouriteMovie.Column_FilmReleaseDate);
                String Column_FilmReleaseDateName=cursor.getString(Column_FilmReleaseDateIndex);
                Log.e("", "Column_FilmReleaseDateIndex:"+Column_FilmReleaseDateIndex);
                Log.e("", "Column_FilmReleaseDateName:"+i+Column_FilmReleaseDateName);



                Column_PosterPathIndex= cursor.getColumnIndex(FavouriteMovie.Column_PosterPath);
                String Column_PosterPathIndexName=cursor.getString(Column_PosterPathIndex);
                Log.e("", "Column_PosterPathIndex:"+Column_PosterPathIndex);
                Log.e("", "Column_PosterPathIndexName:"+i+Column_PosterPathIndexName);

                Column_VoteAverageIndex= cursor.getColumnIndex(FavouriteMovie.Column_VoteAverage);
                String Column_VoteAverageIndexName=cursor.getString(Column_VoteAverageIndex);
                Log.e("", "Column_VoteAverageIndex:"+Column_VoteAverageIndex);
                Log.e("", "Column_VoteAverageIndexName:"+i+Column_VoteAverageIndexName);

                Column_PlotSynopsisIndex= cursor.getColumnIndex(FavouriteMovie.Column_PlotSynopsis);
                String Column_PlotSynopsisIndexName=cursor.getString(Column_PlotSynopsisIndex);
                Log.e("", "Column_PlotSynopsisIndex:"+Column_PlotSynopsisIndex);
                Log.e("", "Column_PlotSynopsisIndexName:"+i+Column_PlotSynopsisIndexName);

                movieInfoArrayListFavouriteFilms.add(new MovieInfo(Column_PosterPathIndexName,true,Column_FilmReleaseDateName,
                        Column_FilmIDIndexName,Column_FilmTitleIndexName,Column_FilmTitleIndexName,"",Column_PlotSynopsisIndexName,1,true,Column_VoteAverageIndexName));

*/
                //   Toast.makeText(getActivity(),"SuccessShowFavouriteFilms", Toast.LENGTH_LONG).show();
                Log.e("SuccessShowFF", "SuccessShowFavouriteFilms:");


            }//end of while
        }//end of if cursor not null

          //  else{}
        }   catch(Exception ex){
           Toast.makeText(getActivity(),"FailedShowFavouriteFilms",Toast.LENGTH_LONG).show();
            Log.e("FailedShowFF", "FailedShowFavouriteFilms:" + ex.getMessage());
            errorDBDisplayFavouriteFilms.setText(ex.getMessage());

        }

    //Set the Arraylist into the adapter and set it into the Grid View

        try{

            if(movieInfoArrayListFavouriteFilms!=null){

                  myAdapterObjectFavouriteFilms = new MyImageViewAdapter(getActivity(), movieInfoArrayListFavouriteFilms);
                   mygridviewFavouriteFilms.setAdapter(myAdapterObjectFavouriteFilms);
                Log.e("Success", "MyImageViewAdapterFavouriteFilms:" );

            }else{                Log.e("Success", "MyImageViewAdapterFavouriteFilms:But it is Empty");
            }
        } catch (Exception ex) {
            Log.e("Failed", "MyImageViewAdapterFavouriteFilms:" + ex.getMessage());


        }
        //Calling OnClickListener(if user click on one image)
        try{
////we need:title, release date, movie poster, vote average, and plot synopsis.
            final ViewHolderClass[] viewHolderClass = {new ViewHolderClass()};

            mygridviewFavouriteFilms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Calling viewHolderObject that we put it in (convertview)
                     viewHolderClass[0]= (ViewHolderClass) view.getTag();

                  //  Toast.makeText(getActivity(),"position is"+position+" Id is:"+id,Toast.LENGTH_LONG).show();
                    Log.e("Position&ID", " position is" + position + " Id is:" + id);
                    Log.e("TryCallFromViewHolder",viewHolderClass[0].getMovieInfo().getTitle());
/*
                    //Put this object in SharedPrefrence
                    SharedPreferences sharedPreferences=getActivity().getSharedPreferences("SharedMovieInfo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("ImageTitle",viewHolderClass[0].getMovieInfo().getTitle());
                    editor.putString("VoteAverage",viewHolderClass[0].getMovieInfo().getVote_average());
                    editor.putString("Image_Poster_Path",viewHolderClass[0].getMovieInfo().getPoster_path());
                    editor.putString("ReleaseDate",viewHolderClass[0].getMovieInfo().getRelease_date());
                    editor.putString("plot_synopsis", viewHolderClass[0].getMovieInfo().getPopularity());
                    editor.putLong("ImageID", viewHolderClass[0].getMovieInfo().getId());

                    editor.commit();*/
                    //Go to AnotherActivity
                  /*  Intent intent=new Intent(FavouriteFilmsActivityFragment.super.getActivity() , FavouriteFilmsActivityShowOneImage.class);
                    startActivity(intent);*/
                    try{   movieListenerClass.setSelectedMovieInfo(
                            viewHolderClass[0].getMovieInfo().getTitle(), viewHolderClass[0].getMovieInfo().getVote_average(),
                            viewHolderClass[0].getMovieInfo().getPoster_path(), viewHolderClass[0].getMovieInfo().getRelease_date(),
                            viewHolderClass[0].getMovieInfo().getPopularity(),
                            viewHolderClass[0].getMovieInfo().getId()

                    );}catch(Exception ex){
                        Toast.makeText(getActivity(),"Error(movieListenerClass):"+ex.getMessage(),Toast.LENGTH_LONG).show();

                        Log.e("movieListenerClass",ex.getMessage());

                    }

                }
            });}
        catch(Exception ex){}

        return  rootView;
    }
    public void setMovieListenerClass(MovieListenerClass movieListenerClass) {
        this.movieListenerClass = movieListenerClass;
    }
}
