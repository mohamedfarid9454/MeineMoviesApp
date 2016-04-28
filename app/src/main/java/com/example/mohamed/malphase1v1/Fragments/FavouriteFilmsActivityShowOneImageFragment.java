package com.example.mohamed.malphase1v1.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohamed.malphase1v1.Activities.FavouriteFilmsActivity;
import com.example.mohamed.malphase1v1.Activities.FavouriteFilmsActivityShowOneImage;
import com.example.mohamed.malphase1v1.Activities.ReviewsActivity;
import com.example.mohamed.malphase1v1.Activities.VideosActivity;
import com.example.mohamed.malphase1v1.R;
import com.example.mohamed.malphase1v1.SQLite.FavouriteMovie;
import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class FavouriteFilmsActivityShowOneImageFragment extends Fragment {

    public FavouriteFilmsActivityShowOneImageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// return inflater.inflate(R.layout.fragment_main2, container, false);
        //Call XML Fragment
        View rootView=null;
        final    String moviePath2="http://image.tmdb.org/t/p/w185/";

        try {
            rootView = inflater.inflate(R.layout.fragment_favourite_films_activity_show_one_image, container, false);
            Log.e("SuccessCallingXML2", "Success Calling XML2");

        }catch(Exception ex){
            Log.e("ErrorCallFragmentXML2","ErrorCallingFragmentXML2"+ex.getMessage());
        }
        //Calling SharedPreferences
        //we need:title, release date, movie poster, vote average, and plot synopsis.

        try{/*
            SharedPreferences sharedPreferences2=getActivity().getSharedPreferences("SharedMovieInfo", Context.MODE_PRIVATE);
            final String Image_Pster_Path=sharedPreferences2.getString("Image_Poster_Path", "");
            final String ImageTitle=sharedPreferences2.getString("ImageTitle", "");
            final String VoteAverage=sharedPreferences2.getString("VoteAverage","");
            final String ReleaseDate=sharedPreferences2.getString("ReleaseDate","");
            final String plot_synopsis=sharedPreferences2.getString("plot_synopsis","");
            final long ImageID=sharedPreferences2.getLong("ImageID", 1);
*/           final String Image_Pster_Path=getArguments().getString("Image_Poster_Path");
            final String ImageTitle=getArguments().getString("ImageTitle", "");
            final String VoteAverage=getArguments().getString("VoteAverage", "");
            final String ReleaseDate=getArguments().getString("ReleaseDate", "");
            final String plot_synopsis=getArguments().getString("plot_synopsis", "");
            final long ImageID=getArguments().getLong("ImageID");
            SharedPreferences sharedPreferences3=getActivity().getSharedPreferences("Shared_FilmID", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences3.edit();
            editor.putString("FilmID", String.valueOf(ImageID));
            editor.commit();

            //getViewsFromXML
            final TextView Image_Pster_Path_TextView= (TextView) rootView.findViewById(R.id.Image_Pster_Path);
            TextView ImageTitle_TextView= (TextView) rootView.findViewById(R.id.ImageTitle);
            TextView VoteAverage_TextView= (TextView) rootView.findViewById(R.id.VoteAverage);
            TextView ReleaseDateTextView= (TextView) rootView.findViewById(R.id.ReleaseDate);
            TextView plot_synopsis_TextView= (TextView) rootView.findViewById(R.id.plot_synopsis);
            ImageView MyImageInFragment2= (ImageView) rootView.findViewById(R.id.MyImageInFragment2);

            //Put Values From SharedPrefrences In Them
           // Image_Pster_Path_TextView.setText(Image_Pster_Path);
            ImageTitle_TextView.setText(ImageTitle);
            VoteAverage_TextView.setText(VoteAverage);//+"///ImageID:"+ImageID);
            ReleaseDateTextView.setText(ReleaseDate);
            plot_synopsis_TextView.setText(plot_synopsis);

            Picasso.with(getActivity())
                    .load(moviePath2+Image_Pster_Path)
                    .placeholder(R.drawable.aaa) // optional
                    .error(R.drawable.bbb)         // optional
                            //        .resize(250, 200)                        // optional
                            //      .rotate(90)
                    .into(MyImageInFragment2);

            Log.e("Success:ImagePath", ":" + Image_Pster_Path);
            Log.e("Success:ImageTitle", ImageTitle);
            Log.e("Success:VoteAverage", VoteAverage);

            final Button DeleteFavouriteFilmsButton= (Button) rootView.findViewById(R.id.DeleteFromFavouriteButton);
            //Add to DB

            DeleteFavouriteFilmsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        FavouriteMovie favouriteMovie=FavouriteMovie.getFavouriteMovieObject(getActivity());
                        // new FavouriteMovie(getActivity(),"FavouriteMovieDataBase",null,1);
                        int rowAffected=favouriteMovie.deleteFavouriteFilm(Image_Pster_Path,ImageID);
                        if(rowAffected>0){
                            Toast.makeText(getActivity(), "SuccesDeletRow", Toast.LENGTH_LONG).show();
                            Log.e("SuccesDeletRow", ":SuccesDeletRow");
                            DeleteFavouriteFilmsButton.setVisibility(View.INVISIBLE);
                            //Go to AnotherActivity
                            Intent intent=new Intent(FavouriteFilmsActivityShowOneImageFragment.super.getActivity() , FavouriteFilmsActivity.class);
                            startActivity(intent);
                        }else{

                            Toast.makeText(getActivity(), "FailedDeleteRow", Toast.LENGTH_LONG).show();

                        }
                        Log.e("rowAffected", ""+rowAffected);

                    }

                    catch(Exception ex){
                        Toast.makeText(getActivity(),"Error DB"+ex.getMessage(),Toast.LENGTH_LONG).show();
                        Log.e("FailedDeleteRow", "FailedDeleteRow:" + ex.getMessage());
                        Image_Pster_Path_TextView.setText(ex.getMessage());

                    }
                }
            });
            Button ViedosButton= (Button) rootView.findViewById(R.id.MovieVideoButton);
            ViedosButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(), VideosActivity.class);
                    startActivity(intent);

                }
            });

            Button ReviewsButton= (Button) rootView.findViewById(R.id.MovieReviewButton);
            try {
                ReviewsButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), ReviewsActivity.class);
                        startActivity(intent);
                    }
                });
            }  catch (Exception ex){

                Log.e("ReviewsButton",ex.getMessage());
            }

            Button ShareButton= (Button) rootView.findViewById(R.id.ShareOverView);
            try {
                ShareButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //create the send intent
                        Intent shareIntent =
                                new Intent(android.content.Intent.ACTION_SEND);

//set the type
                        shareIntent.setType("text/plain");//video/dl

//add a subject
                        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                                ImageTitle );

//build the body of the message to be shared
                        String shareMessage = "Insert message body here.";

//add the message
                        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,
                                shareMessage);

//start the chooser for sharing
                        startActivity(Intent.createChooser(shareIntent,
                                plot_synopsis));
                    }
                });
            }  catch (Exception ex){

                Log.e("ShaeButton",ex.getMessage());
            }
        }
        catch (Exception ex){}


        return  rootView;
    }
}
