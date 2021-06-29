package com.daviixo.breakingbad_api_test

import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {

    @GET("characters")

    fun listCharacters(): Call<List<CharacterInfo>>
}