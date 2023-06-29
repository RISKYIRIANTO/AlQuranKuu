package com.example.al_quranku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.al_quranku.model.Audio.Audio;
import com.example.al_quranku.model.Audio.AudioFilesItem;
import com.example.al_quranku.model.SurahModel.Chapter;
import com.example.al_quranku.model.SurahModel.ChaptersItem;
import com.example.al_quranku.retrofit.ApiServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private MainAdapter mainAdapter;

    private RecyclerView recyclerView;

    private RecyclerView.LayoutManager layoutManager;

    private List<ChaptersItem> Surah = new ArrayList<>();

    List<ChaptersItem> results;

    private List<AudioFilesItem> Audio = new ArrayList<>();

    List<AudioFilesItem> tampilAudio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDataFromApi();
        setUpView();
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        layoutManager = new LinearLayoutManager(this);
        mainAdapter = new MainAdapter(Audio, Surah);
        recyclerView = findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setAdapter(mainAdapter);

    }

    private void setUpView() {
        recyclerView = findViewById(R.id.recyclerview);

    }

    private void getDataFromApi(){
        ApiServices.endPoint().getSurah().enqueue(new Callback<Chapter>() {
            public void onResponse(@NonNull Call<Chapter> call, @NonNull Response<Chapter> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    MainActivity.this.results = response.body().getChapters();
                    getDataFromApiAudio();
                }
            }
            /*@Override
            public void onResponse(Call<Chapter> call, Response<Chapter> response) {
                if (response.isSuccessful()) {
                    List<ChaptersItem> Surah = response.body().getChapters();
                    Log.d("Main", Surah.toString());

                }*/


            @Override
            public void onFailure(@NonNull Call<Chapter> call, @NonNull Throwable t) {
                Log.d("ErrorMain", t.toString());
            }
            /*@Override
            public void onFailure(Call<Chapter> call, Throwable t) {
                Log.d("ErrorMain", t.toString());

            }*/
        });
    }

    private void getDataFromApiAudio() {
        ApiServices.endPoint().getAudio().enqueue(new Callback<Audio>() {
            @Override
            public void onResponse(@NonNull Call<Audio> call, @NonNull Response<Audio> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    MainActivity.this.tampilAudio = response.body().getAudioFiles();
                    setUpView();
                    setUpRecyclerView();
                    mainAdapter.setData(results, tampilAudio);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Audio> call, @NonNull Throwable t) {

            }
        });
    }

}