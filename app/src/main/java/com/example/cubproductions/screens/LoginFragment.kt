package com.example.cubproductions.screens

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.cubproductions.R
import com.example.cubproductions.databinding.FragmentLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var email: String
    private lateinit var password: String

    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        auth = Firebase.auth

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.logIn.setOnClickListener {

            if (checkLogin()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user = auth.currentUser
                            Log.d("login", "signInWithEmail:success ${user?.email}")

                            loginFinished("$user")
                            Navigation.findNavController(requireView()).navigate(
                                R.id.action_loginFragment_to_enterActivity
                            )

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                requireContext(),
                                "Login failed, Email or Password not correct!",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
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