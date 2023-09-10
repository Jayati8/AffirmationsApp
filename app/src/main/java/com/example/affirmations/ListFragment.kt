package com.example.affirmations

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.affirmations.adapter.ItemAdapter
import com.example.affirmations.data.Datasource
import com.example.affirmations.databinding.FragmentListBinding

const val TAG_1 = "ListFragment"
class ListFragment : Fragment() {
     
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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
        Log.d(TAG_1, "onDestroy Called")
    }
}

