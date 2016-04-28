package com.example.mohamed.malphase1v1.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mohamed.malphase1v1.Fragments.Main2ActivityFragment;
import com.example.mohamed.malphase1v1.HelperClasses.MovieInfo;
import com.example.mohamed.malphase1v1.R;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

Bundle bundle=getIntent().getExtras();

       try {
            if (savedInstanceState == null) {
                Main2ActivityFragment main2ActivityFragment=new Main2ActivityFragment();
                main2ActivityFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment2, main2ActivityFragment)//error ?
                        .commit();
                Log.e("SuccessCallFragment2 ", "SuccessCallingFragmentInMainActivity2");

            }
        }catch(Exception ex){
            Log.e("ErrorCallFragment2", "ErrorCallingFragmentInMainActivity2:"+ex.getMessage());

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
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
