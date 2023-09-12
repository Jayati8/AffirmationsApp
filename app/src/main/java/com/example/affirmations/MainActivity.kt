package com.example.affirmations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.affirmations.databinding.ActivityMainBinding

const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate Called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create an instance of the ListFragment
        val listFragment = ListFragment()

        // Get the FragmentManager
        val fragmentManager: FragmentManager = supportFragmentManager

        // Start a new FragmentTransaction
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        // Replace the existing fragment in the "activity_main" container with the new ListFragment
        fragmentTransaction.replace(R.id.activity_main, listFragment).commit()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart Called")
    }

    override fun onResume() {
        super.onResume()
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
        Log.d(TAG, "onDestroy Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart Called")
    }
}









