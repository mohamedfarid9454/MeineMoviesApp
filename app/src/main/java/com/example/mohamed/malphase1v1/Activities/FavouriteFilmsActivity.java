package com.example.mohamed.malphase1v1.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mohamed.malphase1v1.Fragments.FavouriteFilmsActivityFragment;
import com.example.mohamed.malphase1v1.Fragments.FavouriteFilmsActivityShowOneImageFragment;
import com.example.mohamed.malphase1v1.HelperClasses.MovieListenerClass;
import com.example.mohamed.malphase1v1.R;
import android.util.Log;
import android.widget.FrameLayout;

public class FavouriteFilmsActivity extends AppCompatActivity implements MovieListenerClass{
boolean  checkTableFavouriteFilmLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_films);

        try {
            FrameLayout tablet_frameLayoutFavouriteFilmsObject= (FrameLayout) findViewById(R.id.tablet_frameLayout_favouriteFilms);

            if(null==tablet_frameLayoutFavouriteFilmsObject){

                checkTableFavouriteFilmLayout=false;
            }else{checkTableFavouriteFilmLayout=true;}

            if (savedInstanceState == null) {
                FavouriteFilmsActivityFragment favouriteFilmsActivityFragment=new FavouriteFilmsActivityFragment();
                favouriteFilmsActivityFragment.setMovieListenerClass(this);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.mobile_frameLayout_favouriteFilms, favouriteFilmsActivityFragment)//error ?
                        .commit();
                Log.e("SuccessCallFragment3 ", "SuccessCallingFragmentInMainActivity3");

            }
        }catch(Exception ex){
            Log.e("ErrorCallFragment3", "ErrorCallingFragmentInMainActivity3:"+ex.getMessage());

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_favourite_films, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setSelectedMovieInfo(String title, String vote_average, String poster_path, String release_date, String popularity, long id) {

        if(checkTableFavouriteFilmLayout){
            FavouriteFilmsActivityShowOneImageFragment favouriteFilmsActivityShowOneImageFragment=new FavouriteFilmsActivityShowOneImageFragment();

            Bundle bundle=new Bundle();
            bundle.putString("ImageTitle",title);
            bundle.putString("VoteAverage",vote_average);
            bundle.putString("Image_Poster_Path",poster_path);
            bundle.putString("ReleaseDate",release_date);
            bundle.putString("plot_synopsis",popularity);
            bundle.putLong("ImageID", id);
            favouriteFilmsActivityShowOneImageFragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction().replace(R.id.tablet_frameLayout_favouriteFilms,favouriteFilmsActivityShowOneImageFragment).commit();

        }

        else{

            //Go to AnotherActivity
            Intent intent=new Intent(this, FavouriteFilmsActivityShowOneImage.class);
            //  intent.putExtra("movieInfoDetails",movieInfo);
            intent.putExtra("ImageTitle", title);
            intent.putExtra("VoteAverage",vote_average);
            intent.putExtra("Image_Poster_Path",poster_path);
            intent.putExtra("ReleaseDate",release_date);
            intent.putExtra("plot_synopsis", popularity);
            intent.putExtra("ImageID", id);

            startActivity(intent);
        }
    }
}
