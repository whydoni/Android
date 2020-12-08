package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private AppCompatButton submit_bt;
    private TextInputEditText username_et, password_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        submit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });
    }

    private void initView() {
        submit_bt = findViewById(R.id.submit_bt);
        username_et = findViewById(R.id.username_et);
        password_et = findViewById(R.id.password_et);
    }

    private void doLogin() {
        if (!username_et.getText().toString().equals("") && !password_et.getText().toString().equals("")) {
            Intent intent =new Intent(MainActivity.this, HomeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("username", username_et.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),"Cek kembali Username dan Password",Toast.LENGTH_LONG).show();
        }
    }
}