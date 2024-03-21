package com.newsapp.presenter.screen.auth

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.cast.framework.media.ImagePicker
import com.newsapp.R
import com.newsapp.databinding.FragmentProfileBinding
import com.newsapp.databinding.FragmentProfilePublicBinding
import com.newsapp.models.User
import com.newsapp.presenter.screen.auth.login.LoginViewModel
import com.newsapp.ui.profileNav.viewmodel.ViewModelProfile
import com.newsapp.util.SharedPrefsManager

class PublicProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfilePublicBinding
    private val viewModel : ViewModelProfile by viewModels()
    private val prefs by lazy { SharedPrefsManager.getInstance(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfilePublicBinding.inflate(
            inflater, container, false
        )
        return binding.root

    }

    @SuppressLint("SetTextI18n")
    private fun changingValue() {
        binding.includeProfile.tvEmail.text = "Full Name"
        binding.includeProfile.tvPassword.text = "UserName"
        binding.includeProfile.etFillEmail.hint = "e.g. John"
        binding.includeProfile.etFillPassWord.hint = "john@gmail.com"
        binding.includeBio.etBio.hint = "Tech enthusiast, likes to share stories a..."
        binding.includeBio.tvBio.text = "Bio"

        binding.includeBio.etBio.setCompoundDrawablesRelativeWithIntrinsicBounds(
            null, null, null, null)
        binding.includeBio.etBio.inputType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME
        binding.includeProfile.tvEmail.inputType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME
        binding.includeProfile.tvPassword.inputType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME
        binding.includeProfile.etFillEmail.setCompoundDrawablesRelativeWithIntrinsicBounds(
            null, null, null, null)
        binding.includeProfile.etFillPassWord.setCompoundDrawablesRelativeWithIntrinsicBounds(
            null, null, null, null)

    }

    val onBackPressed = {
        findNavController().navigateUp()
        true
    }
    val openAllSetFragment = {
        findNavController().navigate(
            R.id.all_Set_Fragment
        )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.includeProfileFragment.btnAllInOne.text = "Finish"

        binding.includeProfileFragment.btnAllInOne.setOnClickListener {
            dataSave()
        }
        binding.ivBackArrowProfile.setOnClickListener {
            onBackPressed()
        }
        binding.ivProfile.setOnClickListener {
            uploadImage(binding.ivProfile)
        }

        changingValue()


    }
    private fun dataSave() {
        val fullName = binding.includeProfile.etFillEmail.text.toString()
        val name = binding.includeProfile.etFillPassWord.text.toString()
        val bio = binding.includeBio.etBio.text.toString()
        val website = binding.etWebsite.text.toString()

        if(userInputOrNot(fullName, name)) {
            viewModel.setData(fullName, name, bio, website)

            prefs.saveUser(User(name = fullName, email = name, bio = bio, website = website  ))
            openAllSetFragment()

        } else {
            Toast.makeText(requireContext(), "Please fill the userName or name", Toast.LENGTH_SHORT).show()
        }

    }

    private fun uploadImage(ivProfile: ImageView?) {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val ivProfile = view?.findViewById<ImageView>(R.id.ivProfile)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {
                val uri = data?.data
                ivProfile?.setImageURI(uri)
            }
        }
    }
    private fun userInputOrNot(fullName: String, UserName: String): Boolean {
        return fullName.isNotEmpty() && UserName.isNotEmpty()
    }

}


