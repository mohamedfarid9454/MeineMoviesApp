package com.example.mohamed.malphase1v1.HelperClasses;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mohamed.malphase1v1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mohamed on 3/30/2016.
 */
public class MyImageViewAdapter extends BaseAdapter {
    ArrayList<MovieInfo> movieInfoArrayListMyAdaptor;//=new ArrayList<MovieInfo>();
  final static  String moviePath="http://image.tmdb.org/t/p/w185/";
    //Then you will need a ‘size’, which will be one of the following:
    // "w92", "w154", "w185", "w342", "w500", "w780", or "original". For most phones we recommend using “w185”.

    Context thisActivityContext;
    public MyImageViewAdapter(Context thisActivityContext,ArrayList<MovieInfo> movieInfoArrayList){
        this.thisActivityContext=thisActivityContext;
        this.movieInfoArrayListMyAdaptor=movieInfoArrayList;
     /*for(int i=0;i<5;i++) {
         this.movieInfoArrayListMyAdaptor.add(movieInfoArrayList.get(i));
     }*/
    }

    @Override
    public int getCount() {
        return  movieInfoArrayListMyAdaptor.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public MovieInfo getItem(int position) {
        return movieInfoArrayListMyAdaptor.get(position);
    }
    @Override
    //if adapter want to set data in grid view
    //call it number of items in screen
    public View getView(final int position, View convertView, ViewGroup parent) {
        // LayoutInflater myinflate=LayoutInflater.from(thisActivityContext);
        //  View view1=myinflate.inflate(R.layout.fragment_main, null);
      //  for (int i=0;i<movieInfoArrayListMyAdaptor.size();i++) {
        ViewHolderClass viewHolderClass;
        MovieInfo movieInfoobject = movieInfoArrayListMyAdaptor.get(position);
  //      if(convertView==null) {
        viewHolderClass=new ViewHolderClass();

            try {

                convertView = LayoutInflater.from(thisActivityContext).inflate(R.layout.gridviewtext, null);
                Log.e("SuccessCallingImageView", "SuccessCallingImageViewXMLInClassMYImageViewAdapter");
                //Get Image View
                ImageView post_image = (ImageView) convertView.findViewById(R.id.meineimageView);

                viewHolderClass.setImageView(post_image);
                viewHolderClass.setMovieInfo(movieInfoobject);
                convertView.setTag(viewHolderClass);
                Log.e("viewHolderClass", "New" );
            } catch (Exception ex) {
                Log.e("FailedCallingImageView", "FailedCallingImageViewXMLInClassMYImageViewAdapter" + ex.getMessage());

                // Toast.makeText(thisActivityContext, ex.getMessage(), Toast.LENGTH_LONG).show();
            }
        /*    viewHolderClass=new ViewHolderClass();
            //Get Image View
            ImageView post_image = (ImageView) convertView.findViewById(R.id.meineimageView);

            viewHolderClass.setImageView(post_image);
            viewHolderClass.setMovieInfo(movieInfoobject);
            convertView.setTag(viewHolderClass);
            Log.e("viewHolderClass", "New" );
*/
            //}//end of unnecessary for(not correct here)
      /*  }//end of if convert view =null
        else{
            viewHolderClass=(ViewHolderClass)convertView.getTag();
            Log.e("viewHolderClass", "OLD" );

        }
*/

        try {
            if (viewHolderClass.getImageView() == null) {
                Log.e("FailedImageViewwIsNull", "FailedImageViewwIsNullInClassMYImageViewAdapter");

                Toast.makeText(thisActivityContext, "image view is empty", Toast.LENGTH_LONG).show();

            } else {

                //        Picasso.with(thisActivityContext).load("https://cms-assets.tutsplus.com/uploads/users/21/posts/19431/featured_image/CodeFeature.jpg").into(post_image);

                Log.e("Poster Path:", moviePath+movieInfoobject.getPoster_path()+" Img number:"+position);
                Picasso.with(thisActivityContext)
                        .load(moviePath+movieInfoobject.getPoster_path())
                        .placeholder(R.drawable.aaa) // optional
                        .error(R.drawable.bbb)         // optional
                       // .resize(250, 200)                        // optional
                        //.rotate(90)
                        .into(viewHolderClass.getImageView());
                Log.e("SuccessForPicasso", "SuccessForPicassoInClassMYImageViewAdapter");
            }

        } catch (Exception ex) {
            Log.e("FailedForPicasso", "FailedForPicassoInClassMYImageViewAdapter" + ex.getMessage());

            Toast.makeText(thisActivityContext, "Picasso error:" + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        return convertView;

    }//EndOfGetView
}

