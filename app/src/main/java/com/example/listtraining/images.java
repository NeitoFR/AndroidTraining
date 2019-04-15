package com.example.listtraining;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class images extends AppCompatActivity {
    //Web api url
    public static final String DATA_URL = "https://www.rijksmuseum.nl/api/en/collection?key=QKkH603N&format=JSON&principalMaker=Marius Bauer&ps=100";

    //Tag values to read from json
    public static final String TAG_IMAGE_URL = "url";
    public static final String TAG_NAME = "title";

    //GridView Object
    private GridView gridView;

    //ArrayList for Storing image urls and titles
    private ArrayList<String> images;
    private ArrayList<String> artists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        gridView = (GridView) findViewById(R.id.gridView);
        images = new ArrayList<>();
        artists = new ArrayList<>();

        //Calling the getData method
        getData();
    }
    private void getData(){

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, DATA_URL,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                      // loading.dismiss();

                        //Displaying our grid
                        try {
                            System.out.println(response.getJSONArray("artObjects"));
                            JSONArray artworkList = response.getJSONArray("artObjects");
                            for (int i = 0; i < artworkList.length(); i++) {
                                JSONObject artwork = artworkList.getJSONObject(i);
                                if(!artwork.get("webImage").equals(null) && !artwork.getJSONObject("webImage").getString("url").equals(null))
                                    System.out.println(i+" : "+artwork.getJSONObject("webImage").getString("url"));
                                else {
                                    System.out.println("webImage or URL is null");
                                }
//                                if(test.getJSONObject(i).getJSONObject("webImage").getString("url") != "null" || test.getJSONObject(i).getJSONObject("webImage").toString() != "null")

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //Adding our request to the queue
        requestQueue.add(jsonArrayRequest);
    }
    private void showGrid(JSONArray jsonArray){
        //Looping through all the elements of json array
        for(int i = 0; i<5; i++){
            //Creating a json object of the current index
            JSONObject obj = null;
            try {
                //getting json object from current index
                obj = jsonArray.getJSONObject(i);

                //getting image url and title from json object
                images.add(obj.getString(TAG_IMAGE_URL));
                artists.add(obj.getString(TAG_NAME));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //Creating GridViewAdapter Object
        GridViewAdapter gridViewAdapter = new GridViewAdapter(this,images,artists);

        //Adding adapter to gridview
        gridView.setAdapter(gridViewAdapter);
    }
}
