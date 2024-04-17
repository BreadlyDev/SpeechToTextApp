package com.example.speechtotextapp.ui.register

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.speechtotextapp.R
import com.example.speechtotextapp.databinding.FragmentLoginBinding
import com.example.speechtotextapp.databinding.FragmentRegisterBinding
import com.example.speechtotextapp.liveData.AuthViewModel
import com.example.speechtotextapp.requests.LoginRequest
import com.example.speechtotextapp.requests.RegisterRequest
import com.example.speechtotextapp.responses.AuthResponse
import com.example.speechtotextapp.sttApi.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnSignIn.setOnClickListener {
                findNavController().navigate(R.id.action_RegisterFragment_to_LoginFragment)
            }
            btnSignUp.setOnClickListener {
                register(
                    RegisterRequest(
                        txtUsername.text.toString(),
                        txtEmail.text.toString(),
                        txtPassword.text.toString()
                    )
                )
            }
        }
    }

    fun register(authRequest: RegisterRequest) {
        try {
            val response = RetrofitClient.apiInterface.register(authRequest)

            response.enqueue(object: Callback<AuthResponse> {
                @SuppressLint("SetTextI18n")
                override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                    val responseBody = response.body()

                    if(response.isSuccessful && responseBody != null) {
                        findNavController().navigate(R.id.action_RegisterFragment_to_HomeFragment)
    //                    view.findViewById<TextView>(R.id.txt_activity).text = responseBody.activity
    //                    view.findViewById<TextView>(R.id.txt_accessibility).text = responseBody.accessibility.toString()
    //                    view.findViewById<TextView>(R.id.txt_participants).text = responseBody.participants.toString()
    //                    view.findViewById<TextView>(R.id.txt_type).text = responseBody.type
    //                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    //                    view.findViewById<TextView>(R.id.txt_price).text = "${responseBody.price} $"
                    }
                    Log.d("API", "error")
                    return
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    return
                }
            })
        } catch (e: Error) {
            Log.d("API", e.toString())
            binding.txtError.text = e.toString()
        }
    }
}