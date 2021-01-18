package fr.esgi.rpa.cgg.color

import retrofit2.Call
import retrofit2.http.GET

interface ColorsApiService {
    companion object {
        const val BASE_URL: String = "http://www.json-generator.com/api/"
        private const val URI: String = "json/get/bVsTGmGfJu?indent=2"
    }

    @GET(URI)
    fun getAllColors(): Call<List<Color>>
}