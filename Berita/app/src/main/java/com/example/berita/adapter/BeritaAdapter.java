package com.example.berita.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.example.berita.R;
import com.example.berita.model.ListBerita;

import java.util.ArrayList;

public class BeritaAdapter extends BaseAdapter {
    Context context;
    private ArrayList<ListBerita> list;

    public BeritaAdapter(Context context, ArrayList<ListBerita> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressWarnings("deprecation")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(R.layout.item_view, null);
        }
        ListBerita model = list.get(position);
        AppCompatTextView titleView = convertView.findViewById(R.id.titleView);
        AppCompatTextView descView = convertView.findViewById(R.id.descView);
        AppCompatImageView imageView = convertView.findViewById(R.id.imageView);

        titleView.setText(model.getTitle());
        descView.setText(model.getDesc());
        Glide.with(context).load(model.getUrlImage()).into(imageView);

        return convertView;
    }
}
