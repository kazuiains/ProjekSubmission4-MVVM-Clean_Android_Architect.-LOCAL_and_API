package com.muhammad_adi_yusuf.projeksubmission4.model.restwebservice.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse{

	@SerializedName("results")
	private List<ApisItem> results;

	public List<ApisItem> getResults(){
		return results;
	}

	@Override
 	public String toString(){
		return 
			"ApiResponse{" + 
			"results = '" + results + '\'' + 
			"}";
		}
}