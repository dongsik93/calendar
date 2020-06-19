package com.mailplug.exam.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.mailplug.exam.data.TSLiveData

class CalendarHeaderViewModel : ViewModel() {
    val mHeaderDate : TSLiveData<Long> = TSLiveData()

    fun setHeaderDate(headerDate: Long) {
        mHeaderDate.value = headerDate
    }
}