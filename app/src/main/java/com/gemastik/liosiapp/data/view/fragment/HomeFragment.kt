package com.gemastik.liosiapp.data.view.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gemastik.liosiapp.R
import com.gemastik.liosiapp.databinding.FragmentHomeBinding
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    var data = getData(36, 100f)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLineChart(data!!)
        setupView()
    }

    private fun setupLineChart(data: LineData) {
        val color = Color.rgb(137, 230, 81)
        (data.getDataSetByIndex(0) as LineDataSet).circleHoleColor = color

        binding.lineChartBar.lineChart.description.text = "Mood Harin"
        binding.lineChartBar.lineChart.setDrawGridBackground(false)
        binding.lineChartBar.lineChart.setTouchEnabled(true)
        binding.lineChartBar.lineChart.isDragEnabled = true
        binding.lineChartBar.lineChart.setScaleEnabled(true)
        binding.lineChartBar.lineChart.setPinchZoom(false)
        binding.lineChartBar.lineChart.setBackgroundColor(color)
        binding.lineChartBar.lineChart.setViewPortOffsets(10f, 0f, 10f, 0f)
        binding.lineChartBar.lineChart.data = data
        val l: Legend = binding.lineChartBar.lineChart.legend
        l.isEnabled = false
        binding.lineChartBar.lineChart.axisLeft.isEnabled = false
        binding.lineChartBar.lineChart.axisLeft.spaceTop = 40f
        binding.lineChartBar.lineChart.axisLeft.spaceBottom = 40f
        binding.lineChartBar.lineChart.axisRight.isEnabled = false
        binding.lineChartBar.lineChart.xAxis.isEnabled = false
        binding.lineChartBar.lineChart.animateX(2500)
    }

    private fun getData(count: Int, range: Float): LineData? {
        val values = ArrayList<Entry>()
        for (i in 0 until count) {
            val `val` = (Math.random() * range).toFloat() + 3
            values.add(Entry(i.toFloat(), `val`))
        }
        val dataSet = LineDataSet(values, "DataSet 1")
        dataSet.lineWidth = 1.75f
        dataSet.circleRadius = 5f
        dataSet.circleHoleRadius = 2.5f
        dataSet.color = Color.WHITE
        dataSet.setCircleColor(Color.WHITE)
        dataSet.highLightColor = Color.WHITE
        dataSet.setDrawValues(false)

        return LineData(dataSet)
    }

    private fun setupView() {
        binding.menuHome1.tvTitle.text = getString(R.string.todo)
        binding.menuHome2.tvTitle.text = getString(R.string.premium)
        binding.menuHome1.btnStart.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_todoFragment)
        }
        binding.menuHome2.btnStart.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_premiumFragment)
        }
    }

}