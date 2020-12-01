package com.example.berita;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.berita.adapter.BeritaAdapter;
import com.example.berita.model.ListBerita;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ListBerita> beritas =new ArrayList<>();
    private FloatingActionButton addButton;
    private ListView listBerita;
    private static int CODE_MAIN_ACTIVITY=72;
    private BeritaAdapter beritaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                i.putExtra("id", "CEK");
                startActivityForResult(i, CODE_MAIN_ACTIVITY);
            }
        });
    }

    private void initView(){
        addButton =findViewById(R.id.addButton);
        listBerita = findViewById(R.id.listBerita);
        beritaAdapter = new BeritaAdapter(getApplicationContext(), beritas);
        listBerita.setAdapter(beritaAdapter);
        beritaAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        parse(data.getStringExtra("data"));

    }

    private void parse( String data){
        Gson gson = new Gson();
        ListBerita model = gson.fromJson(data , ListBerita.class);
        beritas.add(model);
        beritaAdapter.notifyDataSetChanged();
    }
}