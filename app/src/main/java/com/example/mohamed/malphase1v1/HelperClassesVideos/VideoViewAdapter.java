package com.example.mohamed.malphase1v1.HelperClassesVideos;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.net.Uri;
import com.example.mohamed.malphase1v1.HelperClassesReviews.ReviewInfo;
import com.example.mohamed.malphase1v1.R;

import java.util.ArrayList;

/**
 * Created by Mohamed on 4/23/2016.
 */
public class VideoViewAdapter extends BaseAdapter {

    ArrayList<VideoInfo> VideoInfoInfoArrayListMyAdaptor;//=new ArrayList<MovieInfo>();
    Context thisActivityContext;

    public VideoViewAdapter(ArrayList<VideoInfo> videoInfoInfoArrayListMyAdaptor, Context thisActivityContext) {
        VideoInfoInfoArrayListMyAdaptor = videoInfoInfoArrayListMyAdaptor;
        this.thisActivityContext = thisActivityContext;
    }

    @Override
    public int getCount() {
        return VideoInfoInfoArrayListMyAdaptor.size();
    }

    @Override
    public VideoInfo getItem(int position) {
        return VideoInfoInfoArrayListMyAdaptor.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VideoInfo videoInfoobject = VideoInfoInfoArrayListMyAdaptor.get(position);
        try{
            convertView = LayoutInflater.from(thisActivityContext).inflate(R.layout.videooneelement, null);
            Log.e("SuccessCallingImageView", "SuccessCallingImageViewXMLInClassVideoViewAdapter");
        } catch (Exception ex) {
            Log.e("FailedCallingImageView", "FailedCallingImageViewXMLInClassMYVideoViewAdapter" + ex.getMessage());

            // Toast.makeText(thisActivityContext, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        //Get Texts Viewp[o65
        Button video_id = (Button) convertView.findViewById(R.id.WatchVideoButton);
        try {
            final  String VideoKey=videoInfoobject.getKey();
            video_id.setText(videoInfoobject.getKey());
            video_id.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v="+VideoKey));
                    thisActivityContext.startActivity(browserIntent);
                }
            });
            Log.e("OKVideoTextView", "SuccessCallingReviewTextViewXMLInClassReviewViewAdapter");
        } catch (Exception ex) {
            Log.e("NOVideoTextView", "FailedCallingReviewTextViewXMLInClassReviewViewAdapter" + ex.getMessage());
        }
    return  convertView;
    }
}
