package com.example.affirmations

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.affirmations.broadcastReceiver.CheckConnection
import com.example.affirmations.broadcastReceiver.NetworkChangeReceiver
import com.google.android.material.snackbar.Snackbar

@Suppress("DEPRECATION")
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity(),CheckConnection {

    private lateinit var networkChangeReceiver: NetworkChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        networkChangeReceiver = NetworkChangeReceiver(this)
        checkInternetConnection()
    }

    private fun checkInternetConnection() {
        if (networkChangeReceiver.isNetworkConnected(this)) {
          Handler().postDelayed({
               val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }, 3000)
        } else {
            showSnackbar()
        }
    }
    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangeReceiver, intentFilter)
    }
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkChangeReceiver)
    }

    private fun showSnackbar() {
        val snackbar = Snackbar.make(findViewById(android.R.id.content), "No internet connection", Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Retrieve") {
            // Retrieve button clicked, check for internet connection again
            if(networkChangeReceiver.isNetworkConnected(this))
            {
                checkInternetConnection()
            } else {
                // Internet connection is still not available
                showSnackbar()
            }
        }
        snackbar.show()
    }

    override fun isConnected(b: Boolean) {
        Log.d("notef","kdf")
    }
}

