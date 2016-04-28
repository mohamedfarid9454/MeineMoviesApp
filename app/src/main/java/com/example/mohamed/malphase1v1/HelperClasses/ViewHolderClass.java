package com.example.mohamed.malphase1v1.HelperClasses;

import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by mohamed on 4/4/2016.
 */
public class ViewHolderClass  {
    private ImageView imageView;
    private MovieInfo movieInfo;
    private Button b;

    public MovieInfo getMovieInfo() {
        return movieInfo;
    }

    public Button getB() {
        return b;
    }

    public void setB( Button  b) {
        this.b = b;
    }

    public void setMovieInfo(MovieInfo movieInfo) {
        this.movieInfo = movieInfo;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
