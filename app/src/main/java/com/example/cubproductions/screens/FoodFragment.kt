package com.example.cubproductions.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.cubproductions.R
import com.example.cubproductions.adapter.ImageAdapter
import com.example.cubproductions.databinding.FragmentFoodBinding

class FoodFragment : Fragment() {

    private lateinit var binding: FragmentFoodBinding
    private lateinit var imageList: ArrayList<Int>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFoodBinding.inflate(inflater, container, false)

        imageList = arrayListOf()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageList.add(
            R.drawable.food1
        )
        imageList.add(
            R.drawable.food2
        )
        imageList.add(
            R.drawable.food3
        )
        imageList.add(
            R.drawable.food4
        )
        imageList.add(
            R.drawable.food5
        )

        val rvAdapter = ImageAdapter(imageList)
        binding.foodRecycleview.adapter = rvAdapter
        binding.foodRecycleview.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

    }


}