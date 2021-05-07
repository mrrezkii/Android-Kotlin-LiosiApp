package com.gemastik.liosiapp.data.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gemastik.liosiapp.databinding.FragmentBreatheBinding

class BreatheFragment : Fragment() {

    private lateinit var binding: FragmentBreatheBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBreatheBinding.inflate(inflater, container, false)
        return binding.root
    }

}