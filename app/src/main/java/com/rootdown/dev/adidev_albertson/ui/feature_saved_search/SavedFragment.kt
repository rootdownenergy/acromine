package com.rootdown.dev.adidev_albertson.ui.feature_saved_search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.airbnb.epoxy.EpoxyRecyclerView
import com.rootdown.dev.adidev_albertson.data.local.AcrominDataItem
import com.rootdown.dev.adidev_albertson.data.model.AcromineFull
import com.rootdown.dev.adidev_albertson.databinding.FragmentSavedBinding
import com.rootdown.dev.adidev_albertson.databinding.FragmentSearchBinding
import com.rootdown.dev.adidev_albertson.saved
import com.rootdown.dev.adidev_albertson.ui.feature_search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedFragment : Fragment() {

    private lateinit var binding: FragmentSavedBinding

    val vm: SearchViewModel by viewModels()

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
        val epoxyView: EpoxyRecyclerView = binding.rvSavedSearchs
        vm.savedSearches.observe(viewLifecycleOwner) {
            setupEpoxy(it,epoxyView)
        }
        return binding.root
    }

    private fun setupEpoxy(result: List<AcrominDataItem>, epoxy: EpoxyRecyclerView){
        epoxy.withModels {
            result.forEach {  x ->

                saved {
                    id(x.id)
                    acro(x)
                }
            }
        }
    }
}