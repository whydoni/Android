package com.example.berita.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.berita.R;
import com.example.berita.model.BeritaModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder> {
    Context context;
    private ArrayList<BeritaModel> arrlist;

    public BeritaAdapter(Context context, ArrayList<BeritaModel> arrlist) {
        this.context = context;
        this.arrlist = arrlist;
    }

    @NonNull
    @Override
    public BeritaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view,parent,false);
        return new BeritaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeritaViewHolder holder, int position) {
        holder.titleView.setText(arrlist.get(position).getTitle());
        holder.descView.setText(arrlist.get(position).getCategory());
//        Glide.with(context).load(arrlist.get(position).getUrl()).placeholder(android.R.drawable.progress_indeterminate_horizontal).error(android.R.drawable.stat_notify_error).into(holder.imageView);
        String imageUri = arrlist.get(position).getUrl();
        Picasso.get().load(imageUri).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return arrlist.size();
    }

    public class BeritaViewHolder extends RecyclerView.ViewHolder{
        AppCompatTextView titleView, descView;
        AppCompatImageView imageView;

        public BeritaViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView =itemView.findViewById(R.id.titleView);
            descView =itemView.findViewById(R.id.descView);
            imageView =itemView.findViewById(R.id.imageView);
        }
    }
}
