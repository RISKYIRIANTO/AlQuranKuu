package com.example.al_quranku;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.al_quranku.model.Audio.Audio;
import com.example.al_quranku.model.Audio.AudioFilesItem;
import com.example.al_quranku.model.SurahModel.ChaptersItem;


import java.util.List;


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    private List<ChaptersItem> results;

    private List<AudioFilesItem> Audio;

    /*public MainAdapter(List<ChaptersItem> results) {
        this.results = results;
    }*/

    public MainAdapter(List<AudioFilesItem>Audio,List<ChaptersItem> results) {
        this.results = results;this.Audio=Audio;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        ChaptersItem chapters = results.get(position);
        AudioFilesItem Audioitem = Audio.get(position);

        holder.NomorSurah.setText(String.valueOf(chapters.getId()));



        holder.textViewSurahLatin.setText(chapters.getNameSimple());
        holder.textViewTerjemahanSurah.setText(chapters.getTranslatedName().getName());
        holder.textViewSurahArab.setText(chapters.getNameArabic());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailSurahActivity.class);
//                Intent intent1 = new Intent(holder.itemView.getContext(),MainAdapterNomorAyat.class);
                intent.putExtra("name_simple", chapters.getNameSimple());
                intent.putExtra("id", chapters.getId());
                intent.putExtra("name_arabic", chapters.getNameArabic());
                intent.putExtra("name_complex", chapters.getNameComplex());
                intent.putExtra("revelation_order", chapters.getRevelationOrder());
                intent.putExtra("revelation_place", chapters.getRevelationPlace());
                intent.putExtra("verses_count", chapters.getVersesCount());
                intent.putExtra("audio_url", Audioitem.getAudioUrl());

                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        TextView textViewSurahLatin, textViewTerjemahanSurah, textViewSurahArab, NomorSurah;
        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewSurahLatin = itemView.findViewById(R.id.tvsurahlatin);
            textViewTerjemahanSurah = itemView.findViewById(R.id.tvterjemahansurah);
            textViewSurahArab = itemView.findViewById(R.id.tvsuraharab);
            NomorSurah = itemView.findViewById(R.id.NomorSurah);
        }
    }

    public void setData(List<ChaptersItem> data, List<AudioFilesItem> listAudio){
        results.clear();
        results.addAll(data);

        Audio.clear();
        Audio.addAll(listAudio);
        notifyDataSetChanged();
    }
}

    /*@NonNull
    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);


        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.MainViewHolder holder, int position) {
        final ChaptersItem chapters = results.get(position);

        holder.textViewSurahLatin.setText(chapters.getNameSimple());
        holder.textViewTerjemahanSurah.setText(chapters.getTranslatedName().getName());
        holder.textViewSurahArab.setText(chapters.getNameArabic());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailSurahActivity.class);
                intent.putExtra("name_simple", chapters.getNameSimple());
                intent.putExtra("id", chapters.getId());
                intent.putExtra("name_arabic", chapters.getNameArabic());
                intent.putExtra("name_complex", chapters.getNameComplex());
                intent.putExtra("revelation_order", chapters.getRevelationOrder());
                intent.putExtra("revelation_place", chapters.getRevelationPlace());
                intent.putExtra("verses_count", chapters.getVersesCount());

                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        TextView textViewSurahLatin, textViewTerjemahanSurah, textViewSurahArab;
        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewSurahLatin = itemView.findViewById(R.id.tvsurahlatin);
            textViewTerjemahanSurah = itemView.findViewById(R.id.tvterjemahansurah);
            textViewSurahArab = itemView.findViewById(R.id.tvsuraharab);
        }
    }

    public void setData(List<ChaptersItem> data ){
        results.clear();
        results.addAll(data);

        Audio.clear();
        Audio.addAll(tampilAudio);
        notifyDataSetChanged();
    }
}*/