package com.example.affirmations

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.affirmations.broadcastReceiver.NetworkChangeReceiver
import com.google.android.material.snackbar.Snackbar

@Suppress("DEPRECATION")
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var networkChangeReceiver: NetworkChangeReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        networkChangeReceiver = NetworkChangeReceiver()

        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangeReceiver, intentFilter)

        // Check for initial network connectivity
        if (!networkChangeReceiver.isNetworkConnected(this)) {
            // Internet connection is not available, retry after a delay
            showSnackbar()
            Handler(Looper.getMainLooper()).postDelayed({
                checkInternetConnection()
            }, 5000) // Retry after 5 seconds
        }
    }

    private fun checkInternetConnection() {
        if (networkChangeReceiver.isNetworkConnected(this)) {
            // Internet connection is available, continue to the main activity
            startActivity(Intent(this, MainActivity::class.java))
            unregisterReceiver(networkChangeReceiver)
            finish()
        } else {
            // Internet connection is still not available, continue to check
            Handler(Looper.getMainLooper()).postDelayed({
                checkInternetConnection()
            }, 5000) // Retry after 5 seconds
        }
    }

    private fun showSnackbar() {
        val snackbar = Snackbar.make(findViewById(android.R.id.content), "No internet connection", Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Retrieve") {
            // Retrieve button clicked, check for internet connection again
            if (networkChangeReceiver.isNetworkConnected(this)) {
                // Internet connection is available, continue to the main activity
                startActivity(Intent(this, MainActivity::class.java))
                unregisterReceiver(networkChangeReceiver)
                finish()
            } else {
                // Internet connection is still not available
                showSnackbar()
            }
        }
        snackbar.show()
    }
}

