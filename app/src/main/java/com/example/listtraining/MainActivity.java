package com.example.listtraining;

import android.app.Dialog;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ListView listView;
    private ArrayList<String> artworkList;
    private ArrayAdapter<String> adapter;
    private Button addButton;
    private EditText itemEdit;
    private Button clrButton;
    private Button changeArtistButton;
    private TextView selectItemViewer;
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        listView = findViewById(R.id.navigation_drawer_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setTitle(R.string.app_title);
        actionBar.setSubtitle(R.string.app_subtitle);

        artworkList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, artworkList);
        listView.setAdapter(adapter);
        itemEdit = findViewById(R.id.item_edit);
        addButton = findViewById(R.id.add_btn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.add(itemEdit.getText().toString());
                Snackbar.make(v, "Item "+artworkList.size()+" added", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                itemEdit.setText("");
            }
        });
        for (int i = 1; i <= 3; i++) {
            adapter.add("Item "+ i);
        }
        clrButton = findViewById(R.id.clr_btn);
        clrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.clear();
                Snackbar.make(v, "List cleared", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        selectItemViewer = findViewById(R.id.selected_item_viewer);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemViewer.setText(adapter.getItem(position));
                drawerLayout.closeDrawers();
            }
        });
        changeArtistButton = findViewById(R.id.change_artist_button);
        changeArtistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.artist_dialog);

                Toolbar artistDialToolbar = dialog.findViewById(R.id.artist_dial_toolbar);
                artistDialToolbar.setTitle(R.string.artist_dial_toolbar_title);

                Button artistDialBtn = dialog.findViewById(R.id.artist_dial_btn);
                artistDialBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
//                        Créer la liste des artistes dans le Dialog
                    }
                });

                dialog.show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}