package com.example.al_quranku;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.al_quranku.model.AyatModel.VersesItem;
import com.example.al_quranku.model.terjemahan.TranslationsItem;

import java.util.ArrayList;
import java.util.List;

public class AdapterTerjemahan extends RecyclerView.Adapter<AdapterTerjemahan.TerjemahanViewHolder> {

    private List <VersesItem> ayat;
    private List <TranslationsItem> arti;


    public AdapterTerjemahan (List<VersesItem> ayat, List<TranslationsItem> arti){
       this.arti = arti;
       this.ayat = ayat;
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

        TranslationsItem result = arti.get(position);
        VersesItem result_1 = ayat.get(position);

        holder.textViewTerjemahanAyat.setText(result.getText());
        holder.textViewAyat.setText(result_1.getTextUthmani());
        holder.NomorAyat.setText(String.valueOf(result_1.getId()));
        /*holder.NomorSurah.setText(String.valueOf(result_1.getId()));*/


    }

    @Override
    public int getItemCount() {
        return arti.size();
    }

    public class TerjemahanViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTerjemahanAyat, textViewAyat, NomorAyat, NomorSurah;

        public TerjemahanViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTerjemahanAyat = itemView.findViewById(R.id.tvTerjemahanAyat);
            textViewAyat = itemView.findViewById(R.id.tvAyat);
            NomorAyat = itemView.findViewById(R.id.NomorAyat);
            NomorSurah = itemView.findViewById(R.id.NomorSurah);

        }
    }

    public void setData(List<TranslationsItem> Terjemahan, List <VersesItem> result){
        arti.clear();
        arti.addAll(Terjemahan);

        ayat.clear();
        ayat.addAll(result);

        notifyDataSetChanged();
    }
}
