package com.example.automissingcomponentdemo.binding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isTouched")
fun bindViewVisibility(view: View, isTouched: Boolean) = if (isTouched) {
    view.visibility = View.VISIBLE
} else {
    view.visibility = View.GONE
}