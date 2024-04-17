package com.example.speechtotextapp.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.speechtotextapp.R
import com.example.speechtotextapp.databinding.FragmentLoginBinding
import com.example.speechtotextapp.liveData.AuthViewModel
import com.example.speechtotextapp.requests.LoginRequest
import com.example.speechtotextapp.sttApi.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: AuthViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnSignUp.setOnClickListener {
                findNavController().navigate(R.id.action_LoginFragment_to_RegisterFragment)
            }
            btnSignIn.setOnClickListener {
                auth(
                    LoginRequest(
                        txtEmail.text.toString(),
                        txtPassword.text.toString()
                    )
                )
            }
        }
    }

    private fun auth(authRequest: LoginRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.apiInterface.login(authRequest)
                if (!response.isSuccessful) {
                    val errorBody = response.errorBody()?.string()
                    if (!errorBody.isNullOrEmpty()) {
                        val message = JSONObject(errorBody).getString("message")
                        requireActivity().runOnUiThread {
                            binding.txtError.text = message
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        requireActivity().runOnUiThread {
                            Toast.makeText(context, "Unexpected error occurred", Toast.LENGTH_SHORT).show()
                        }
                    }
                    return@launch
                }

                val user = response.body()
                if (user != null) {
                    requireActivity().runOnUiThread {
                        Toast.makeText(context, "You are registered, please click next", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_LoginFragment_to_navActivity)
                        viewModel.token.value = user.access_token
                    }
                } else {
                    requireActivity().runOnUiThread {
                        Toast.makeText(context, "User not found", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                requireActivity().runOnUiThread {
                    Toast.makeText(context, "An error occurred: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}
