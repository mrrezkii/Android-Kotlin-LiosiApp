package com.gemastik.liosiapp.data.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gemastik.liosiapp.R
import com.gemastik.liosiapp.databinding.FragmentDetailMuscleBinding

class DetailMuscleFragment : Fragment() {

    private lateinit var binding: FragmentDetailMuscleBinding
    private val id by lazy { requireArguments().getString("id") }
    private var idNumber: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailMuscleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        idNumber = id!!.toInt()
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        when (idNumber) {
            1 -> {
                binding.tvTitle.text = getString(R.string.menu_muscle_1)
                binding.tvDesc.text = getString(R.string.detail_muscle_1)
                binding.ivMuscle.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.balasana
                    )
                )
            }
            2 -> {
                binding.tvTitle.text = getString(R.string.menu_muscle_2)
                binding.tvDesc.text = getString(R.string.detail_muscle_2)
                binding.ivMuscle.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.viparita_korani
                    )
                )
            }
            3 -> {
                binding.tvTitle.text = getString(R.string.menu_muscle_3)
                binding.tvDesc.text = getString(R.string.detail_muscle_3)
                binding.ivMuscle.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.makarasona
                    )
                )
            }
            4 -> {
                binding.tvTitle.text = getString(R.string.menu_muscle_4)
                binding.tvDesc.text = getString(R.string.detail_muscle_4)
                binding.ivMuscle.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.savasana
                    )
                )
            }
        }
    }
}