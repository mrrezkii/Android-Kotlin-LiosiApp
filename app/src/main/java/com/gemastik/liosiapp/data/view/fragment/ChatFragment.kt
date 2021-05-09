package com.gemastik.liosiapp.data.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gemastik.liosiapp.data.model.PsychologistModel
import com.gemastik.liosiapp.data.view.adapter.PsychologistAdapter
import com.gemastik.liosiapp.databinding.FragmentChatBinding

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private lateinit var psychologistAdapter: PsychologistAdapter
    private var psychologistList: ArrayList<PsychologistModel> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupRecyclerView()
        setupList()
    }

    private fun setupList() {
        psychologistList.add(0, PsychologistModel("Kak Rezki", "Sistem Informasi"))
        psychologistList.add(1, PsychologistModel("Kak Novi", "Informatika"))
        psychologistList.add(2, PsychologistModel("Kak Teguh", "Kedokteran"))
        psychologistList.add(3, PsychologistModel("Kak Rezki", "Sistem Informasi"))
        psychologistList.add(4, PsychologistModel("Kak Novi", "Informatika"))
        psychologistList.add(5, PsychologistModel("Kak Teguh", "Kedokteran"))
        psychologistList.add(6, PsychologistModel("Kak Rezki", "Sistem Informasi"))
        psychologistList.add(7, PsychologistModel("Kak Novi", "Informatika"))
        psychologistList.add(8, PsychologistModel("Kak Teguh", "Kedokteran"))
        psychologistList.add(9, PsychologistModel("Kak Rezki", "Sistem Informasi"))
        psychologistList.add(10, PsychologistModel("Kak Novi", "Informatika"))
        psychologistList.add(11, PsychologistModel("Kak Teguh", "Kedokteran"))
        psychologistAdapter.setData(psychologistList)
    }

    private fun setupView() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupRecyclerView() {
        psychologistAdapter =
            PsychologistAdapter(arrayListOf(), object : PsychologistAdapter.OnAdapterListener {
                override fun onClick(result: PsychologistModel) {


                }
            })
        binding.listResult.adapter = psychologistAdapter
    }

}