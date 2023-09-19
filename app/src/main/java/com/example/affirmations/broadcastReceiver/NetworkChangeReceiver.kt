package com.example.affirmations.broadcastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.example.affirmations.MainActivity

@Suppress("DEPRECATION")
// Define a BroadcastReceiver that listens for network connectivity changes.
class NetworkChangeReceiver : BroadcastReceiver() {

    // This function is called when a broadcast intent is received.
    override fun onReceive(context: Context?, intent: Intent?) {
        if (isNetworkConnected(context)) {
            // Internet connection is available, continue to the main activity
            context?.startActivity(Intent(context, MainActivity::class.java))
            context?.unregisterReceiver(this)
        }
    }

    // This function checks whether a network connection is available.
    fun isNetworkConnected(context: Context?): Boolean{
        // Get a reference to the ConnectivityManager service using the provided context.
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        // Retrieve information about the currently active network connection.
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        // Check if activeNetworkInfo is not null and if the device is connected to a network.
        // If both conditions are met, return true.
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}










