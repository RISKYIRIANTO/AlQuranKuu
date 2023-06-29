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

   /* private AdapterAudio adapterAudio;*/
    /*private ConcatAdapter concatAdapter;*/

    private final List<VersesItem> results =new ArrayList<>();
    private final List<TranslationsItem> terjemahan = new ArrayList<>();

    List <TranslationsItem> result;

    List <VersesItem> versesItems;

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

        mediaPlayer = new MediaPlayer();
        String audioUrl = getIntent().getStringExtra("audio_url");
        btnAudio = findViewById(R.id.tvAudio);
        btnAudio.setOnClickListener(view -> {
            if (mediaPlayer.isPlaying()) {
                pauseAudio();
            } else {
                playAudio(audioUrl);
            }
        });

        setUpView();
        setUpRecyclerView();
        System.out.println(id);
        getTranslateData(id);

    }

    private void getTranslateData(int id) {
        ApiServices.endPoint().getText(id).enqueue(new Callback<Terjemahan>() {
            @Override
            public void onResponse(Call<Terjemahan> call, Response<Terjemahan> response) {
                if (response.isSuccessful()){
                    DetailSurahActivity.this.result = response.body().getTranslations();
                    getDataFromApi(getIntent().getIntExtra("id", 1));
                }
            }

            @Override
            public void onFailure(Call<Terjemahan> call, Throwable t) {
            }
        });
}
    private void getDataFromApi(int id) {
        ApiServices.endPoint().getAyat(id).enqueue(new Callback<Verses>() {
            @Override
            public void onResponse(Call<Verses> call, Response<Verses> response) {
                if (response.isSuccessful()) {
                    DetailSurahActivity.this.versesItems = response.body().getVerses();
                    adapterTerjemahan.setData(result, versesItems);
                }
            }

            @Override
            public void onFailure(Call<Verses> call, Throwable t) {
            }
        });
    }


    private void setUpRecyclerView() {

        adapterTerjemahan = new AdapterTerjemahan(results, terjemahan);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterTerjemahan);

    }


    private void pauseAudio(){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    private void playAudio(String audio){
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(audio);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e){
            e.printStackTrace();
        }
    }



    private void setUpView() {
        recyclerView = findViewById(R.id.recyclerViewAyat);
    }

}