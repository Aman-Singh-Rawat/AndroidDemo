package com.newsapp.ui.bookMarkNav

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.newsapp.R
import com.newsapp.databinding.FragmentBookmarkBinding
import com.newsapp.ui.homeNav.recentstories.TagsRecyclerView

class BookmarkFragment : Fragment() {
    private lateinit var binding: FragmentBookmarkBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookmarkBinding.inflate(
            inflater, container, false
        )
        setUpStoriesTag()
        return binding.root
    }
    private fun setUpStoriesTag() {
        val tagsRecyclerView = TagsRecyclerView(featureList(), true, requireContext(), findNavController())
        binding.rvBookmarkTag.adapter = tagsRecyclerView
    }
    private fun featureList(): List<String> {
        return listOf(
            "All","Politics","Technology","Business"
        )
    }
}