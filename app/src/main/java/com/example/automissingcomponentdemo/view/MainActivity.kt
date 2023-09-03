package com.example.automissingcomponentdemo.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.automissingcomponentdemo.R
import com.example.automissingcomponentdemo.handler.ViewGoneHandler
import com.google.android.material.floatingactionbutton.FloatingActionButton

private const val MSG_GONE = 1

class MainActivity : AppCompatActivity() {

    private val fab by lazy {
        findViewById<FloatingActionButton>(R.id.fab)
    }

    private val handler by lazy {
        ViewGoneHandler(this@MainActivity, fab)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rootView = findViewById<View>(R.id.rootView)
        rootView.setOnTouchListener { _, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_UP -> {
                    sendMessageByHandler()
                }

                else -> {
                    fab.visibility = View.VISIBLE
                }
            }
            true
        }

        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        handler.apply {
            removeCallbacksAndMessages(null)
            handler.removeMessages(MSG_GONE)
        }
    }

    private fun sendMessageByHandler() {
        handler.removeMessages(MSG_GONE)
        val msg = handler.obtainMessage(MSG_GONE)
        handler.sendMessageDelayed(msg, 5000)
    }
}