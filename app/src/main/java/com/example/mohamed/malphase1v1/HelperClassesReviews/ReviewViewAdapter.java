package com.example.mohamed.malphase1v1.HelperClassesReviews;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohamed.malphase1v1.HelperClasses.MovieInfo;
import com.example.mohamed.malphase1v1.HelperClasses.ViewHolderClass;
import com.example.mohamed.malphase1v1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Mohamed on 4/23/2016.
 */
public class ReviewViewAdapter extends BaseAdapter {
    ArrayList<ReviewInfo> movieInfoArrayListMyAdaptor;//=new ArrayList<MovieInfo>();
    Context thisActivityContext;

    public ReviewViewAdapter(ArrayList<ReviewInfo> movieInfoArrayListMyAdaptor, Context thisActivityContext) {
        this.movieInfoArrayListMyAdaptor = movieInfoArrayListMyAdaptor;
        this.thisActivityContext = thisActivityContext;
    }

    @Override
    public int getCount() {
        return movieInfoArrayListMyAdaptor.size();
    }

    @Override
    public ReviewInfo getItem(int position) {
        return movieInfoArrayListMyAdaptor.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ReviewInfo reviewInfoobject = movieInfoArrayListMyAdaptor.get(position);
       try{
           convertView = LayoutInflater.from(thisActivityContext).inflate(R.layout.reviewoneelement, null);
        Log.e("SuccessCallingImageView", "SuccessCallingImageViewXMLInClassReviewViewAdapter");
    } catch (Exception ex) {
        Log.e("FailedCallingImageView", "FailedCallingImageViewXMLInClassReviewViewAdapter" + ex.getMessage());

        // Toast.makeText(thisActivityContext, ex.getMessage(), Toast.LENGTH_LONG).show();
    }
    //Get Texts Viewp[o65
    TextView review_id = (TextView) convertView.findViewById(R.id.ReviewID);
    TextView review_author = (TextView) convertView.findViewById(R.id.ReviewAuthor);
    TextView review_content = (TextView) convertView.findViewById(R.id.ReviewContent);
try {
    review_id.setText(reviewInfoobject.getId());
    review_author.setText(reviewInfoobject.getAuthor());
    review_content.setText(reviewInfoobject.getContent());
    Log.e("OKReviewTextView", "SuccessCallingReviewTextViewXMLInClassReviewViewAdapter");
} catch (Exception ex) {
    Log.e("NOReviewTextView", "FailedCallingReviewTextViewXMLInClassReviewViewAdapter" + ex.getMessage());
}

/*     if(convertView==null) {
            try {

                convertView = LayoutInflater.from(thisActivityContext).inflate(R.layout.reviewoneelement, null);
                Log.e("SuccessCallingImageView", "SuccessCallingImageViewXMLInClassMYImageViewAdapter");
            } catch (Exception ex) {
                Log.e("FailedCallingImageView", "FailedCallingImageViewXMLInClassMYImageViewAdapter" + ex.getMessage());

                // Toast.makeText(thisActivityContext, ex.getMessage(), Toast.LENGTH_LONG).show();
            }
             reviewHolderClasses=new ReviewHolderClasses();
            //Get Texts View
            TextView review_id = (TextView) convertView.findViewById(R.id.ReviewID);
            TextView review_author = (TextView) convertView.findViewById(R.id.ReviewAuthor);
            TextView review_content = (TextView) convertView.findViewById(R.id.ReviewContent);

            reviewHolderClasses.setReview_id(review_id);
            reviewHolderClasses.setReview_author(review_author);
            reviewHolderClasses.setReview_content(review_content);

            convertView.setTag(reviewHolderClasses);
            Log.e("viewHolderClass", "New" );

            //}//end of unnecessary for(not correct here)
        }//end of if convert view =null
        else{
            reviewHolderClasses=(ReviewHolderClasses)convertView.getTag();
            Log.e("ReviewHolderClass", "OLD" );

        }


        try {
            if (reviewHolderClasses.getReview_id() == null) {
                Log.e("FailedImageViewwIsNull", "FailedImageViewwIsNullInClassMYImageViewAdapterReview");

                Toast.makeText(thisActivityContext, "image view is empty", Toast.LENGTH_LONG).show();

            } else {

                //        Picasso.with(thisActivityContext).load("https://cms-assets.tutsplus.com/uploads/users/21/posts/19431/featured_image/CodeFeature.jpg").into(post_image);



            }

        } catch (Exception ex) {
            Log.e("FailedForPicasso", "FailedForPicassoInClassMYImageViewAdapter" + ex.getMessage());

            Toast.makeText(thisActivityContext, "Picasso error:" + ex.getMessage(), Toast.LENGTH_LONG).show();
        }*/
        return convertView;

    }//EndOfGetView    }
}
