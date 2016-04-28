package com.example.mohamed.malphase1v1.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mohamed.malphase1v1.Fragments.FavouriteFilmsActivityShowOneImageFragment;
import com.example.mohamed.malphase1v1.R;

public class FavouriteFilmsActivityShowOneImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_films_activity_show_one_image);
        Bundle bundle=getIntent().getExtras();

        try {

            if (savedInstanceState == null) {
                //If you don't do that only tablet will see your info
                FavouriteFilmsActivityShowOneImageFragment favouriteFilmsActivityShowOneImageFragment=new FavouriteFilmsActivityShowOneImageFragment();
                favouriteFilmsActivityShowOneImageFragment.setArguments(bundle);
                //
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment4, favouriteFilmsActivityShowOneImageFragment)//error ?
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
        getMenuInflater().inflate(R.menu.menu_favourite_films_activity_show_one_image, menu);
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
}
