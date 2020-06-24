package com.mailplug.exam.ui.weekly.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mailplug.exam.ui.weekly.Day
import com.mailplug.exam.ui.weekly.Week
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class WeeklyViewModel : ViewModel() {
    private var mCurrentTime: Long = 0
    var mTitle = MutableLiveData<String>()
    var mCalendarList: MutableLiveData<Vector<Week>> = MutableLiveData(Vector<Week>())

}