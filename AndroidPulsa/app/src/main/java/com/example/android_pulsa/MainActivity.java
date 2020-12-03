package com.example.android_pulsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android_pulsa.adapter.PulsaAdapter;
import com.example.android_pulsa.model.PulsaModel;
import com.example.android_pulsa.viewmodel.PulsaViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView phone_number;
    private ArrayList<PulsaModel> arrModel =new ArrayList<>();
    private RecyclerView listPulsa;
    private PulsaAdapter pulsaAdapter;
    private PulsaViewModel pulsaViewModel;
    private List<PulsaModel> model;
    private View viewfocus;
    private RelativeLayout layout_rincian;
    private AppCompatImageView ic_close;
    private AppCompatTextView tv_payment,tv_pulsa;
    private CoordinatorLayout layoutPulsa;
    private LinearLayoutCompat btn_pay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    public void initView() {
        phone_number = findViewById(R.id.phone_number);
        listPulsa = findViewById(R.id.listPulsa);
        viewfocus = findViewById(R.id.viewfocus);
        layout_rincian = findViewById(R.id.layout_rincian);
        ic_close = findViewById(R.id.ic_close);
        tv_payment = findViewById(R.id.tv_payment);
        tv_pulsa = findViewById(R.id.tv_pulsa);
        layoutPulsa = findViewById(R.id.layoutPulsa);
        btn_pay = findViewById(R.id.btn_pay);
    }

    private void initData(){
        pulsaViewModel = ViewModelProviders.of(this).get(PulsaViewModel.class);
        if (pulsaAdapter==null){
            pulsaAdapter=new PulsaAdapter(MainActivity.this,arrModel,layout_rincian,
                    viewfocus,ic_close,tv_payment,layoutPulsa,tv_pulsa,btn_pay,pulsaViewModel);
            listPulsa.setLayoutManager(new GridLayoutManager(this,2));
            listPulsa.setAdapter(pulsaAdapter);
            listPulsa.setHasFixedSize(true);
            listPulsa.setItemAnimator(new DefaultItemAnimator());
            listPulsa.setNestedScrollingEnabled(true);
        } else {
            pulsaAdapter.notifyDataSetChanged();
        }
        pulsaViewModel.init();
        pulsaViewModel.getProduct().observe(this,pulsaResponse -> {
            model = pulsaResponse.getData();
            arrModel.clear();
            arrModel.addAll(model);
            pulsaAdapter.notifyDataSetChanged();
        });
    }
}