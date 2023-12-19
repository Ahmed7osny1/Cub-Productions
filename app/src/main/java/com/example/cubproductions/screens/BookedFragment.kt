package com.example.cubproductions.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cubproductions.adapter.BookingAdatpter
import com.example.cubproductions.data.UserBooking
import com.example.cubproductions.databinding.FragmentBookedBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class BookedFragment : Fragment() {

    private lateinit var binding: FragmentBookedBinding
    private lateinit var userId: String
    private lateinit var database: DatabaseReference
    private lateinit var bookingList: ArrayList<UserBooking>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBookedBinding.inflate(inflater, container, false)

        database = FirebaseDatabase.getInstance().getReference("Booking")
        userId = FirebaseAuth.getInstance().currentUser!!.uid

        bookingList = arrayListOf()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                bookingList.clear()
                if (snapshot.exists()) {
                    for (contactSnap in snapshot.children) {
                        val contacts = contactSnap.getValue(UserBooking::class.java)
                        bookingList.add(contacts!!)
                    }
                }

                val rvAdapter = BookingAdatpter(bookingList)
                binding.BookedRecycleView.adapter = rvAdapter
                binding.BookedRecycleView.layoutManager =
                    LinearLayoutManager(requireContext())
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, " error : $error", Toast.LENGTH_SHORT).show()
            }

        })
    }

}