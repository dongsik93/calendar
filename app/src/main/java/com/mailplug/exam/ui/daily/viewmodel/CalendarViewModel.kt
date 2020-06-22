package com.mailplug.exam.ui.daily.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*


class CalendarViewModel : ViewModel() {
    var mCalendar: MutableLiveData<Calendar> = MutableLiveData()

    fun setCalendar(calendar: Calendar) {
        mCalendar.value = calendar
    }
}