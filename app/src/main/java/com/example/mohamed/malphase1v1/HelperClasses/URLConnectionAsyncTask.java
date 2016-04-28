package com.example.mohamed.malphase1v1.HelperClasses;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;

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

import android.content.Context;
import android.widget.Toast;

/**
 * Created by mohamed on 3/30/2016.
 */
public class URLConnectionAsyncTask   extends AsyncTask<String, Void,  ArrayList<MovieInfo>> {
    String movieJasonArrayFromURL;
    Context context;
  //  ArrayList<MovieInfo> movieInfoArrayListMyFragment;
    MyImageViewAdapter myAdapterObject;
    GridView mygridview;

    public URLConnectionAsyncTask( Context context, MyImageViewAdapter myAdapterObject, GridView mygridview) {// ArrayList<MovieInfo> movieInfoArrayListMyFragment
        this.context = context;
        //this.movieInfoArrayListMyFragment = movieInfoArrayListMyFragment;
        this.myAdapterObject = myAdapterObject;
        this.mygridview = mygridview;
    }


    public  ArrayList<MovieInfo> readJsonFromMovieDB( String BigJsonObject){
        /*Ex of one json array:
        "results":[]
        object 1 in this array:
        {"poster_path":"\/8aatumKUvdqtpj0Be3t27jpLjGL.jpg",
        "adult":false,"overview":"",
        "release_date":"2005-06-25",
        "genre_ids":[],"id":158414,
        "original_title":"The Originals - PraTodo Mundo Ouvir",
        "original_language":"pt",
        "title":"The Originals - PraTodo Mundo Ouvir",
        "backdrop_path":"\/cE9xxrtq7yA4F0hDTwksqjCn2ZO.jpg",
        "popularity":1.000429,"vote_count":1,"video":false,"vote_average":10}*/

        /*object 2 in this array:
         *
          *
          * ,{"poster_path":"\/gAOFNQwNxf9fJOw9Jz8GdymxwHT.jpg","adult":false,"overview":"Wes Montgomery shines a light on one of the most unique and influential guitarists in music history. These beautifully filmed programs from the spring of 1965 feature Wes, in intimate studio settings, leading three different lineups through some of his best-known tunes, including \"Four On Six\", \"Jingles\" and \"West Coast Blues\". This rare footage, complete with rehearsals, between-song banter and closeup camera angles, illuminates We's extraordinary musical vocabulary and unconventional picking technique. An in-depth, song-by-song analysis by legendary jazz guitarist Pat Metheny makes this DVD a guitar lover's dream.","release_date":"2007-09-01","genre_ids":[],"id":157044,"original_title":"Jazz Icons: Wes Montgomery: Live in '65","original_language":"en","title":"Jazz Icons: Wes Montgomery: Live in '65","backdrop_path":"\/zv97UeiHkYR2rDP1TLNV07aABKk.jpg","popularity":1.000858,"vote_count":1,"video":false,"vote_average":10},*/
        try {
            JSONObject  MyBigJsonObject = new JSONObject(BigJsonObject);
            JSONArray results=MyBigJsonObject.getJSONArray("results");
//MovieInfo []movieInfoObject=new MovieInfo[results.length()];
            ArrayList<MovieInfo> movieInfoArrayList=new ArrayList<MovieInfo>();
            for(int i=0;i<results.length();i++){
                JSONObject infoAboutOneMovie=results.getJSONObject(i);
                String poster_path=infoAboutOneMovie.getString("poster_path");
                Boolean adult=infoAboutOneMovie.getBoolean("adult");
                String release_date=infoAboutOneMovie.getString("release_date");
                long id=infoAboutOneMovie.getLong("id");
                String original_title=infoAboutOneMovie.getString("original_title");
                String title=infoAboutOneMovie.getString("title");
                String backdrop_path=infoAboutOneMovie.getString("backdrop_path");
                String popularity=infoAboutOneMovie.getString("overview");
                double vote_count=infoAboutOneMovie.getDouble("vote_count");
                boolean video=infoAboutOneMovie.getBoolean("video");
                String vote_average=infoAboutOneMovie.getString("vote_average");
                movieInfoArrayList.add(new MovieInfo(poster_path,adult,release_date,id,original_title,title,backdrop_path,popularity,vote_count,video,vote_average));
            }
            Log.e("SuccessFormattingJSON","Success:JsonFormatinInURLClassIn(readJsonFromMovieDB)method");

            return movieInfoArrayList;
        } catch (JSONException e) {
            Toast.makeText(this.context, "FailedReadJsonFromMovieDB(Error):"+e.getMessage(), Toast.LENGTH_LONG).show();

            Log.e("ErrorFormattingJSON", "Failed:JsonFormatinInURLClassIn(readJsonFromMovieDB)method"+e.getMessage());

            e.printStackTrace();
            return null;
        }

    }
    @Override
    protected  ArrayList<MovieInfo> doInBackground(String... params) {
        // If there's no zip code, there's nothing to look up.  Verify size of params.


        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.

        String MyAPI=params[0];
        String Sort_order=params[1];//top_rated,popular
        try {
            //https://api.themoviedb.org/3/movie/550?api_key=0015a692626461cde8d22a7f39dd2db7
            //http://api.themoviedb.org/3/discover/movie?sort_by=vote_average.desc&api_key=0015a692626461cde8d22a7f39dd2db7&page=1
            //page=1to1000

           final String Movie_BASE_URL =
                   "http://api.themoviedb.org/3/movie/"+Sort_order+"?api_key="+MyAPI+"";
                                 //  "http://api.themoviedb.org/3/discover/movie/"+Sort_order+"?format=json&sort_by=vote_average.desc&page=1&api_key="+MyAPI+"";//top_related,vote_average.desc
                  //  "https://api.themoviedb.org/3/movie/550?api_key=0015a692626461cde8d22a7f39dd2db7";





            URL url = new URL(Movie_BASE_URL);//builtUri.toString());

            //Log.v(LOG_TAG, "Built URI " + builtUri.toString());

            // Create the request to MovieDB, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                Log.e("FailedInputStream","Failed:InputStreamInURLClassIn(doInbackground)method");

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

            Log.v("", "Movie string: " + movieJasonArrayFromURL);

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
           // return readJsonFromMovieDB(movieJasonArrayFromURL);
          // return  movieJasonArrayFromURL;
  //         Log.d("key","what you want here");
           Log.e("JsonObject:",movieJasonArrayFromURL);
           Log.d("JSON Object:", movieJasonArrayFromURL);
           return readJsonFromMovieDB(movieJasonArrayFromURL);

//           Toast.makeText(context,"JSON Object:"+movieJasonArrayFromURL,Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Log.e("JsonError:","Failed:Calling(readJsonFromMovieDB) error:"+ e.getMessage());
           Toast.makeText(this.context, "FailedReadJsonFromMovieDB(Error):"+e.getMessage(), Toast.LENGTH_LONG).show();

           e.printStackTrace();
           return  null;
        }


    }//EndOfdoInBackGround

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }//EndOfonPreExecute

    @Override
    protected void onPostExecute( ArrayList<MovieInfo> movieInfoArrayListOnPostExcute) {
        if(movieInfoArrayListOnPostExcute!=null){

//If JsonFormatting done Successfully and they were puutting in MovieInfoObjectArrayList so we get this objects and put them in our Arraylist and send it to Adapter

         /*  for(int i=0;i<movieInfoArrayListOnPostExcute.size();i++){
                this.movieInfoArrayListMyFragment.add(movieInfoArrayListOnPostExcute.get(i));
            }
*/
            this.myAdapterObject=new MyImageViewAdapter(this.context,movieInfoArrayListOnPostExcute);//this.movieInfoArrayListMyFragment);
            this.mygridview.setAdapter(this.myAdapterObject);
        }
//this.myAdapterObject.notifyDataSetChanged();
        //super.onPostExecute(strings);

    }//EndOfonPostExecute

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }//EndOfonProgressUpdate

}
