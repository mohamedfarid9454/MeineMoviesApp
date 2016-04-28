package com.example.mohamed.malphase1v1.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.content.Intent;
import android.graphics.Bitmap;
import android.app.ProgressDialog;

import com.example.mohamed.malphase1v1.Activities.FavouriteFilmsActivity;
import com.example.mohamed.malphase1v1.Activities.Main2Activity;
import com.example.mohamed.malphase1v1.HelperClasses.MovieListenerClass;
import com.example.mohamed.malphase1v1.HelperClasses.MyImageViewAdapter;
import com.example.mohamed.malphase1v1.R;
import com.example.mohamed.malphase1v1.HelperClasses.URLConnectionAsyncTask;
import com.example.mohamed.malphase1v1.HelperClasses.ViewHolderClass;
import com.example.mohamed.malphase1v1.SQLite.FavouriteMovie;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    Bitmap bitmap;
    ProgressDialog pDialog;
      ImageView mypost_image;
MovieListenerClass movieListenerClass=new MovieListenerClass() {
    @Override
    public void setSelectedMovieInfo(String title, String vote_average, String poster_path, String release_date, String popularity, long id) {

    }
};
   // ArrayList<MovieInfo> movieInfoArrayListMyFragment;
    MyImageViewAdapter myAdapterObject;
    GridView mygridview;
    public MainActivityFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);
    }



    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
       // return inflater.inflate(R.layout.fragment_main, container, false);
        //Call XML Fragment
        View rootView=null;
        try {
             rootView = inflater.inflate(R.layout.fragment_main, container, false);
            // View rootView2=inflater.inflate(R.layout.gridviewtext, container, false);
            Log.e("SuccessCallingXML","Success Calling XML(fragment_main)");
            //Call Grid View
            mygridview = (GridView) rootView.findViewById(R.id.gridView);
        }catch(Exception ex){
            Log.e("ErrorCallingFragmentXML","ErrorCallingFragmentXML(fragment_main)"+ex.getMessage());
        }

      /*  Button FavouriteFilms= (Button) rootView.findViewById(R.id.FavouriteFilms);
        FavouriteFilms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivityFragment.super.getActivity() , FavouriteFilmsActivity.class);
                startActivity(intent);

/*
                FavouriteMovie favouriteMovie=new FavouriteMovie(getActivity(),"MyMovieDB",null,1);
                favouriteMovie.deleteFavouriteFilm("/tQwnjuyFUCF5Bic67QTBo60GteY.jpg",3);
            }

        });*/
        //Spinners
        Spinner myspinner= (Spinner) rootView.findViewById(R.id.myspinner);
        ArrayAdapter<CharSequence> arrayAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.items_spinner, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner.setAdapter(arrayAdapter);

        //SpinneOnitemListener

        myspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String myFilmsType = (String) parent.getItemAtPosition(position);
                if (myFilmsType.equals("Favourite")) {
                    Intent intent=new Intent(MainActivityFragment.super.getActivity() , FavouriteFilmsActivity.class);
                    startActivity(intent);
                } else if (myFilmsType.equals("top_rated")||myFilmsType.equals("popular")) {
                    try {

                        URLConnectionAsyncTask myasyncTask = new URLConnectionAsyncTask(getActivity(), myAdapterObject, mygridview);//movieInfoArrayListMyFragment
                        myasyncTask.execute("", myFilmsType);
                        Log.e("SuccessExcuteAsyncTask", "Success Excute AsyncTask");

                    } catch (Exception ex) {
                        Log.e("ErrorCallingAsyncTask", "SuccessCallingAsyncTask" + ex.getMessage());

                        Toast.makeText(getActivity(), "Asynctask URL Error::" + ex.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });/*
           // movieInfoArrayListMyFragment=new ArrayList<MovieInfo>();
        URLConnectionAsyncTask myasyncTask=new URLConnectionAsyncTask(getActivity(),myAdapterObject,mygridview);//movieInfoArrayListMyFragment
        try {
            SharedPreferences sharedPreferences3=getActivity().getSharedPreferences("Shared_API_AndFilmsType", Context.MODE_PRIVATE);
            String film_api=sharedPreferences3.getString("film_api","");
            String films_list_type=sharedPreferences3.getString("films_list_type","");//popular,top_rated

            myasyncTask.execute(film_api,films_list_type);
            Log.e("SuccessExcuteAsyncTask", "Success Excute AsyncTask");

        }
        catch (Exception ex){
            Log.e("ErrorCallingAsyncTask","SuccessCallingAsyncTask"+ex.getMessage());

            Toast.makeText(getActivity(),"Asynctask URL Error::"+ex.getMessage(),Toast.LENGTH_LONG).show();
        }
*/
        try{

            mygridview.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(view!=null) {
                        ViewHolderClass viewHolderClass = (ViewHolderClass) view.getTag();

                      //  Toast.makeText(getActivity(), "Title:" + viewHolderClass.getMovieInfo().getTitle()+"Voting:" + viewHolderClass.getMovieInfo()+"Release Date::" + viewHolderClass.getMovieInfo().getRelease_date(), Toast.LENGTH_LONG).show();
                       }


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                   // Toast.makeText(getActivity(),"Welcome ^_^",Toast.LENGTH_LONG).show();

                }
            });
        }
        catch(Exception ex){
            Toast.makeText(getActivity(),"Error:"+ex.getMessage(),Toast.LENGTH_LONG).show();

        }
        //Calling OnClickListener(if user click on one image)
        try{
////we need:title, release date, movie poster, vote average, and plot synopsis.
            final ViewHolderClass[] viewHolderClass = {new ViewHolderClass()};

            mygridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               //Calling viewHolderObject that we put it in (convertview)
                 viewHolderClass[0] = (ViewHolderClass) view.getTag();

                //Toast.makeText(getActivity(),"position is"+position+" Id is:"+id,Toast.LENGTH_LONG).show();
                Log.e("Position&ID", " position is" + position + " Id is:" + id);
                Log.e("TryCallFromViewHolder", viewHolderClass[0].getMovieInfo().getTitle());

                //Put this object in SharedPrefrence
                SharedPreferences sharedPreferences=getActivity().getSharedPreferences("SharedMovieInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("ImageTitle", viewHolderClass[0].getMovieInfo().getTitle());
                editor.putString("VoteAverage", viewHolderClass[0].getMovieInfo().getVote_average());
                editor.putString("Image_Poster_Path", viewHolderClass[0].getMovieInfo().getPoster_path());
                editor.putString("ReleaseDate", viewHolderClass[0].getMovieInfo().getRelease_date());
                editor.putString("plot_synopsis", viewHolderClass[0].getMovieInfo().getPopularity());
                editor.putLong("ImageID", viewHolderClass[0].getMovieInfo().getId());

                editor.commit();

              //Get this film and send it to MainActivity(it will display it if tablet or go to DetailActivity(Main2Activity) if mobile)
                //movieListenerClass.setSelectedMovieInfo(viewHolderClass.getMovieInfo());


               /* //Go to AnotherActivity
                Intent intent=new Intent(MainActivityFragment.super.getActivity() , Main2Activity.class);
                startActivity(intent);
*/
             try{   movieListenerClass.setSelectedMovieInfo(
                        viewHolderClass[0].getMovieInfo().getTitle(), viewHolderClass[0].getMovieInfo().getVote_average(),
                        viewHolderClass[0].getMovieInfo().getPoster_path(), viewHolderClass[0].getMovieInfo().getRelease_date(),
                        viewHolderClass[0].getMovieInfo().getPopularity(),
                        viewHolderClass[0].getMovieInfo().getId()

                );}catch(NullPointerException ex){
                 Toast.makeText(getActivity(),"Error(movieListenerClass):"+ex.getMessage(),Toast.LENGTH_LONG).show();
                 ex.printStackTrace();//if you don't write  may be you will have NULL POINTER Exception
                // or you can write
              // Log.e("movieListenerClass",""+ex.getMessage());

             }
            }
        });


        }
        catch(Exception ex){


            Log.e("",""+ex.getMessage());//if you remove "" that beside ex.getMessage() may be you will have NULL POINTER Exception
        }

       //try call static images
    //  mypost_image =(ImageView)rootView.findViewById(R.id.meineimageView);
