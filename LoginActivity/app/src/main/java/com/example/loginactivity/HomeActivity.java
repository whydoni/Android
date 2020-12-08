package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    private TextView user_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initData();
    }

    private void initView() {
        user_tv = findViewById(R.id.user_tv);
    }

    private void initData() {
        Bundle bundle =getIntent().getExtras();
        user_tv.setText(bundle.getString("username"));
    }
}