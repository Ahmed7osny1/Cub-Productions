package com.example.cubproductions.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.cubproductions.R
import com.example.cubproductions.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var confirmPassword: String
    private lateinit var phone: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signup.setOnClickListener {

            if (createAccount()) {
                Navigation.findNavController(requireView()).navigate(
                    R.id.action_registerFragment_to_loginFragment
                )
            }
        }

        binding.backLogin.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(
                R.id.action_registerFragment_to_loginFragment
            )
        }

    }

    private fun createAccount(): Boolean {

        firstName = binding.firstname.editText?.text.toString()
        lastName = binding.lastname.editText?.text.toString()
        phone = binding.phonenumber.editText?.text.toString()
        email = binding.email.editText?.text.toString()
        password = binding.password.editText?.text.toString()
        confirmPassword = binding.confirmpassword.editText?.text.toString()

        if (firstName.isEmpty() || lastName.isEmpty() ||
            email.isEmpty() || confirmPassword.isEmpty() || password.length != 8 ||
            phone.length != 11
        ) {
            if (firstName.isEmpty()) Toast.makeText(
                requireActivity(), "Please Enter First Name",
                Toast.LENGTH_LONG
            ).show()
            if (lastName.isEmpty()) Toast.makeText(
                requireActivity(), "Please Enter Last Name",
                Toast.LENGTH_LONG
            ).show()
            if (phone.length != 11) Toast.makeText(
                requireActivity(), "Please check your Phone = 11",
                Toast.LENGTH_LONG
            ).show()
            if (email.isEmpty()) Toast.makeText(
                requireActivity(), "Please Enter Email",
                Toast.LENGTH_LONG
            ).show()
            if (password.length != 8) Toast.makeText(
                requireActivity(), "Please check your Password = 8",
                Toast.LENGTH_LONG
            ).show()
            if (confirmPassword == password) Toast.makeText(
                requireActivity(), "Please check your Confirm Password",
                Toast.LENGTH_LONG
            ).show()
        } else return true
        return false
    }

}