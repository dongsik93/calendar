package com.mailplug.exam.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.mailplug.exam.data.TSLiveData
import java.util.*


class CalendarViewModel : ViewModel() {
    var mCalendar: TSLiveData<Calendar> = TSLiveData<Calendar>()

    fun setCalendar(calendar: Calendar) {
        mCalendar.value = calendar
    }
}