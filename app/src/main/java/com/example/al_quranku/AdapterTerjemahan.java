package com.example.al_quranku;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.al_quranku.model.terjemahan.TranslationsItem;

import java.util.ArrayList;
import java.util.List;

public class AdapterTerjemahan extends RecyclerView.Adapter<AdapterTerjemahan.TerjemahanViewHolder> {

    ArrayList<TranslationsItem> arrayListSurah;

    public AdapterTerjemahan (ArrayList<TranslationsItem>arrayListSurah){
        this.arrayListSurah = arrayListSurah;
    }

    @NonNull
    @Override
    public AdapterTerjemahan.TerjemahanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TerjemahanViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.ayat, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTerjemahan.TerjemahanViewHolder holder, int position) {

        TranslationsItem result = arrayListSurah.get(position);
        holder.textViewTerjemahanAyat.getText();


    }

    @Override
    public int getItemCount() {
        return arrayListSurah.size();
    }

    public class TerjemahanViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTerjemahanAyat;
        public TerjemahanViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTerjemahanAyat = itemView.findViewById(R.id.tvTerjemahanAyat);
        }
    }

    public void setData(List<TranslationsItem> Terjemahan){
        arrayListSurah.clear();
        arrayListSurah.addAll(Terjemahan);
    }
}
