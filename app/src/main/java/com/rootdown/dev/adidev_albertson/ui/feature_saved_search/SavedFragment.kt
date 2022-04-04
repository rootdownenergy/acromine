package com.rootdown.dev.adidev_albertson.ui.feature_saved_search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.airbnb.epoxy.EpoxyRecyclerView
import com.rootdown.dev.adidev_albertson.databinding.FragmentSavedBinding
import com.rootdown.dev.adidev_albertson.databinding.FragmentSearchBinding

class SavedFragment : Fragment() {

    private lateinit var binding: FragmentSavedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedBinding.inflate(inflater)
        return binding.root
    }
}