package com.gemastik.liosiapp.data.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gemastik.liosiapp.data.model.PsychologistModel
import com.gemastik.liosiapp.databinding.AdapterListPsychologistBinding

class PsychologistAdapter(
    var psychologists: ArrayList<PsychologistModel>,
    var listener: OnAdapterListener
) : RecyclerView.Adapter<PsychologistAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        AdapterListPsychologistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val psychologist = psychologists[position]
        holder.binding.tvTitle.text = psychologist.title
        holder.binding.tvSubtitle.text = psychologist.subtitle
    }

    override fun getItemCount() = psychologists.size

    interface OnAdapterListener {
        fun onClick(result: PsychologistModel)
    }

    class ViewHolder(val binding: AdapterListPsychologistBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun setData(data: List<PsychologistModel>) {
        psychologists.clear()
        psychologists.addAll(data)
        notifyDataSetChanged()
    }

}