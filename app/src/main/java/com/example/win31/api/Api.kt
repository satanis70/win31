package com.example.win31.api

import com.example.win31.model.ImageModel
import com.example.win31.model.WordModel
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("images.json")
    fun getImages(): Call<ImageModel>
    @GET("countries.json")
    fun getWords(): Call<WordModel>
}