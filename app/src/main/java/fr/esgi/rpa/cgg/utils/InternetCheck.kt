package fr.esgi.rpa.cgg.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class InternetCheck (private val context: Context ){

    fun showAlert(myApp : AppCompatActivity ) {
        val alertBuilder = AlertDialog.Builder(myApp)
        alertBuilder.setTitle("Our app need Internet")
        alertBuilder.setMessage("Dear customer, you're have no internet connection, please refresh after succesfully connect to internet")
        alertBuilder.setPositiveButton("refresh") { dialog, which ->
            myApp.finish()
            myApp.startActivity(myApp.intent)
        }
        alertBuilder.setNeutralButton("back to menu") { dialog, which ->
            myApp.onBackPressed()
        }
        alertBuilder.show()
    }

    fun internetWorking(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork
            val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
            networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
            isConnected
        }
    }
}