package com.example.hackeru.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hackeru.R
import com.example.hackeru.databinding.SearchItemBinding
import com.example.hackeru.models.SearchCategory


class SearchThemeAdapter(
    private val themes: List<String>,
) : RecyclerView.Adapter<SearchThemeAdapter.SearchThemeViewHolder>() {

    private var selectedTheme: String? = null

    fun selectedThemes(): String? {
        return selectedTheme
    }

    fun isThemeSelected(theme: String): Boolean {
        return selectedTheme == theme
    }

    fun selectTheme(theme: String?) {
        selectedTheme = theme
        notifyDataSetChanged()
    }

    fun deselectTheme(theme: String?) {
        selectedTheme = null
        notifyDataSetChanged()
    }

    open class SearchThemeViewHolder(
        private val binding: SearchItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            theme: String, selectedTheme: String?,
            selectTheme: (String?) -> Unit,
            deselectTheme: (String?) -> Unit,
        ) {
            binding.themeLabel.text = theme

            if (selectedTheme == theme) {
                binding.themeLabel.setBackgroundResource(R.drawable.rounded_fill)
            } else {
                binding.themeLabel.setBackgroundResource(R.drawable.rounded)
            }

            binding.themeLabel.setOnClickListener {
                if (selectedTheme != theme) {
                    selectTheme(theme)
                } else {
                    deselectTheme(theme)
                }
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (position < 7) {
            0
        } else {
            1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchThemeViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context))
        return SearchThemeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return themes.size
    }

    override fun onBindViewHolder(holder: SearchThemeViewHolder, position: Int) {
        val theme = themes[position]
        holder.bind(theme, selectedTheme, {
            selectTheme(it)
        }, {
            deselectTheme(it)
        })
    }
}