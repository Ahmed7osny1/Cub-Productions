package com.example.cubproductions.screens

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.cubproductions.R
import com.example.cubproductions.databinding.FragmentBookingBinding
import java.util.Calendar

class BookingFragment : Fragment() {

    private lateinit var binding: FragmentBookingBinding
    private var radioButton: RadioButton? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBookingBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->

            radioButton = requireActivity().findViewById(checkedId)

        }

        binding.etDate.setOnClickListener { calenderShow() }

        binding.bookBtn.setOnClickListener {

            if(checkBook()){
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

    private fun checkBook(): Boolean{

        if(binding.etDate.text?.isEmpty() == true ||
            binding.brandName.editText?.text?.isEmpty() == true ||
            radioButton == null
        ){
            if(binding.etDate.text?.isEmpty() == true){
                Toast.makeText(
                    requireContext(),
                    "Please Enter Your Date",
                    Toast.LENGTH_LONG
                ).show()
            } else if(binding.brandName.editText?.text?.isEmpty() == true){
                Toast.makeText(
                    requireContext(),
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
        }else return true


    }

}