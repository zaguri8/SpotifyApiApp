package com.example.hackeru.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackeru.R
import com.example.hackeru.adapters.SearchResultsAdapter
import com.example.hackeru.databinding.FragmentSearchResultsBinding
import com.example.hackeru.viewmodel.SearchViewModel

class SearchResultsFragment : Fragment() {


    private var _binding: FragmentSearchResultsBinding? = null
    val binding: FragmentSearchResultsBinding get() = _binding!!

    lateinit var searchViewModel: SearchViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        searchViewModel = ViewModelProvider(requireActivity())[SearchViewModel::class.java]
        _binding = FragmentSearchResultsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchResultsRv.layoutManager = LinearLayoutManager(requireContext())

        searchViewModel.selectedItem.observe(viewLifecycleOwner) { selectedItem ->

            findNavController()
                .navigate(R.id.action_search_results_fragment_to_item_details_fragment)
        }

        searchViewModel.searchLiveData.observe(viewLifecycleOwner) { searchResults ->
            val adapter = SearchResultsAdapter(searchResults.toDataList()) {
                searchViewModel.selectItem(it)
            }
            binding.searchResultsRv.adapter = adapter
        }

    }


}