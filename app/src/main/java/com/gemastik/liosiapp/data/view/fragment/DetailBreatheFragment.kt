package com.gemastik.liosiapp.data.view.fragment

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.gemastik.liosiapp.R
import com.gemastik.liosiapp.databinding.FragmentDetailBreatheBinding


class DetailBreatheFragment : Fragment() {

    private lateinit var binding: FragmentDetailBreatheBinding
    private val latar_suara by lazy { requireArguments().getString("latar_suara") }
    private val durasi by lazy { requireArguments().getString("durasi") }
    private val background by lazy { requireArguments().getString("background") }
    private var setDurasi: Int? = null
    private lateinit var mediaPlayer: MediaPlayer


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBreatheBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRetriveData()
        setupView()
    }

    override fun onResume() {
        super.onResume()
        mediaPlayer.start()
        mediaPlayer.isLooping = true
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.stop()
    }


    private fun setupRetriveData() {
        setDurasi = when (durasi) {
            "8 Set" -> 8
            "12 Set" -> 12
            "16 Set" -> 16
            else -> 1
        }

        when (background) {
            "Danau" -> {
                binding.layoutBackground.setBackgroundResource(R.drawable.bg_danau)
            }
            "Air Terjun" -> {
                binding.layoutBackground.setBackgroundResource(R.drawable.bg_air_terjun)
            }
            "Api Unggun" -> {
                binding.layoutBackground.setBackgroundResource(R.drawable.bg_muscle)
            }
            "Pantai" -> {
                binding.layoutBackground.setBackgroundResource(R.drawable.bg_pantai)
            }
        }


        when (latar_suara) {
            "Air Terjun" -> {
                mediaPlayer = MediaPlayer.create(requireContext(), R.raw.air_terjun)
            }
            "Api Unggun" -> {
                mediaPlayer = MediaPlayer.create(requireContext(), R.raw.api_unggun)
            }
            "Pantai" -> {
                mediaPlayer = MediaPlayer.create(requireContext(), R.raw.pantai)
            }
        }
    }

    private fun setupView() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        Glide.with(requireContext())
            .asGif()
            .load(R.drawable.breathe)
            .addListener(object : RequestListener<GifDrawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<GifDrawable?>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: GifDrawable?,
                    model: Any?,
                    target: Target<GifDrawable?>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    resource?.setLoopCount(setDurasi!!)
                    return false
                }

            })
            .into(binding.ivBreathe)

        binding.tvSubtitle.text = durasi

    }
}