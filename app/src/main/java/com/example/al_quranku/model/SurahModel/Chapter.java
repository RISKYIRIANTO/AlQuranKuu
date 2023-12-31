package com.example.al_quranku.model.SurahModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Chapter{

	@SerializedName("chapters")
	private List<ChaptersItem> chapters;

	public List<ChaptersItem> getChapters(){
		return chapters;
	}

	@Override
 	public String toString(){
		return 
			"Chapter{" + 
			"chapters = '" + chapters + '\'' + 
			"}";
		}
}