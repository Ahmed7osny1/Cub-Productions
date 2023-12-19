package com.example.cubproductions.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.cubproductions.R
import com.example.cubproductions.adapter.BookingAdatpter
import com.example.cubproductions.adapter.ImageAdapter
import com.example.cubproductions.data.UserBooking
import com.example.cubproductions.databinding.FragmentFashonBinding

class FashonFragment : Fragment() {

    private lateinit var binding: FragmentFashonBinding
    private lateinit var imageList: ArrayList<Int>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFashonBinding.inflate(inflater, container, false)

        imageList = arrayListOf()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageList.add(
            R.drawable.fashon1
        )
        imageList.add(
            R.drawable.fashon2
        )
        imageList.add(
            R.drawable.fashon3
        )
        imageList.add(
            R.drawable.fashon4
        )
        imageList.add(
            R.drawable.fashon5
        )

        val rvAdapter = ImageAdapter(imageList)
        binding.fashonRecycleview.adapter = rvAdapter
        binding.fashonRecycleview.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

    }


}