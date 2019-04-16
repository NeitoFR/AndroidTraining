package com.example.listtraining.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.listtraining.R;
import com.example.listtraining.adapters.RecyclerViewAdapter;
import com.example.listtraining.model.Anime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class RecyclerView extends AppCompatActivity {

    private String URL="https://www.rijksmuseum.nl/api/en/collection?key=QKkH603N&format=JSON&principalMaker=Marius Bauer";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Anime>listeAnime;
    private JSONArray artworkList;
    private JsonObjectRequest jsonObjectRequest;
    private android.support.v7.widget.RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        listeAnime= new ArrayList<>();


        jsonRequest();
    }

    public void jsonRequest(){
       // request=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL,null,
                    new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                System.out.println("url est "+jsonObjectRequest);
                for (int j = 0; j < response.length(); j++) {

                    try {
                        System.out.println(response.getJSONArray("artObjects"));
                        artworkList = response.getJSONArray("artObjects");
                        //JSONObject jsonObject=null;
                        for (int i = 0; i < artworkList.length(); i++) {
                            JSONObject artwork = artworkList.getJSONObject(i);
                            if (!artwork.get("webImage").equals(null) && !artwork.getJSONObject("webImage").getString("url").equals(null))
                                System.out.println(i + " : " + artwork.getJSONObject("webImage").getString("url"));
                            else {
                                System.out.println("webImage or URL is null deleting from array");
                                artworkList.remove(i);
                            }
                            Anime anime = new Anime();
                            anime.setTitle(artwork.getString("title"));
                            anime.setLongTitle(artwork.getString("longTitle"));
                            anime.setPrincipalMaker(artwork.getString("principalOrFirstMaker"));
                            anime.setImage(artwork.getJSONObject("webImage").getString("url"));
                            listeAnime.add(anime);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                setuprecyclerview(listeAnime);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

    });

        requestQueue= Volley.newRequestQueue(RecyclerView.this);
        requestQueue.add(jsonObjectRequest);
    }


    private void setuprecyclerview(List<Anime> listeAnime) {

        android.support.v7.widget.RecyclerView recyclerView = findViewById(R.id.recyclerViewid);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,listeAnime);
        recyclerView.setAdapter(myAdapter);
    }
}
