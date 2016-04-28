package com.example.mohamed.malphase1v1.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.mohamed.malphase1v1.Fragments.Main2ActivityFragment;
import com.example.mohamed.malphase1v1.Fragments.MainActivityFragment;
import com.example.mohamed.malphase1v1.HelperClasses.MovieInfo;
import com.example.mohamed.malphase1v1.HelperClasses.MovieListenerClass;
import com.example.mohamed.malphase1v1.HelperClasses.ViewHolderClass;
import com.example.mohamed.malphase1v1.R;

public class MainActivity extends AppCompatActivity implements MovieListenerClass {
boolean checkTabletOrNot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
try {
    FrameLayout tablet_frameLayoutObject= (FrameLayout) findViewById(R.id.tablet_frameLayout);

    if(null==tablet_frameLayoutObject){

        checkTabletOrNot=false;
    }else{checkTabletOrNot=true;}


    if (savedInstanceState == null) {

        MainActivityFragment mainActivityFragment=new MainActivityFragment();
        mainActivityFragment.setMovieListenerClass(this);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.mobile_frameLayout, mainActivityFragment)//error ?
                .commit();
        Log.e("SuccessCallingFragment ", "SuccessCallingFragmentInMainActivity");

    }
}catch(Exception ex){
    Log.e("ErrorCallingFragment", "ErrorCallingFragmentInMainActivity:"+ex.getMessage());

}

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void setSelectedMovieInfo(String title,String vote_average,String poster_path,String release_date,String popularity,long id){//(MovieInfo movieInfo) {

        if(checkTabletOrNot){
            Main2ActivityFragment main2ActivityFragment=new Main2ActivityFragment();
            /*Intent intent=new Intent(this, Main2Activity.class);
            intent.putExtra("movieInfoDetails", movieInfo);
            */
            Bundle bundle=new Bundle();
            bundle.putString("ImageTitle",title);
            bundle.putString("VoteAverage",vote_average);
            bundle.putString("Image_Poster_Path",poster_path);
            bundle.putString("ReleaseDate",release_date);
            bundle.putString("plot_synopsis",popularity);
            bundle.putLong("ImageID", id);
            main2ActivityFragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction().replace(R.id.tablet_frameLayout,main2ActivityFragment).commit();
        }
        else{

            //Go to AnotherActivity
            Intent intent=new Intent(this, Main2Activity.class);
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
