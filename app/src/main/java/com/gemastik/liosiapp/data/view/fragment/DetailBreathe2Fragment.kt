package com.gemastik.liosiapp.data.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gemastik.liosiapp.databinding.FragmentDetailBreathe2Binding

class DetailBreathe2Fragment : Fragment() {

    private lateinit var binding: FragmentDetailBreathe2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBreathe2Binding.inflate(inflater, container, false)
        return binding.root
    }
}