package com.agtium.technical.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.agtium.technical.databinding.FragmentEmptyBinding

class EmptyFragment: Fragment() {

    private lateinit var binding: FragmentEmptyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        android.util.Log.d("EmptyFragment", "onCreateView")
        binding = FragmentEmptyBinding.inflate(inflater, container, false)
        return binding.root
    }

}