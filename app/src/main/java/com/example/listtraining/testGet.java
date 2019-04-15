package com.example.listtraining;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class testGet extends AppCompatActivity implements Response.ErrorListener, Response.Listener<Bitmap> {
    ImageView imageView;
    RequestQueue requestQueue;
    ProgressBar progressBar;
    String url="https://www.rijksmuseum.nl/api/en/collection?key=MvDNbZD9&format=json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_get);
        imageView = findViewById(R.id.imageView);
        requestQueue = Volley.newRequestQueue(this);
        progressBar = findViewById(R.id.progressBar);

    }
    public void download(View view) {
        if (NetworkConnection.getConnection(this)) {
            progressBar.setVisibility(View.VISIBLE);
            ImageRequest imageRequest = new ImageRequest(
                    url,
            this,
                    600, 600,
                    ImageView.ScaleType.CENTER, null,
                    this);
            requestQueue.add(imageRequest);
        }else {
            Toast.makeText(this, "No internet connectivity…", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onResponse(Bitmap bitmap) {
        progressBar.setVisibility(View.GONE);
        imageView.setImageBitmap(bitmap);

    }
}