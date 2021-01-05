package fr.esgi.rpa.cgg.color

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetColorsCallback(
    private val colors: MutableList<Color>,
    private val callback: () -> Unit
) : Callback<List<Color>> {
    companion object {
        private const val TAG: String = "GetColorsCallback"
    }

    override fun onFailure(call: Call<List<Color>>, t: Throwable) {
        Log.e(TAG, "An error has occurred", t)
    }

    override fun onResponse(call: Call<List<Color>>, response: Response<List<Color>>) {
        response.body()?.forEach { color: Color -> this.colors.add(color) }
        this.callback()
    }
}