package com.example.speechtotextapp.ui.register

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.speechtotextapp.R
import com.example.speechtotextapp.requests.RegisterRequest
import com.example.speechtotextapp.responses.AuthResponse
import com.example.speechtotextapp.sttApi.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale

class RegisterFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    fun register(authRequest: RegisterRequest, view: View) {
        val response = RetrofitClient.apiInterface.register(authRequest)
        response.enqueue(object: Callback<AuthResponse> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                val responseBody = response.body()

                if(response.isSuccessful && responseBody != null) {
                    findNavController().navigate(R.id.action_RegisterFragment_to_navActivity)
//                    view.findViewById<TextView>(R.id.txt_activity).text = responseBody.activity
//                    view.findViewById<TextView>(R.id.txt_accessibility).text = responseBody.accessibility.toString()
//                    view.findViewById<TextView>(R.id.txt_participants).text = responseBody.participants.toString()
//                    view.findViewById<TextView>(R.id.txt_type).text = responseBody.type
//                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
//                    view.findViewById<TextView>(R.id.txt_price).text = "${responseBody.price} $"
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
            }
        })

    }
}