package com.example.berita;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.berita.model.ListBerita;
import com.google.gson.Gson;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    private ArrayList<ListBerita> listBerita = new ArrayList<>();
    private EditText titleEditText, descEditText, urlEditText;
    private static String json;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViewById();

    }

    void findViewById() {
        titleEditText = findViewById(R.id.titleEditText);
        descEditText = findViewById(R.id.descEditText);
        urlEditText = findViewById(R.id.urlEditText);
    }

    void saveBerita () {
        String title = titleEditText.getText().toString();
        String desc = descEditText.getText().toString();
        String url = urlEditText.getText().toString();

        ListBerita listBerita = new ListBerita();
        listBerita.setTitle(title);
        listBerita.setDesc(desc);
        listBerita.setUrlImage(url);
        Gson gson = new Gson();
        json=gson.toJson(listBerita);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.save_button:
                saveBerita();
                Intent i = getIntent();
                i.putExtra("data", json);
                setResult(RESULT_OK, i);
                finish();
                return(true);
        }
        return(super.onOptionsItemSelected(item));
    }


}