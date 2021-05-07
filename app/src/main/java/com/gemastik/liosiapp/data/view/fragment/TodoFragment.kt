package com.gemastik.liosiapp.data.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gemastik.liosiapp.R
import com.gemastik.liosiapp.databinding.FragmentTodoBinding

class TodoFragment : Fragment() {

    private lateinit var binding: FragmentTodoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        binding.menuTodo1.btnMenu.text = getString(R.string.breathe)
        binding.menuTodo2.btnMenu.text = getString(R.string.muscle)
        binding.menuTodo3.btnMenu.text = getString(R.string.positif)
        binding.menuTodo1.btnMenu.setOnClickListener {
            findNavController().navigate(R.id.action_todoFragment_to_breatheFragment)
        }
        binding.menuTodo2.btnMenu.setOnClickListener {
            findNavController().navigate(R.id.action_todoFragment_to_muscleFragment)
        }
        binding.menuTodo3.btnMenu.setOnClickListener {
            findNavController().navigate(R.id.action_todoFragment_to_positifFragment)
        }
    }

}