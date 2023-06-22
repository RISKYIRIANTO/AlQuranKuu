package com.example.al_quranku;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.al_quranku.model.Audio.AudioFilesItem;

import java.io.IOException;
import java.util.List;

public class AdapterAudio extends RecyclerView.Adapter<AdapterAudio.AudioViewHolder> {
    private List<AudioFilesItem> results;

    private List<AudioFilesItem> audioList;

    private MediaPlayer mediaPlayer;

    AdapterAudio(List<AudioFilesItem> audioList){
        this.audioList = audioList;
        mediaPlayer = new MediaPlayer();
    }

    @NonNull
    @Override
    public AdapterAudio.AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AudioViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.audio, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAudio.AudioViewHolder holder, int position) {

        holder.textViewAudio.setOnClickListener(view -> {
            AudioFilesItem audio = audioList.get(position);

            if (mediaPlayer.isPlaying()){
                pauseAudio();
            }else {
                playAudio(audio.getAudioUrl());
            }
                });

        /*AudioFilesItem result = results.get(position);

        holder.textViewayat.setText(result.getAudioUrl());*/

    }


    private void playAudio(String audioplay) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(audioplay);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void pauseAudio() {
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void setData(List<AudioFilesItem> results){
        audioList.clear();
        audioList.addAll(results);
        notifyDataSetChanged();
    }

    public class AudioViewHolder extends RecyclerView.ViewHolder {
        public Button audiobutton;
        public View textViewAudio;

        public AudioViewHolder(View itemView) {
            super(itemView);

            audiobutton = itemView.findViewById(R.id.btAudio);
        }
    }

}

