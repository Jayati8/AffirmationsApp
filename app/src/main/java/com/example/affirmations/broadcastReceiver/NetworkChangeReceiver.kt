package broadcastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import com.example.affirmations.SplashActivity
import com.google.android.material.snackbar.Snackbar

@Suppress("DEPRECATION")
// Define a BroadcastReceiver that listens for network connectivity changes.
class NetworkChangeReceiver(private val rootView: View, private val context: SplashActivity) : BroadcastReceiver() {

    // This function is called when a broadcast intent is received.
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == ConnectivityManager.CONNECTIVITY_ACTION) {
            // Get a reference to the ConnectivityManager to check network status.
            val connectivityManager =
                context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            // Retrieve information about the currently active network.
            val activeNetworkInfo = connectivityManager.activeNetworkInfo

            // Check if the active network is not null and is connected.
            val isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnected
            if (isConnected) {
                // Internet is available, perform your action here
                showSnackbar("Internet Connected!")
            }else{
                // Internet is not available, show the custom Snackbar with a "Retrieve" action
                showRetrieveSnackbar()
            }
        }
    }

    //A private function to display a snackbar with a specified message
    private fun showSnackbar(message:String) {
        // Create a Snackbar with the given message and associate it with the rootView.
        val snackbar=Snackbar.make(rootView, message, Snackbar.LENGTH_LONG)
        //show snackbar on screen
        snackbar.show()
    }

    private fun showRetrieveSnackbar(){
        val snackbar = Snackbar.make(rootView,"No internet connection",Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Retrieve") {
            // Retrieve action: Check internet connectivity again
            if (isInternetAvailable(context)) {
                // Internet is available now, perform your action here
                showSnackbar("Internet is available!")
            } else {
                // Internet is still not available, show the retrieve Snackbar again
                showSnackbar("Still no internet connection!")
            }
        }
        snackbar.show()
    }


    //  internet connectivity check logic
    private fun isInternetAvailable(context: Context): Boolean {
        // Get a reference to the ConnectivityManager to check network status.
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        // Retrieve the active network. If it's null, there's no active network connection.
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        // Retrieve network capabilities for the active network.
        val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        // Check if the active network has the capability of INTERNET, indicating internet access.
        return actNw.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}










