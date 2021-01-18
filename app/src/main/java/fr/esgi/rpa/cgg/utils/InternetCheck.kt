package fr.esgi.rpa.cgg.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AlertDialog
import fr.esgi.rpa.cgg.BaseActivity
import fr.esgi.rpa.cgg.R

object InternetCheck {
    private const val ALERT_BACK: Int = R.string.back
    private const val ALERT_MSG: Int = R.string.internet_alert_msg
    private const val ALERT_REFRESH: Int = R.string.refresh
    private const val ALERT_TITLE: Int = R.string.internet_alert_title

    fun internetWorking(context: Context): Boolean {
        val connectivityManager: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities: NetworkCapabilities? =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return null != networkCapabilities
                && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    fun showAlert(activity: BaseActivity) {
        val alertBuilder = AlertDialog.Builder(activity)
        val alertTitle = activity.getString(ALERT_TITLE)
        val alertMessage = activity.getString(ALERT_MSG)
        val alertRefresh = activity.getString(ALERT_REFRESH)
        val alertBack = activity.getString(ALERT_BACK)
        alertBuilder.setTitle(alertTitle)
        alertBuilder.setMessage(alertMessage)
        alertBuilder.setPositiveButton(alertRefresh) { _, _ ->
            if (this.internetWorking(activity)) activity.continueOnCreate()
            else alertBuilder.show()
        }
        alertBuilder.setNeutralButton(alertBack) { _, _ -> activity.onBackPressed() }
        alertBuilder.setCancelable(false)
        alertBuilder.show()
    }
}