package com.example.affirmations

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.affirmations.adapter.ItemAdapter
import com.example.affirmations.broadcastReceiver.CheckConnection
import com.example.affirmations.broadcastReceiver.NetworkChangeReceiver
import com.example.affirmations.data.Datasource
import com.example.affirmations.databinding.FragmentListBinding
import com.google.android.material.snackbar.Snackbar

const val TAG_1 = "ListFragment"

@Suppress("DEPRECATION")
class ListFragment : Fragment(), CheckConnection {
     
    private var _binding: FragmentListBinding? = null
    private lateinit var networkChangeReceiver: NetworkChangeReceiver
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        networkChangeReceiver = NetworkChangeReceiver(this)
        checkInternetConnection()

        Log.d(TAG_1,"onCreate called")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG_1, "onCreateView Called")
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun onItemClick(position: Int) {
         Toast.makeText(requireContext(),"item clicked at $position", Toast.LENGTH_LONG).show()
    }
    private fun checkInternetConnection() {
        if (networkChangeReceiver.isNetworkConnected(requireContext())) {
            Log.d("InternetCheck", "connected")
            // Snackbar.make(findViewById(android.R.id.content), "Internet Connected", Snackbar.LENGTH_SHORT).show()
        } else {
            Log.d("InternetCheck", "not Connected")
            Snackbar.make(requireActivity().findViewById(android.R.id.content), "No internet connection", Snackbar.LENGTH_INDEFINITE).show()
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         recyclerView = binding.recyclerView

        val myDataset = Datasource().loadAffirmations()
        recyclerView.adapter = ItemAdapter(this,myDataset,this)
        recyclerView.setHasFixedSize(true)
        Log.d(TAG_1, "onViewCreated Called")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG_1, "onStart Called")
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        activity?.registerReceiver(networkChangeReceiver, intentFilter)
        Log.d(TAG, "onResume Called")
        Log.d(TAG_1, "onResume Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG_1, "onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG_1, "onStop Called")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d(TAG_1, "onDestroyView Called")
    }
    override fun onDestroy() {
        super.onDestroy()
        activity?.unregisterReceiver(networkChangeReceiver)
        Log.d(TAG_1, "onDestroy Called")
    }

    private fun showSnackbar() {
        val snackbar = Snackbar.make(requireActivity().findViewById(android.R.id.content), "No internet connection", Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Retrieve") {
            // Retrieve button clicked, check for internet connection again
            if(networkChangeReceiver.isNetworkConnected(requireContext()))
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
       if(b)
       {
           showSnackbar()
       }
    }
}

