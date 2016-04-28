package com.example.mohamed.malphase1v1.HelperClassesReviews;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.example.mohamed.malphase1v1.HelperClasses.MovieInfo;
import com.example.mohamed.malphase1v1.HelperClasses.MyImageViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Mohamed on 4/23/2016.
 */
public class ReviewURLConnectionAsyncTask  extends AsyncTask<String, Void,  ArrayList<ReviewInfo>> {
    String movieJasonArrayFromURL;
    Context context;
    //  ArrayList<MovieInfo> movieInfoArrayListMyFragment;
    ReviewViewAdapter myAdapterObject;
    GridView mygridview;

    public ReviewURLConnectionAsyncTask( Context context, ReviewViewAdapter myAdapterObject, GridView mygridview) {// ArrayList<MovieInfo> movieInfoArrayListMyFragment
        this.context = context;
        this.myAdapterObject = myAdapterObject;
        this.mygridview = mygridview;
    }


    public  ArrayList<ReviewInfo> readJsonFromReview( String BigJsonObject){
        /*Ex of one json array:
        "results":[]

        object 1 in this array:
{

id: "56f4f0bd9251417a440017bd",
author: "Rahul Gupta",
content: "Awesome moview. Best Action sequence. **Slow in the first half**",
url: "https://www.themoviedb.org/review/56f4f0bd9251417a440017bd"

}

        /*object 2 in this array:...*/
        try {
            JSONObject MyBigJsonObject = new JSONObject(BigJsonObject);
            JSONArray results=MyBigJsonObject.getJSONArray("results");
            /*if(MyBigJsonObject.getJSONArray("total_pages").toString().equals("0")) {
                Toast.makeText(this.context, "Pages=0", Toast.LENGTH_LONG).show();
                Log.e("Pages=0", "Pages=0");

            }*/
//MovieInfo []movieInfoObject=new MovieInfo[results.length()];
            ArrayList<ReviewInfo> ReviewInfoArrayList=new ArrayList<ReviewInfo>();
            for(int i=0;i<results.length();i++){
                JSONObject infoAboutOneMovie=results.getJSONObject(i);
                String author=infoAboutOneMovie.getString("author");
                String content=infoAboutOneMovie.getString("content");
                String id=infoAboutOneMovie.getString("id");

                ReviewInfoArrayList.add(new ReviewInfo(author,content,id));
                Log.e("JSON("+i+")", "author:"+author+"//id:"+id+"///Content:"+content);

            }
            Log.e("SuccessFormattingJSON", "Success:JsonFormatinInURLClassIn(readJsonFromReview)method");

            return ReviewInfoArrayList;
        } catch (JSONException e) {
            Log.e("ErrorFormattingJSON", "Failed:JsonFormatinInURLClassIn(readJsonFromReview)method"+e.getMessage());
            Toast.makeText(this.context, "FailedReadJsonFromMovieDB(Error):"+e.getMessage(), Toast.LENGTH_LONG).show();

            e.printStackTrace();
            return null;
        }

    }
    @Override
    protected  ArrayList<ReviewInfo> doInBackground(String... params) {
        // If there's no zip code, there's nothing to look up.  Verify size of params.


        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.

        String MyAPI=params[0];
        String MovieID=params[1];//MovieID
        try {

            final String Movie_BASE_URL =
                    "https://api.themoviedb.org/3/movie/"+MovieID+"/reviews?api_key="+MyAPI+"";





            URL url = new URL(Movie_BASE_URL);//builtUri.toString());

            Log.e("BuiltReviewURL", "Built URI " + url.toString());

            // Create the request to MovieDB(reviews), and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                Log.e("FailedInputStream","Failed:InputStreamInURLClassIn(doInbackground)methodReviews");

                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                Log.e("NothingtoRead","Failed:BufferIsEmpty(readJsonFromMovieDB)method");

                return null;
            }
            movieJasonArrayFromURL = buffer.toString();
            Log.e("SuccessGetURLJson","Success:GetJsonFromURL(doInbachground)method");

            Log.v("", "Review string: " + movieJasonArrayFromURL);

        } catch (IOException ex) {

            Log.e("FailedGetURLJson","Failed:GetJsonFromURL(doInbachground)method:"+ex.getMessage());

            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("", "Error closing stream", e);
                }
            }
        }

        try {

            Log.e("JsonObject:",movieJasonArrayFromURL);
            Log.d("JSON Object:", movieJasonArrayFromURL);
            return readJsonFromReview(movieJasonArrayFromURL);

//           Toast.makeText(context,"JSON Object:"+movieJasonArrayFromURL,Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Log.e("JsonError:","Failed:Calling(readJsonFromMovieDB) error:"+ e.getMessage());
            Toast.makeText(this.context, "FailedReadJsonFromMovieDB(Error):" + e.getMessage(), Toast.LENGTH_LONG).show();

            e.printStackTrace();
            return  null;
        }


    }//EndOfdoInBackGround

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }//EndOfonPreExecute

    @Override
    protected void onPostExecute( ArrayList<ReviewInfo> movieInfoArrayListOnPostExcute) {
        if(movieInfoArrayListOnPostExcute!=null){

//If JsonFormatting done Successfully and they were be put in MovieInfoObjectArrayList so we get this objects and put them in our Arraylist and send it to Adapter

         /*  for(int i=0;i<movieInfoArrayListOnPostExcute.size();i++){
                this.movieInfoArrayListMyFragment.add(movieInfoArrayListOnPostExcute.get(i));
            }
*/
            this.myAdapterObject=new ReviewViewAdapter(movieInfoArrayListOnPostExcute,this.context);//this.movieInfoArrayListMyFragment);
            this.mygridview.setAdapter(this.myAdapterObject);
            Log.e("SetInAdapter:", "SetInAdapterSuccess");

        }
//this.myAdapterObject.notifyDataSetChanged();
        //super.onPostExecute(strings);

    }//EndOfonPostExecute

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }//EndOfonProgressUpdate

}
