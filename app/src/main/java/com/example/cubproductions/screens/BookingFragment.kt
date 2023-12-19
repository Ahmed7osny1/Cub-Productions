package com.example.cubproductions.screens

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.cubproductions.R
import com.example.cubproductions.data.UserBooking
import com.example.cubproductions.databinding.FragmentBookingBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.Calendar

class BookingFragment : Fragment() {

    private lateinit var binding: FragmentBookingBinding
    private var radioButton: RadioButton? = null
    private lateinit var brandName: String
    private lateinit var sessionDate: String
    private lateinit var sessionType: String

    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBookingBinding.inflate(inflater, container, false)

        database = FirebaseDatabase.getInstance().getReference("Booking")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->

            radioButton = requireActivity().findViewById(checkedId)

        }

        binding.etDate.setOnClickListener { calenderShow() }

        binding.bookBtn.setOnClickListener {

            if (checkBook()) {

                brandName = binding.brandName.editText!!.text.toString()
                sessionDate = binding.etDate.text.toString()
                sessionType = radioButton!!.text.toString()

                val booking = UserBooking(brandName, sessionDate, sessionType)

                val userId = database.push().key!!

                database.child(userId).setValue(booking)

                Toast.makeText(
                    requireContext(),
                    "Booked Successfully",
                    Toast.LENGTH_LONG
                ).show()


                Navigation.findNavController(view).navigate(
                    R.id.action_bookingFragment_to_homeFragment
                )
            }
        }

    }

    private fun calenderShow() {

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, monthOfYear, dayOfMonth ->
                binding.etDate.setText("$year-${(monthOfYear + 1)}-$dayOfMonth")
            }, year, month, day
        )
        //currentTimeMillis
        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.show()

    }

    private fun checkBook(): Boolean {

        if (binding.etDate.text?.isEmpty() == true ||
            binding.brandName.editText?.text?.isEmpty() == true ||
            radioButton == null
        ) {
            if (binding.etDate.text?.isEmpty() == true) {
                Toast.makeText(
                    requireContext(),
                    "Please Enter Your Date",
                    Toast.LENGTH_LONG
                ).show()
            } else if (binding.brandName.editText?.text?.isEmpty() == true) {
                Toast.makeText(
                    context,
                    "Please Enter Your Brand Name",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Please choose Your Session Type",
                    Toast.LENGTH_LONG
                ).show()
            }
            return false
        } else return true


    }

}