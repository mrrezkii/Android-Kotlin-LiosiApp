package com.gemastik.liosiapp.data.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.gemastik.liosiapp.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.*

class SplashScreenActivity : AppCompatActivity() {

    private val moveToMain: Intent by lazy {
        Intent(
            this@SplashScreenActivity,
            MainActivity::class.java
        )
    }
    private val moveToAuth: Intent by lazy {
        Intent(
            this@SplashScreenActivity,
            AuthenticationActivity::class.java
        )
    }

    private val binding: ActivitySplashScreenBinding by lazy {
        ActivitySplashScreenBinding.inflate(
            layoutInflater
        )
    }

    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        activityScope.launch {
            delay(3000)

            var intent = moveToAuth
            startActivity(intent)
            finish()
        }

    }

    @Suppress("DEPRECATION")
    override fun onResume() {
        super.onResume()
        window.decorView.apply {
            systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }
}