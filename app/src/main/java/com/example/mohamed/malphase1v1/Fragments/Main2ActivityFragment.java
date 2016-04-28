package com.example.mohamed.malphase1v1.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mohamed.malphase1v1.Activities.ReviewsActivity;
import com.example.mohamed.malphase1v1.Activities.VideosActivity;
import com.example.mohamed.malphase1v1.R;
import com.example.mohamed.malphase1v1.SQLite.FavouriteMovie;
import com.squareup.picasso.Picasso;
import android.widget.Toast;
/**
 * A placeholder fragment containing a simple view.
 */
public class Main2ActivityFragment extends Fragment {

    public Main2ActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// return inflater.inflate(R.layout.fragment_main2, container, false);
        //Call XML Fragment
        View rootView=null;
        final    String moviePath2="http://image.tmdb.org/t/p/w500/";

        try {
            rootView = inflater.inflate(R.layout.fragment_main2, container, false);
            Log.e("SuccessCallingXML2", "Success Calling XML2");

        }catch(Exception ex){
            Log.e("ErrorCallFragmentXML2","ErrorCallingFragmentXML2"+ex.getMessage());
        }
        //Calling SharedPreferences
        //we need:title, release date, movie poster, vote average, and plot synopsis.

        try{
            final String Image_Pster_Path=getArguments().getString("Image_Poster_Path");
            final String ImageTitle=getArguments().getString("ImageTitle", "");
            final String VoteAverage=getArguments().getString("VoteAverage", "");
            final String ReleaseDate=getArguments().getString("ReleaseDate", "");
            final String plot_synopsis=getArguments().getString("plot_synopsis", "");
            final long ImageID=getArguments().getLong("ImageID");
            SharedPreferences sharedPreferences3=getActivity().getSharedPreferences("Shared_FilmID", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences3.edit();
            editor.putString("FilmID", String.valueOf(ImageID));
            editor.commit();
            /*SharedPreferences sharedPreferences2=getActivity().getSharedPreferences("SharedMovieInfo", Context.MODE_PRIVATE);
            final String Image_Pster_Path=sharedPreferences2.getString("Image_Poster_Path", "");
            final String ImageTitle=sharedPreferences2.getString("ImageTitle", "");
            final String VoteAverage=sharedPreferences2.getString("VoteAverage","");
            final String ReleaseDate=sharedPreferences2.getString("ReleaseDate","");
            final String plot_synopsis=sharedPreferences2.getString("plot_synopsis","");
            final long ImageID=sharedPreferences2.getLong("ImageID",1);

          */  //getViewsFromXML
            final TextView Image_Pster_Path_TextView= (TextView) rootView.findViewById(R.id.Image_Pster_Path);
            TextView ImageTitle_TextView= (TextView) rootView.findViewById(R.id.ImageTitle);
            TextView VoteAverage_TextView= (TextView) rootView.findViewById(R.id.VoteAverage);
            TextView ReleaseDateTextView= (TextView) rootView.findViewById(R.id.ReleaseDate);
            TextView plot_synopsis_TextView= (TextView) rootView.findViewById(R.id.plot_synopsis);
            ImageView MyImageInFragment2= (ImageView) rootView.findViewById(R.id.MyImageInFragment2);

            //Put Values From SharedPrefrences In Them
           //Image_Pster_Path_TextView.setText(Image_Pster_Path);
           ImageTitle_TextView.setText(ImageTitle);
            VoteAverage_TextView.setText(VoteAverage);//+"///ID:"+ImageID);
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

            Button AddToFavouriteFilmsButton= (Button) rootView.findViewById(R.id.ADDToFavouriteButton);
            //Add to DB

            AddToFavouriteFilmsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{//use Sigletoon design pattern
                        FavouriteMovie favouriteMovie=FavouriteMovie.getFavouriteMovieObject(getActivity());
                                //new FavouriteMovie(getActivity(),"FavouriteMovieDataBase",null,1);
                        long rowAffected=favouriteMovie.addFavouriteFilm(ImageID,ImageTitle,ReleaseDate,plot_synopsis,Image_Pster_Path,VoteAverage);
                        if(rowAffected!=-1){
                            Toast.makeText(getActivity(), "SuccesAddRow", Toast.LENGTH_LONG).show();
                            Log.e("SuccesAddRow", ":SuccesAddRow");
                        }else{

                            Toast.makeText(getActivity(), "SuccesAddRow(May be this film is already exist ^_^)", Toast.LENGTH_LONG).show();

                        }
                        Log.e("rowAffected", ""+rowAffected);

                    }

                    catch(Exception ex){
                        Toast.makeText(getActivity(),"Error DB"+ex.getMessage(),Toast.LENGTH_LONG).show();
                        Log.e("FailedAddRow", "FailedAddRow:" + ex.getMessage());
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

               Log.e("ReviewsButton",""+ex.getMessage());
           }
        }
        catch (Exception ex){
ex.printStackTrace();
            Log.e("",""+ex.getMessage());//if you don't write  may be you will have NULL POINTER Exception
        }


        return  rootView;
    }
}
