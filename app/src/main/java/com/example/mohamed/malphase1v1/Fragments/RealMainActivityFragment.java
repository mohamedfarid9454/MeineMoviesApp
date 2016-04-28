package com.example.mohamed.malphase1v1.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mohamed.malphase1v1.Activities.MainActivity;
import com.example.mohamed.malphase1v1.R;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class RealMainActivityFragment extends Fragment {

    public RealMainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View realMainView=inflater.inflate(R.layout.fragment_real_main, container, false);
        final EditText myAPI=(EditText)realMainView.findViewById(R.id.myAPI);
        final EditText FilmsType=(EditText)realMainView.findViewById(R.id.FilmsType);
        Button GOTOMainActivity=(Button)realMainView.findViewById(R.id.GOTOMainActivity);

        //Spinners
        Spinner myspinner= (Spinner) realMainView.findViewById(R.id.myspinner);
        ArrayAdapter<CharSequence> arrayAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.items_spinner, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner.setAdapter(arrayAdapter);

        //SpinneOnitemListener

        myspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String myFilmsType= (String) parent.getItemAtPosition(position);
                FilmsType.setText(myFilmsType);

                try{
                    String film_api=myAPI.getText().toString();
                    String films_list_type=myFilmsType;//FilmsType.getText().toString();

                    //Put this object in SharedPrefrence
                    SharedPreferences sharedPreferences=getActivity().getSharedPreferences("Shared_API_AndFilmsType", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("film_api", film_api);
                    editor.putString("films_list_type", films_list_type);
                    editor.commit();

                    Intent intent=new Intent(RealMainActivityFragment.super.getActivity() , MainActivity.class);
                    startActivity(intent);                }

                catch(Exception ex){
                    Toast.makeText(getActivity(),"RealActivity:"+ex.getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /*setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String myFilmsType= (String) parent.getItemAtPosition(position);
                FilmsType.setText(myFilmsType);
            }
        });*/
        GOTOMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String film_api=myAPI.getText().toString();
                    String films_list_type=FilmsType.getText().toString();

                    //Put this object in SharedPrefrence
                    SharedPreferences sharedPreferences=getActivity().getSharedPreferences("Shared_API_AndFilmsType", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("film_api", film_api);
                    editor.putString("films_list_type", films_list_type);
                    editor.commit();

                    Intent intent=new Intent(RealMainActivityFragment.super.getActivity() , MainActivity.class);
                    startActivity(intent);                }

                catch(Exception ex){
                    Toast.makeText(getActivity(),"RealActivity:"+ex.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
        return realMainView;

    }
}
