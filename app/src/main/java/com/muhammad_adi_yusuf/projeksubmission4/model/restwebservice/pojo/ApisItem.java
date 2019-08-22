package com.muhammad_adi_yusuf.projeksubmission4.model.restwebservice.pojo;

import com.google.gson.annotations.SerializedName;

public class ApisItem {

	@SerializedName(value="release_date", alternate= "first_air_date")
	private String firstAirDate;

	@SerializedName("overview")
	private String overview;

	@SerializedName("original_language")
	private String originalLanguage;

	@SerializedName("vote_average")
	private double voteAverage;

	@SerializedName(value="title", alternate= "name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("poster_path")
	private String posterPath;

	public String getFirstAirDate(){
		return firstAirDate;
	}

	public String getOverview(){
		return overview;
	}

	public String getOriginalLanguage(){
		return originalLanguage;
	}

	public double getVoteAverage(){
		return voteAverage;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public String getPosterPath(){
		return posterPath;
	}

	@Override
 	public String toString(){
		return 
			"ApisItem{" +
					"first_air_date = '" + firstAirDate + '\'' +
					",overview = '" + overview + '\'' +
					",original_language = '" + originalLanguage + '\'' +
					",vote_average = '" + voteAverage + '\'' +
					",name = '" + name + '\'' +
					",poster_path = '" + posterPath + '\'' +
					",release_date = '" + firstAirDate + '\'' +
					",title = '" + name + '\'' +
					",id = '" + name + '\'' +
			"}";
		}
}