package com.gemastik.liosiapp.data.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gemastik.liosiapp.databinding.FragmentMoodBinding
import com.gemastik.liosiapp.utils.showToast
import com.hsalf.smileyrating.SmileyRating


class MoodFragment : Fragment() {

    private lateinit var binding: FragmentMoodBinding
    private var rating: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        binding.smileRating.setRating(SmileyRating.Type.TERRIBLE, true)
        rating = binding.smileRating.selectedSmiley.rating

        binding.smileRating.setSmileySelectedListener {
            rating = when (it) {
                SmileyRating.Type.TERRIBLE -> 1
                SmileyRating.Type.BAD -> 2
                SmileyRating.Type.OKAY -> 3
                SmileyRating.Type.GOOD -> 4
                SmileyRating.Type.GREAT -> 5
                else -> 0
            }
        }
        binding.btnNext.setOnClickListener { showToast("$rating") }
    }

}