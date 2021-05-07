package com.gemastik.liosiapp.data.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gemastik.liosiapp.databinding.ActivityAuthenticationBinding

class AuthenticationActivity : AppCompatActivity() {

    private val binding: ActivityAuthenticationBinding by lazy {
        ActivityAuthenticationBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}