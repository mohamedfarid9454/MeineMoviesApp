package com.example.mohamed.malphase1v1.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mohamed.malphase1v1.Fragments.ReviewsActivityFragment;
import com.example.mohamed.malphase1v1.Fragments.VideosActivityFragment;
import com.example.mohamed.malphase1v1.R;

public class VideosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);


        try {
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.video_fragment, new VideosActivityFragment())//error ?
                        .commit();
                Log.e("SuccessCallingFragment ", "SuccessCallingFragmentInVideosActivity");

            }
        }catch(Exception ex){
            Log.e("ErrorCallingFragment", "ErrorCallingFragmentInVideosActivity:"+ex.getMessage());

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_videos, menu);
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
