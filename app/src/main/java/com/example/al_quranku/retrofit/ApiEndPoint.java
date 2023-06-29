package com.example.al_quranku.retrofit;

import com.example.al_quranku.model.Audio.Audio;
import com.example.al_quranku.model.AyatModel.Verses;
import com.example.al_quranku.model.SurahModel.Chapter;
import com.example.al_quranku.model.terjemahan.Terjemahan;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndPoint {

   @GET("chapters?language=id")
    Call<Chapter> getSurah();

    @GET("quran/verses/uthmani")
    Call<Verses> getAyat(@Query("chapter_number") int id);

    @GET("quran/translations/174")
    Call<Terjemahan> getText(@Query("chapter_number") int id);

    @GET("chapter_recitations/33?")
    Call<Audio> getAudio();


}
