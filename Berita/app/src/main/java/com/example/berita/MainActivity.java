package com.example.berita;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berita.adapter.BeritaAdapter;
import com.example.berita.model.BeritaModel;
import com.example.berita.viewmodel.BeritaViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<BeritaModel> beritas =new ArrayList<>();
    private List<BeritaModel> beritaList;
    private FloatingActionButton addButton;
    private RecyclerView rvlistBerita;
    private BeritaViewModel ListBerita;
    private static int CODE_MAIN_ACTIVITY=72;
    private BeritaAdapter beritaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    private void initView(){
        addButton =findViewById(R.id.addButton);
        rvlistBerita = findViewById(R.id.rvlistBerita);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        parse(data.getStringExtra("data"));

    }

    private void parse( String data){
        Gson gson = new Gson();
        BeritaModel model = gson.fromJson(data , BeritaModel.class);
        beritas.add(model);
        beritaAdapter.notifyDataSetChanged();
    }

    private void initData(){
        if (beritaAdapter==null){
            beritaAdapter=new BeritaAdapter(MainActivity.this,beritas);
            rvlistBerita.setLayoutManager(new LinearLayoutManager(this));
            rvlistBerita.setAdapter(beritaAdapter);
            rvlistBerita.setItemAnimator(new DefaultItemAnimator());
            rvlistBerita.setNestedScrollingEnabled(true);
        } else {
            beritaAdapter.notifyDataSetChanged();
        }
        ListBerita = ViewModelProviders.of(this).get(BeritaViewModel.class);

        ListBerita.init();
        ListBerita.getBerita().observe(this, beritaResponse -> {
            beritaList=beritaResponse.getData();
            beritas.clear();
            beritas.addAll(beritaList);
            beritaAdapter.notifyDataSetChanged();
        });
    }
}