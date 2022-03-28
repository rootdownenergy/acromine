package com.rootdown.dev.adidev_albertson.ui.feature_search

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.rootdown.dev.adidev_albertson.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    val vm: SearchViewModel by viewModels()

}