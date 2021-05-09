package com.gemastik.liosiapp.data.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gemastik.liosiapp.R
import com.gemastik.liosiapp.databinding.FragmentMuscleBinding

class MuscleFragment : Fragment() {

    private lateinit var binding: FragmentMuscleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMuscleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.scrollContainer.menuMuscle1.tvTitle.text = getString(R.string.menu_muscle_1)
        binding.scrollContainer.menuMuscle1.ivBackground.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.balasana
            )
        )

        binding.scrollContainer.menuMuscle2.tvTitle.text = getString(R.string.menu_muscle_2)
        binding.scrollContainer.menuMuscle2.ivBackground.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.viparita_korani
            )
        )

        binding.scrollContainer.menuMuscle3.tvTitle.text = getString(R.string.menu_muscle_3)
        binding.scrollContainer.menuMuscle4.ivBackground.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.makarasona
            )
        )

        binding.scrollContainer.menuMuscle4.tvTitle.text = getString(R.string.menu_muscle_4)
        binding.scrollContainer.menuMuscle4.ivBackground.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.savasana
            )
        )
    }

}