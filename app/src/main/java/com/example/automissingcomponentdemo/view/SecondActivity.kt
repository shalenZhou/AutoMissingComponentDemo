package com.example.automissingcomponentdemo.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.automissingcomponentdemo.databinding.ActivitySecondBinding
import com.example.automissingcomponentdemo.viewmodel.SecondViewModel

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    private val viewModel: SecondViewModel by viewModels()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.setOnTouchListener { _, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_UP -> {
                    viewModel.openCountdownTimer()
                }

                else -> {
                    binding.fab.visibility = View.VISIBLE
                }
            }
            true
        }

        observeDataChanged()
    }

    private fun observeDataChanged() {
        viewModel.isTouched.observe(this@SecondActivity) {
            binding.isTouched = it
        }
    }
}