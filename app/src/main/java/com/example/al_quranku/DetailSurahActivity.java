package com.example.al_quranku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.al_quranku.model.Audio.Audio;
import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.al_quranku.model.Audio.Audio;
import com.example.al_quranku.model.Audio.AudioFilesItem;
import com.example.al_quranku.model.AyatModel.Verses;
import com.example.al_quranku.model.AyatModel.VersesItem;
import com.example.al_quranku.model.terjemahan.Terjemahan;
import com.example.al_quranku.model.terjemahan.TranslationsItem;
import com.example.al_quranku.retrofit.ApiServices;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailSurahActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private AdapterAyat adapterAyat;

    private AdapterTerjemahan adapterTerjemahan;

    private AdapterAudio adapterAudio;
    /*private ConcatAdapter concatAdapter;*/

    private List<VersesItem> results =new ArrayList<>();
    private List<TranslationsItem> terjemahan = new ArrayList<>();

    private List<AudioFilesItem> audio = new ArrayList<>();

    private MediaPlayer mediaPlayer;

    TextView textViewNameSimpleSurah;
    TextView textViewIDSurah;
    TextView textViewNameComplexSurah;
    TextView textViewNameArabicSurah;
    TextView textViewUrutanTurunSurah;
    TextView textViewTempatTurunSurah;
    TextView textViewJumlahAyatSurah;

    /*public DetailSurahActivity() {
    }*/

    Button btnAudio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_surah);

        btnAudio = findViewById(R.id.tvAudio);

        int id = getIntent().getIntExtra("id", 1);



        /*int finalId = id;
        btnAudio.setOnClickListener(view -> ApiServices.endPoint().getAudio(finalId).enqueue(new Callback<Audio>() {
            @Override
            public void onResponse(Call<Audio> call, Response<Audio> response) {
                if (response.isSuccessful()) {
                    Audio audio = response.body();
                    String audioUrl = String.valueOf(audio.getAudioFiles());

                    mediaPlayer = new MediaPlayer();
                    try {
                        mediaPlayer.setDataSource(audioUrl);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (IOException t){
                        t.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Audio> call, Throwable t) {

            }
        }));*/

        String nameSimple = getIntent().getStringExtra("name_simple");
        textViewNameSimpleSurah = findViewById(R.id.tvNamaSurah);
        textViewNameSimpleSurah.setText(nameSimple);

        id = getIntent().getIntExtra("id", 1);
        textViewIDSurah = findViewById(R.id.ID);
        textViewIDSurah.setText("Surah Ke " + (id) + " Di Al-Qur'an");

        String nameComplex = getIntent().getStringExtra("name_complex");
        textViewNameComplexSurah = findViewById(R.id.tvNamaKompleks);
        textViewNameComplexSurah.setText("(" + (nameComplex) + ")");

        String nameArabic = getIntent().getStringExtra("name_arabic");
        textViewNameArabicSurah = findViewById(R.id.tvNamaArab);
        textViewNameArabicSurah.setText(nameArabic);

        int revelationOrder = getIntent().getIntExtra("revelation_order", 1);
        textViewUrutanTurunSurah = findViewById(R.id.tvUrutan);
        textViewUrutanTurunSurah.setText("Dan Turun Diurutan ke : " + (revelationOrder));

        String revelationPlace = getIntent().getStringExtra("revelation_place");
        textViewTempatTurunSurah = findViewById(R.id.tvTurun);
        textViewTempatTurunSurah.setText("Surah Ini Diturunkan Di " + (revelationPlace));

        int versesCount = getIntent().getIntExtra("verses_count", 1);
        textViewJumlahAyatSurah = findViewById(R.id.tvJumlahAyat);
        textViewJumlahAyatSurah.setText((versesCount) + " Ayat ");

        setUpView();
        setUpRecyclerView();
        System.out.println(id);
        getDataFromApi(id);
        getDataFromApiArti(id);
        getDataFromApiAudio(id);

    }

    private void setUpRecyclerView() {
        adapterAyat = new AdapterAyat(results);
        adapterTerjemahan = new AdapterTerjemahan((ArrayList<TranslationsItem>) terjemahan);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterAyat);
        adapterAudio = new AdapterAudio(audio);

    }


    private void getDataFromApi(int id) {
        ApiServices.endPoint().getAyat(id).enqueue(new Callback<Verses>() {
            @Override
            public void onResponse(Call<Verses> call, Response<Verses> response) {
                if (response.isSuccessful()) {
                    List<VersesItem> result = response.body().getVerses();
                    Log.d("Ayat", result.toString());
                    adapterAyat.setData(result);
                }
            }

            @Override
            public void onFailure(Call<Verses> call, Throwable t) {
                Log.d("Ayat", results.toString());
                adapterAyat.setData(results);
            }
        });
    }

    private void getDataFromApiArti(int id){
        ApiServices.endPoint().getText(id).enqueue(new Callback<Terjemahan>() {
            @Override
            public void onResponse(Call<Terjemahan> call, Response<Terjemahan> response) {
                if(response.isSuccessful()){
                    List<TranslationsItem> result = response.body().getTranslations();
                    Log.d("Arti", result.toString());
                    adapterTerjemahan.setData(result);
                }
            }

            @Override
            public void onFailure(
                    Call<Terjemahan> call, Throwable t) {
                Log.d("Error", t.toString());
            }
        });
    }

    private void getDataFromApiAudio (int id){
        ApiServices.endPoint().getAudio(id).enqueue(new Callback<Audio>() {

            public void onResponse (Call<Audio> call, Response<Audio> response){
                List<AudioFilesItem> result = response.body().getAudioFiles();
                Log.d("Audio", result.toString());
                adapterAudio.setData(result);
            }

            @Override
            public void onFailure(Call<Audio> call, Throwable t) {

            }
        });
    }
    

    private void setUpView() {
        recyclerView = findViewById(R.id.recyclerViewAyat);
    }

}