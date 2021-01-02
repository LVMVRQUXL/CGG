package fr.esgi.rpa.cgg.color

import retrofit2.Call
import retrofit2.http.GET

interface ColorsApiService {
    @GET("json/get/bVsTGmGfJu?indent=2")
    fun getAllColors(): Call<List<Color>>
}