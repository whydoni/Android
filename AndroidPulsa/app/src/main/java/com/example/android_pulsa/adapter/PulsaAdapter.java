package com.example.android_pulsa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_pulsa.R;
import com.example.android_pulsa.model.PulsaModel;
import com.example.android_pulsa.util.Util;
import com.example.android_pulsa.viewmodel.PulsaViewModel;

import java.util.ArrayList;

public class PulsaAdapter extends RecyclerView.Adapter<PulsaAdapter.PulsaViewHolder> {
    private Context context;
    private RelativeLayout layout_rincian;
    private ArrayList<PulsaModel> list;
    private View viewfocus;
    private AppCompatImageView ic_close;
    private AppCompatTextView tv_payment,tv_pulsa;
    private CoordinatorLayout layoutPulsa;
    private LinearLayoutCompat btn_pay;
    private PulsaViewModel pulsaViewModel;
    private PulsaModel pulsas=new PulsaModel();

    public PulsaAdapter(Context context, ArrayList<PulsaModel> list, RelativeLayout layout_rincian,View viewfocus,
                        AppCompatImageView ic_close,AppCompatTextView tv_payment,CoordinatorLayout layoutPulsa,
                        AppCompatTextView tv_pulsa,LinearLayoutCompat btn_pay, PulsaViewModel pulsaViewModel) {
        this.layout_rincian=layout_rincian;
        this.layoutPulsa=layoutPulsa;
        this.tv_payment=tv_payment;
        this.tv_pulsa=tv_pulsa;
        this.ic_close=ic_close;
        this.viewfocus=viewfocus;
        this.btn_pay=btn_pay;
        this.context = context;
        this.pulsaViewModel = pulsaViewModel;
        this.list = list;
    }

    @NonNull
    @Override
    public PulsaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
        return new PulsaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PulsaViewHolder holder, int position) {
        int pulsa = (int) list.get(position).getNominal();
        int price = (int) list.get(position).getPrice();
        holder.nominal.setText(pulsa);

        holder.linear_nominal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.expand(layout_rincian,2);
                viewfocus.setVisibility(View.VISIBLE);
                viewfocus.animate().alpha(0.5f);
                layoutPulsa.animate().alpha(0.5f);
                tv_pulsa.setText("Pulsa "+pulsa);
                tv_payment.setText("Rp. "+price);
            }
        });
        ic_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.collapse(layout_rincian,2);
                viewfocus.setVisibility(View.GONE);
                layoutPulsa.animate().alpha(1f);
            }
        });
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PulsaModel payload = new PulsaModel();
                payload.setCode(list.get(position).toString());
                payload.setNomor("081381863335");
                postNasabah(payload);
            }
        });
    }

    private void postNasabah(PulsaModel nasabahPayload ){
        pulsaViewModel.postPulsa(nasabahPayload).observe((LifecycleOwner) context, pulsaResponse -> {
            pulsas = pulsaResponse.getData();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PulsaViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView nominal;
        LinearLayoutCompat linear_nominal;

        public PulsaViewHolder (@NonNull View itemView) {
            super(itemView);
            nominal = itemView.findViewById(R.id.nominal);
            linear_nominal=itemView.findViewById(R.id.linear_nominal);
        }
    }
}
