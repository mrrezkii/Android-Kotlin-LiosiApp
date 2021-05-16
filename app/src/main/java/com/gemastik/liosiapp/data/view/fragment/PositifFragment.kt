package com.gemastik.liosiapp.data.view.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gemastik.liosiapp.databinding.FragmentPositifBinding
import com.gemastik.liosiapp.utils.showToast
import com.github.squti.androidwaverecorder.RecorderState
import com.github.squti.androidwaverecorder.WaveRecorder
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit

class PositifFragment : Fragment() {

    private lateinit var binding: FragmentPositifBinding

    private val PERMISSIONS_REQUEST_RECORD_AUDIO = 77

    private lateinit var waveRecorder: WaveRecorder
    private lateinit var filePath: String
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
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
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
                RecorderState.STOP -> showToast("Record Stop")
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

}