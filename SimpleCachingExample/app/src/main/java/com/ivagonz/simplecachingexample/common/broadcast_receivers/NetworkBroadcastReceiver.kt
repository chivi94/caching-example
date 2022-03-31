package com.ivagonz.simplecachingexample.common.broadcast_receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity


/**
 * Created by igonzalez on 31/3/22
 * www.toools.es
 */
class NetworkBroadcastReceiver(
    mContext: Context,
    //private val mConnectionChangeListener: OnConnectionChangeListener,
    private val connectionStateChanged: (Boolean) -> Unit
) :
    BroadcastReceiver() {

    private var wifiManager: WifiManager =
        mContext.applicationContext.getSystemService(AppCompatActivity.WIFI_SERVICE) as WifiManager

    /*private fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }

        return result
    }*/

    override fun onReceive(context: Context, intent: Intent) {
        val wifiStateExtra = intent.getIntExtra(
            WifiManager.EXTRA_WIFI_STATE,
            WifiManager.WIFI_STATE_UNKNOWN
        )

        /*if (wifiStateExtra == WifiManager.WIFI_STATE_ENABLED || ) {
            connectionStateChanged(true)
        } else {
            connectionStateChanged(false)
        }*/

        when (wifiStateExtra) {
            WifiManager.WIFI_STATE_ENABLED -> {
                connectionStateChanged(true)
                // mConnectionChangeListener.onConnectionChange(true)
            }

            WifiManager.WIFI_STATE_DISABLED -> {
                connectionStateChanged(false)
                //mConnectionChangeListener.onConnectionChange(false)
            }
        }
    }

}