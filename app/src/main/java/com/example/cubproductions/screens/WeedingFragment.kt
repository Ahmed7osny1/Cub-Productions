package com.example.cubproductions.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cubproductions.R
import com.example.cubproductions.adapter.ImageAdapter
import com.example.cubproductions.databinding.FragmentWeedingBinding

class WeedingFragment : Fragment() {

    private lateinit var binding: FragmentWeedingBinding
    private lateinit var imageList: ArrayList<Int>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWeedingBinding.inflate(inflater, container, false)

        imageList = arrayListOf()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageList.add(
            R.drawable.weeding1
        )
        imageList.add(
            R.drawable.weeding2
        )
        imageList.add(
            R.drawable.weeding3
        )
        imageList.add(
            R.drawable.weeding4
        )
        imageList.add(
            R.drawable.weeding5
        )

        val rvAdapter = ImageAdapter(imageList)
        binding.weedingRecycleview.adapter = rvAdapter
        binding.weedingRecycleview.layoutManager =
            LinearLayoutManager(requireContext())

    }
}