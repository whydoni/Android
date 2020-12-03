package com.example.berita;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.ViewModelProviders;

import com.example.berita.model.BeritaModel;
import com.example.berita.viewmodel.BeritaViewModel;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    private AppCompatEditText titleEditText, descEditText, urlEditText;
    private ArrayList<BeritaModel> arrayList = new ArrayList<>();
    private static String json="";
    private BeritaViewModel beritaViewModel;
    private BeritaModel beritaModel;
    private static String message="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    void saveBerita () {
        String title = titleEditText.getText().toString();
        String category = descEditText.getText().toString();
        String url = urlEditText.getText().toString();

        BeritaModel beritaModel = new BeritaModel();
        beritaModel.setTitle(title);
        beritaModel.setCategory(category);
        beritaModel.setUrl(url);

        beritaViewModel.addBerita(beritaModel).observe(this, beritaResponse -> {
            message=beritaResponse.getMessage();
            Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void initView(){
        beritaModel = new BeritaModel();
        titleEditText =findViewById(R.id.titleEditText);
        descEditText =findViewById(R.id.descEditText);
        urlEditText =findViewById(R.id.urlEditText);
        beritaViewModel= ViewModelProviders.of(this).get(BeritaViewModel.class);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        initView();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.save_button:
                saveBerita();
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
                return(true);
        }
        return(super.onOptionsItemSelected(item));
    }


}