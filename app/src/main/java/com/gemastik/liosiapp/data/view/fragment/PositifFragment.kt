package com.gemastik.liosiapp.data.view.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gemastik.liosiapp.databinding.FragmentPositifBinding
import com.gemastik.liosiapp.utils.showToast
import com.github.squti.androidwaverecorder.RecorderState
import com.github.squti.androidwaverecorder.WaveRecorder
import timber.log.Timber
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.roundToInt


class PositifFragment : Fragment() {

    private lateinit var binding: FragmentPositifBinding

    private val PERMISSIONS_REQUEST_RECORD_AUDIO = 77

    private lateinit var waveRecorder: WaveRecorder
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var filePath: String
    private lateinit var uri: Uri
    private var isRecording = false
    private var isPaused = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPositifBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupRecorder()

    }

    private fun setupView() {
        mediaPlayer = MediaPlayer()
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.playButton.setOnClickListener {
            setupMediaPlayer()
            binding.playButton.visibility = View.GONE
            binding.pauseButton.visibility = View.VISIBLE
        }

        binding.pauseButton.setOnClickListener {
            mediaPlayer.pause()
            binding.playButton.visibility = View.VISIBLE
            binding.pauseButton.visibility = View.GONE
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupRecorder() {
        filePath = requireContext().externalCacheDir?.absolutePath + "/audioFile.wav"

        waveRecorder = WaveRecorder(filePath)

        waveRecorder.onTimeElapsed = {
            Timber.d("onCreate: time elapsed $it")
            Timber.d(formatTimeUnit(it * 1000))
        }

        waveRecorder.onStateChangeListener = {
            when (it) {
                RecorderState.RECORDING -> {
                    Timber.d(waveRecorder.audioSessionId.toString())
                    isRecording = true
                    isPaused = false
                    showToast("Recording...")
                }
                RecorderState.STOP -> {
                    binding.appCompatSeekBar.visibility = View.VISIBLE
                    binding.playButton.visibility = View.VISIBLE
                    binding.pauseButton.visibility = View.GONE
                }
                RecorderState.PAUSE -> showToast("Record Pause")
            }
        }

        binding.circleButton.setOnTouchListener(View.OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (ContextCompat.checkSelfPermission(
                            requireContext(),
                            Manifest.permission.RECORD_AUDIO
                        )
                        != PackageManager.PERMISSION_GRANTED
                    ) {
                        ActivityCompat.requestPermissions(
                            requireActivity(),
                            arrayOf(Manifest.permission.RECORD_AUDIO),
                            PERMISSIONS_REQUEST_RECORD_AUDIO
                        )
                    } else {
                        v.isPressed = true
                        waveRecorder.startRecording()
                    }
                    true
                }
                MotionEvent.ACTION_UP -> {
                    v.isPressed = false
                    waveRecorder.stopRecording()
                    false
                }
                else -> false
            }
        })

    }

    override fun onStart() {
        super.onStart()
        val file = File(filePath)
        if (file.exists()) {
            binding.appCompatSeekBar.visibility = View.VISIBLE
            binding.playButton.visibility = View.VISIBLE

        } else {
            binding.appCompatSeekBar.visibility = View.GONE
            binding.playButton.visibility = View.GONE
            binding.pauseButton.visibility = View.GONE
        }
    }

    private fun formatTimeUnit(timeInMilliseconds: Long): String {
        return try {
            String.format(
                Locale.getDefault(),
                "%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(timeInMilliseconds),
                TimeUnit.MILLISECONDS.toSeconds(timeInMilliseconds) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(timeInMilliseconds)
                )
            )
        } catch (e: Exception) {
            "00:00"
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSIONS_REQUEST_RECORD_AUDIO -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    waveRecorder.startRecording()
                }
                return
            }

            else -> {
            }
        }
    }

    private fun setupMediaPlayer() {
        uri = Uri.parse(filePath)
        mediaPlayer.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )
        mediaPlayer.reset()


        binding.appCompatSeekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                    seekBar?.progress = progress

                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        try {
            mediaPlayer.setDataSource(requireContext(), uri)
        } catch (e: Exception) {
            showToast("$e")
        }

        mediaPlayer.prepareAsync()
        mediaPlayer.setOnPreparedListener {
            binding.appCompatSeekBar.max = mediaPlayer.duration
            mediaPlayer.start()
            updateSeekbar()
        }

        mediaPlayer.setOnBufferingUpdateListener { mp, percent ->
            val ratio: Double = percent / 100.0
            val bufferingLevel: Int = (mediaPlayer.duration * ratio).roundToInt()
            binding.appCompatSeekBar.secondaryProgress = bufferingLevel
        }

        mediaPlayer.setOnCompletionListener {
            binding.playButton.visibility = View.VISIBLE
            binding.pauseButton.visibility = View.GONE
        }

    }

    private fun updateSeekbar() {
        val current: Int = mediaPlayer.currentPosition
        binding.appCompatSeekBar.progress = current

        Handler(Looper.getMainLooper()).postDelayed({ updateSeekbar() }, 1000)
    }

    override fun onPause() {
        super.onPause()
        try {
            mediaPlayer.stop()
        } catch (e: Exception) {
            print(e)
        }
    }
}