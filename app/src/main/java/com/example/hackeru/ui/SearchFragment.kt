package com.example.hackeru.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hackeru.R
import com.example.hackeru.adapters.SearchThemeAdapter
import com.example.hackeru.databinding.FragmentSearchBinding
import com.example.hackeru.models.SearchCategory
import com.example.hackeru.viewmodel.SearchViewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    lateinit var searchViewModel: SearchViewModel

    lateinit var adapter: SearchThemeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater)
        searchViewModel = ViewModelProvider(requireActivity())[SearchViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        searchViewModel.searchLiveData.observe(viewLifecycleOwner) {
            findNavController()
                .navigate(R.id.action_search_fragment_to_search_results_fragment)
        }

        binding.searchThemeRv.layoutManager = GridLayoutManager(requireContext(), 3)
        adapter = SearchThemeAdapter(
            SearchCategory.values().map { it.category }
        )
        binding.searchThemeRv.adapter = adapter

        binding.searchButton.setOnClickListener {
            if (adapter.selectedThemes() == null)
                adapter.selectTheme(SearchCategory.Multi.category)
            searchViewModel.search(
                binding.searchNameEditText.text.toString(),
                adapter.selectedThemes()!!
            )

        }
    }
}