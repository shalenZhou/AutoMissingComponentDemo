package com.example.automissingcomponentdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SecondViewModel : ViewModel() {
    private var viewGoneCountDownTimer: Job? = null

    private val _isTouched = MutableStateFlow(false)
    val isTouched: LiveData<Boolean> = _isTouched.asLiveData(viewModelScope.coroutineContext)

    fun openCountdownTimer() {
        _isTouched.value = true
        // 计时器存在时，有操作先关闭当前计时器，在创建新的计时器，重新计时
        viewGoneCountDownTimer?.cancel()

        viewGoneCountDownTimer = viewModelScope.launch {
            delay(5000)
            _isTouched.value = false
        }
    }
}