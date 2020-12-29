package fr.esgi.rpa.cgg.color

import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ColorsRepository {
    private var apiService: ColorsApiService? = null

    init {
        this.apiService = Retrofit.Builder()
            .baseUrl("http://www.json-generator.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ColorsApiService::class.java)
    }

    fun getAllColors(callback: Callback<List<Color>>) {
        val call = this.apiService?.getAllColors()
        call?.enqueue(callback)
    }
}