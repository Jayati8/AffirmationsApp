package com.example.affirmations.broadcastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log

@Suppress("DEPRECATION")
// Define a BroadcastReceiver that listens for network connectivity changes.
class NetworkChangeReceiver(private val listener: CheckConnection) : BroadcastReceiver() {

    // This function is called when a broadcast intent is received.
    override fun onReceive(context: Context?, intent: Intent?) {

        if (isNetworkConnected(context)) {

            val connect = isNetworkConnected(context)

        } else {

            listener.isConnected(true)
        }
    }

    // This function checks whether a network connection is available.
    fun isNetworkConnected(context: Context?): Boolean {
        // Get a reference to the ConnectivityManager service using the provided context.
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        // Retrieve information about the currently active network connection.
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        // Check if activeNetworkInfo is not null and if the device is connected to a network.
        // If both conditions are met, return true.\

        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}

interface CheckConnection {
    fun isConnected(b: Boolean)
}