//post_image.setImageResource(R.drawable.bbb); run time exception

/*
        ArrayList<String> PostPaths=new ArrayList<String>();
       PostPaths.add("http://i.imgur.com/DvpvklR.png");
        PostPaths.add("http://i.imgur.com/DvpvklR.png");
        PostPaths.add("http://i.imgur.com/DvpvklR.png");
        PostPaths.add("http://i.imgur.com/DvpvklR.png");
        PostPaths.add("http://i.imgur.com/DvpvklR.png");
        PostPaths.add("http://i.imgur.com/DvpvklR.png");*/


//Call from Path

        //JSon Object
       // Toast.makeText(getActivity(),"JSON Object:"+myasyncTask.getMovieJasonArrayFromURL(),Toast.LENGTH_LONG).show();

        // Load image another example
try {
   // LoadImage.execute("https://www.learn2crack.com/wp-content/uploads/2014/04/node-cover-720x340.png");
   // LoadImage.execute("https://drive.google.com/file/d/0B53Ddz2nxL_8eWdJQUFPYVZtN28/view?usp=sharing");
}
    catch (Exception ex){Toast.makeText(getActivity(),"Asynctask new:"+ex.getMessage(),Toast.LENGTH_LONG).show();}







        return rootView;


    }


    public void setMovieListenerClass(MovieListenerClass movieListenerClass) {
        this.movieListenerClass = movieListenerClass;
    }
    //it like equal MainActivity.movieListenerClass=MainActivityFragment.movieListenerClass
}