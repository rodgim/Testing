package com.rodgim.testing.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rodgim.testing.databinding.FragmentShoppingBinding

class ShoppingFragment : Fragment() {

    private lateinit var binding: FragmentShoppingBinding

    private val viewModel: ShoppingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoppingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}