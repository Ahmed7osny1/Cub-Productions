package com.example.cubproductions.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.cubproductions.R
import com.example.cubproductions.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var email: String
    private lateinit var password: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.logIn.setOnClickListener {

            if (checkLogin()) {
                loginFinished("login")
                Navigation.findNavController(requireView()).navigate(
                    R.id.action_loginFragment_to_enterActivity
                )
            }
        }

        binding.Register.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(
                R.id.action_loginFragment_to_registerFragment
            )
        }
    }

    private fun loginFinished(userToken: String?) {
        val sharedPref = requireActivity().getSharedPreferences(
            "test", Context.MODE_PRIVATE
        )
        val editor = sharedPref.edit()
        editor.putString("token", userToken)
        editor.apply()
    }

    private fun checkLogin(): Boolean {
        email = binding.email.editText!!.text.toString()
        password = binding.password.editText!!.text.toString()
        if (email.isEmpty() || password.isEmpty()) {
            if (email.isEmpty()) Toast.makeText(
                requireActivity(), "Please Enter Email",
                Toast.LENGTH_LONG
            ).show()
            if (password.isEmpty()) Toast.makeText(
                requireActivity(), "Please Enter your Password",
                Toast.LENGTH_LONG
            ).show()
        } else return true
        return false
    }

}