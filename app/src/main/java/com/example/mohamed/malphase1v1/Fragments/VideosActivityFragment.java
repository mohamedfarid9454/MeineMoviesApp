package com.example.mohamed.malphase1v1.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.mohamed.malphase1v1.HelperClassesReviews.ReviewURLConnectionAsyncTask;
import com.example.mohamed.malphase1v1.HelperClassesVideos.VideoURLConnectionAsyncTask;
import com.example.mohamed.malphase1v1.HelperClassesVideos.VideoViewAdapter;
import com.example.mohamed.malphase1v1.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class VideosActivityFragment extends Fragment {
    GridView videoGridViewObject;
    VideoViewAdapter videoViewAdapter;
    public VideosActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//Call XML Fragment
        View rootView=null;
        try {
            rootView = inflater.inflate(R.layout.fragment_videos, container, false);
            // View rootView2=inflater.inflate(R.layout.gridviewtext, container, false);
            Log.e("SuccessCallingXML", "Success Calling XML(fragment_video)");
            //Call Grid View
            videoGridViewObject = (GridView) rootView.findViewById(R.id.VideosgridView);
        }catch(Exception ex){
            Log.e("ErrorCallingFragmentXML","ErrorCallingFragmentXML(fragment_main)"+ex.getMessage());
        }
        try {
          SharedPreferences sharedPreferences3=getActivity().getSharedPreferences("Shared_FilmID", Context.MODE_PRIVATE);

            VideoURLConnectionAsyncTask myasyncTask = new VideoURLConnectionAsyncTask(getActivity(), videoViewAdapter, videoGridViewObject);//movieInfoArrayListMyFragment
            myasyncTask.execute("0015a692626461cde8d22a7f39dd2db7", sharedPreferences3.getString("FilmID", ""));
            Log.e("SuccessExcuteAsyncTask", "Success Excute AsyncTask");

        } catch (Exception ex) {
            Log.e("ErrorCallingAsyncTask", "SuccessCallingAsyncTask" + ex.getMessage());

            Toast.makeText(getActivity(), "Asynctask URL Error::" + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        return  rootView;    }
}
