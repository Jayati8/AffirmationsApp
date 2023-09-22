package com.example.affirmations

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.affirmations.broadcastReceiver.CheckConnection
import com.example.affirmations.broadcastReceiver.NetworkChangeReceiver
import com.example.affirmations.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

const val TAG = "MainActivity"

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), CheckConnection {

    private lateinit var binding: ActivityMainBinding
    private lateinit var networkChangeReceiver: NetworkChangeReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate Called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        networkChangeReceiver = NetworkChangeReceiver(this)
        checkInternetConnection()

        // Create an instance of the ListFragment
        val listFragment = ListFragment()
        // Get the FragmentManager
        val fragmentManager: FragmentManager = supportFragmentManager
        // Start a new FragmentTransaction
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        // Replace the existing fragment in the "activity_main" container with the new ListFragment
        fragmentTransaction.replace(R.id.activity_main, listFragment).commit()
    }

    private fun checkInternetConnection() {
        if (networkChangeReceiver.isNetworkConnected(this)) {
           // Snackbar.make(findViewById(android.R.id.content), "Internet Connected", Snackbar.LENGTH_SHORT).show()
        } else {
            Snackbar.make(findViewById(android.R.id.content), "No internet connection", Snackbar.LENGTH_INDEFINITE).show()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart Called")
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangeReceiver, intentFilter)
        Log.d(TAG, "onResume Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkChangeReceiver)
        Log.d(TAG, "onDestroy Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart Called")
    }

    override fun isConnected(b: Boolean) {
        Log.d("notef","kdf")
    }
}









