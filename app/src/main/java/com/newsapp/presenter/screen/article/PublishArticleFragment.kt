package com.newsapp.presenter.screen.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.newsapp.R
import com.newsapp.databinding.FragmentPublishBinding
import com.newsapp.util.hideKeyboard

class PublishArticleFragment : Fragment() {
    private lateinit var binding: FragmentPublishBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPublishBinding.inflate(
            inflater, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etAddTags.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (!binding.etAddTags.text.isNullOrEmpty() && !binding.etAddTags.text.isNullOrBlank()) {
                    addChips(binding.etAddTags.text.toString())
                    binding.etAddTags.setText("")
                    hideKeyboard(v)
                }
                return@setOnEditorActionListener true
            }
            false
        }

        spinnerFunctionality()
        //recyclerViewFunctionality()

        binding.tvPublish.setOnClickListener {
            openStoryPublished()
        }
        binding.imgPublishBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun list(): List<String> {
        return listOf<String>(
            "Technology", "ai", "computer", "artificialIntelligence",
            "innovation", "machine", "digital", "robot"
        )
    }

    private fun spinnerFunctionality() {
        val arrayAdapter = ArrayAdapter.createFromResource(
            requireContext(), R.array.stringSelectTopic,
            android.R.layout.simple_spinner_item
        )
        arrayAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        binding.spinnerPublish.adapter = arrayAdapter
        binding.spinnerPublish
    }

    val openStoryPublished = {
        findNavController().navigate(R.id.fragmentStoryPublished)
    }

    val onBackPressed = {
        findNavController()
            .navigateUp()
        true
    }

    private fun addChips(text: String) {
        val chip = Chip(requireContext())
        chip.text = text

        chip.isCloseIconVisible = true
        chip.setOnCloseIconClickListener {
            binding.chipGroup.removeView(chip)
        }
        binding.chipGroup.addView(chip)
    }
}