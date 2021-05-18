package com.gemastik.liosiapp.data.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gemastik.liosiapp.R
import com.gemastik.liosiapp.databinding.FragmentBreatheBinding

class BreatheFragment : Fragment() {

    private lateinit var binding: FragmentBreatheBinding
    private lateinit var latarSuaraSelected: String
    private lateinit var durasiSelected: String
    private lateinit var backgroundSelected: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBreatheBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupSpinner()
    }

    private fun setupView() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.menuLatarSuara.tvTitle.text = getString(R.string.menu_breathe_1)
        binding.menuDurasiBernafas.tvTitle.text = getString(R.string.menu_breathe_2)
        binding.menuBackground.tvTitle.text = getString(R.string.menu_breathe_3)
        binding.btnMulai.setOnClickListener {
            findNavController().navigate(
                R.id.action_breatheFragment_to_detailBreatheFragment,
                bundleOf(
                    "latar_suara" to latarSuaraSelected,
                    "durasi" to durasiSelected,
                    "background" to backgroundSelected
                )
            )
        }
    }

    private fun setupSpinner() {
        val spinner1 = binding.menuLatarSuara.spinner
        val spinner2 = binding.menuDurasiBernafas.spinner
        val spinner3 = binding.menuBackground.spinner

        val option_latar_suara = arrayListOf("Air Terjun", "Api Unggun", "Pantai")
        val option_durasi_bernafas = arrayListOf("8 Set", "12 Set", "16 Set")
        val option_background = arrayListOf("Danau", "Air Terjun", "Api Unggun", "Pantai")

        spinner1.adapter = ArrayAdapter<String>(
            requireContext(),
            R.layout.color_spinner_layout,
            option_latar_suara
        )
        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                latarSuaraSelected = option_latar_suara[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }

        spinner2.adapter = ArrayAdapter<String>(
            requireContext(),
            R.layout.color_spinner_layout,
            option_durasi_bernafas
        )
        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                durasiSelected = option_durasi_bernafas[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }

        spinner3.adapter =
            ArrayAdapter<String>(requireContext(), R.layout.color_spinner_layout, option_background)
        spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                backgroundSelected = option_background[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }
    }

}