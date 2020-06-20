package com.mailplug.exam.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalendarHeaderViewModel : ViewModel() {
    val mHeaderDate : MutableLiveData<Long> = MutableLiveData()

    fun setHeaderDate(headerDate: Long) {
        mHeaderDate.value = headerDate
    }
}