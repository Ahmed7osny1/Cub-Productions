package com.example.cubproductions.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cubproductions.R
import com.example.cubproductions.adapter.ImageAdapter
import com.example.cubproductions.databinding.FragmentProductBinding

class ProductFragment : Fragment() {

    private lateinit var binding: FragmentProductBinding
    private lateinit var imageList: ArrayList<Int>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductBinding.inflate(inflater, container, false)

        imageList = arrayListOf()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageList.add(
            R.drawable.product1
        )
        imageList.add(
            R.drawable.product2
        )
        imageList.add(
            R.drawable.product3
        )

        val rvAdapter = ImageAdapter(imageList)
        binding.productRecycleview.adapter = rvAdapter
        binding.productRecycleview.layoutManager =
            LinearLayoutManager(requireContext())

    }
}