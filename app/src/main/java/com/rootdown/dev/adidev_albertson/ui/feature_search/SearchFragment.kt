package com.rootdown.dev.adidev_albertson.ui.feature_search

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.airbnb.epoxy.EpoxyRecyclerView
import com.google.android.material.snackbar.Snackbar
import com.rootdown.dev.adidev_albertson.R
import com.rootdown.dev.adidev_albertson.acromine
import com.rootdown.dev.adidev_albertson.data.model.AcromineFull
import com.rootdown.dev.adidev_albertson.databinding.FragmentSearchBinding
import com.rootdown.dev.adidev_albertson.scroll
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    val vm: SearchViewModel by viewModels()
    private lateinit var state: String
    private var searchJob: Job? = null
    private lateinit var xCorLayout: CoordinatorLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater)
        val epoxyView: EpoxyRecyclerView = binding.rvTask
        xCorLayout = binding.searchCoordinatedLayout

        vm.acromineResult.observe(viewLifecycleOwner) {
            setupEpoxy(it,epoxyView)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.appbar_menu, menu)
        val search: MenuItem? = menu.findItem(R.id.character_search)
        val searchView: SearchView = search?.actionView as SearchView
        searchView.queryHint = "Acronym : ADD"
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
            override fun onQueryTextSubmit(query: String?): Boolean {
                state = query.toString()
                updateRepoLsIn()
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setupEpoxy(result: AcromineFull.AcromineFullItem, epoxy: EpoxyRecyclerView) {
        val xLs = result.lfs
        vm.predaciteNum = xLs?.count()!!
        vm.predicateNum2 = xLs.size
        Log.w("SIZE", "SIZE: ${vm.predicateNum2}")
        epoxy.withModels {
            acromine {
                vm.makeIds(vm.predicateNum2)
                id(vm.countAcro)
                acro(result)
                clickListener { x ->
                    vm.saveSearch()
                    Snackbar.make(binding.searchCoordinatedLayout, "Delete ${vm.acromineResult.value?.sf}?", Snackbar.LENGTH_LONG)
                        .setActionTextColor(Color.GREEN)
                        .setAction("Undo", DeleteStrainListener(id))
                        .show()
                }
            }
            if ( xLs != null ){
                xLs.forEach { x ->
                    vm.makeIds(vm.predaciteNum)
                    Log.w("PRE", "COUNT: $vm.count")
                    scroll {
                        id(vm.count)
                        lf(x)
                    }
                }
            }
        }
    }

    private fun updateRepoLsIn() {
        val x: String = state
        search(x)
    }

    private fun search(query: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            vm.getAcromineReults(query)
        }
    }
    inner class DeleteStrainListener(id: Int): View.OnClickListener{
        val currentAcromineId: Int = id
        override fun onClick(v: View?) {
            lifecycleScope.launch {
                vm.deleteSearch(currentAcromineId)
            }
        }
    }
}
data class Id(val id: Long = 0L)