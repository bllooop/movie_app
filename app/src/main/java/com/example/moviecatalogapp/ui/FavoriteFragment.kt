package com.example.moviecatalogapp.ui

import androidx.fragment.app.Fragment
import com.example.moviecatalogapp.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}