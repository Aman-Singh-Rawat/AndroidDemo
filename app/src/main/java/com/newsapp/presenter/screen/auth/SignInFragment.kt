package com.newsapp.presenter.screen.auth

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.newsapp.R

@Suppress("UNREACHABLE_CODE")
class SignInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)

    }



//    private fun diloge(){
//        Handler(Looper.getMainLooper()).postDelayed({
//           val btnSignIn = view?.findViewById<Button>(R.id.btnSignIn)
//            btnSignIn?.setOnClickListener{
//                findNavController().navigate()
//            }
//        },3000)
//    }






}