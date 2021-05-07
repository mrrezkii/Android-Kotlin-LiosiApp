package com.gemastik.liosiapp.data.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gemastik.liosiapp.databinding.FragmentMoodBinding

class MoodFragment : Fragment() {

    private lateinit var binding: FragmentMoodBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoodBinding.inflate(inflater, container, false)
        return binding.root
    }

}