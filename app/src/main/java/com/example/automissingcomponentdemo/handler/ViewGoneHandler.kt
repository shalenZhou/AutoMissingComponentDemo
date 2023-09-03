package com.example.automissingcomponentdemo.handler

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import java.lang.ref.WeakReference

private const val MSG_GONE = 1

class ViewGoneHandler constructor(activity: Activity, view: View) :
    Handler(Looper.getMainLooper()) {

    private val weakReference: WeakReference<Activity>
    private val view: View

    init {
        weakReference = WeakReference<Activity>(activity)
        this.view = view
    }

    override fun handleMessage(msg: Message) {
        val activity = weakReference.get()
        if (activity != null) {
            when (msg.what) {
                MSG_GONE -> {
                    view.visibility = View.GONE
                }
            }
        }
    }
}