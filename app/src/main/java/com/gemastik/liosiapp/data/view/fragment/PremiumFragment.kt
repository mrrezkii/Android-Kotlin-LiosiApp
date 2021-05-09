package com.gemastik.liosiapp.data.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gemastik.liosiapp.R
import com.gemastik.liosiapp.databinding.FragmentPremiumBinding

class PremiumFragment : Fragment() {

    private lateinit var binding: FragmentPremiumBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPremiumBinding.inflate(inflater, container, false)
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

        binding.scrollContainer.menuPremium1.tvTitle.text = getString(R.string.menu_premium_1)
        binding.scrollContainer.menuPremium1.ivBackground.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.bg_breathe_positif
            )
        )
        binding.scrollContainer.menuPremium1.root.setOnClickListener {
            findNavController().navigate(R.id.action_premiumFragment_to_chatFragment)
        }


        binding.scrollContainer.menuPremium2.tvTitle.text = getString(R.string.menu_premium_2)
        binding.scrollContainer.menuPremium2.ivBackground.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.bg_mood_todo
            )
        )
        binding.scrollContainer.menuPremium2.root.setOnClickListener {
            findNavController().navigate(R.id.action_premiumFragment_to_chatFragment)
        }


        binding.scrollContainer.menuPremium3.tvTitle.text = getString(R.string.menu_premium_3)
        binding.scrollContainer.menuPremium3.ivBackground.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.bg_home_positif
            )
        )
        binding.scrollContainer.menuPremium3.root.setOnClickListener {
            findNavController().navigate(R.id.action_premiumFragment_to_chatFragment)
        }


        binding.scrollContainer.menuPremium4.tvTitle.text = getString(R.string.menu_premium_4)
        binding.scrollContainer.menuPremium4.ivBackground.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.bg_breathe_positif
            )
        )
        binding.scrollContainer.menuPremium4.root.setOnClickListener {
            findNavController().navigate(R.id.action_premiumFragment_to_chatFragment)
        }

    }

}