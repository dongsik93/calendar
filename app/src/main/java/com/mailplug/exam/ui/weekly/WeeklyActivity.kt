package com.mailplug.exam.ui.weekly

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.mailplug.exam.R
import com.mailplug.exam.databinding.ActivityWeeklyBinding
import com.mailplug.exam.ui.weekly.adapter.WeeklyAdapter
import com.mailplug.exam.ui.weekly.viewmodel.WeeklyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*


class WeeklyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeeklyBinding
    private lateinit var weeklyAdapter: WeeklyAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val vm: WeeklyViewModel by viewModel()

    private var mStrWeek: String = ""

    private var mYear: Int = 0
    private var mMonth: Int = 0
    private var mDay: Int = 0
    private var mDayNum: Int = 0
    private var mWeek: Int = 0
    private var mCurPos: Int = 0
    private var mStartDayNum: Int = 0

    private lateinit var weeks: MutableList<Week>

    private var mEnd = 0


    // init
    private var mCalendar = GregorianCalendar()
    private var dayFormat = SimpleDateFormat("dd", Locale.KOREA)
    private var monthFormat = SimpleDateFormat("MM", Locale.KOREA)
    private var yearFormat = SimpleDateFormat("yyyy", Locale.KOREA)

    private var weekCalendarFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_weekly)
        val date = Date()
//        subscribeObserver()
        bottomTab(date)
        initListener()
    }

    private fun dayOfWeekMonDay(date: String): Date {
        val calendar = Calendar.getInstance()
        val parseDate = weekCalendarFormat.parse(date) ?: Date()
        val cal = 1000 * 60 * 60 * 24
        calendar.time = parseDate
        return when {
            calendar.get(Calendar.DAY_OF_WEEK) > 1 -> Date(parseDate.time - (calendar.get(Calendar.DAY_OF_WEEK) - 2) * cal)
            calendar.get(Calendar.DAY_OF_WEEK) == 0 -> Date(parseDate.time + cal)
            else -> Date()
        }
    }

    private fun bottomTab(date: Date) {
        val today = weekCalendarFormat.format(date)
        var startDate = "2020-01-01"
        startDate = weekCalendarFormat.format(dayOfWeekMonDay(startDate))
        mEnd = today.replace("-", "").toInt()
        makeList(startDate, today)
    }

    private fun calcDateBetweenEndAndStart(start: String, end: String): Int {
        val endDate = weekCalendarFormat.parse(end) ?: Date()
        val startDate = weekCalendarFormat.parse(start) ?: Date()
        val ymd = start.split("-")
        val mCal = GregorianCalendar()
        mCal.set(ymd[0].toInt(), ymd[1].toInt() + 1, ymd[2].toInt())
        mStartDayNum = mCal.get(Calendar.DAY_OF_WEEK)
        val diff = endDate.time - startDate.time
        val diffDays = diff / (24 * 60 * 60 * 1000)
        return diffDays.toInt()
    }

    private fun makeList(startDate: String, today: String) {
        val diff = calcDateBetweenEndAndStart(startDate, today)
        mYear = mCalendar.get(Calendar.YEAR)
        mMonth = mCalendar.get(Calendar.MONTH) + 1
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
        mWeek = mCalendar.get(Calendar.WEEK_OF_MONTH)
        mDayNum = mCalendar.get(Calendar.DAY_OF_WEEK)
        mStrWeek = getStrWeek(mWeek)
        mCalendar.set(mYear, mMonth, mDay)

        val thisWeek = "${mYear}년 ${mMonth}월 $mStrWeek"
        binding.ymwTxtView.text = thisWeek

        val days = mutableListOf<Day>()
        val calendar = Calendar.getInstance()

        for (i in -((mStartDayNum) + diff)..(diff - mDayNum)) {
            calendar.set(mYear, mMonth - 1, mDay + i)
            val date1 = calendar.time
            days.add(
                Day(
                    yearFormat.format(date1),
                    monthFormat.format(date1),
                    dayFormat.format(date1)
                )
            )
        }

        weeks = mutableListOf()
        for (i in 0 until (days.size / 7)) {
            weeks.add(
                Week(
                    days[(7 * i)],
                    days[(7 * i) + 1],
                    days[(7 * i) + 2],
                    days[(7 * i) + 3],
                    days[(7 * i) + 4],
                    days[(7 * i) + 5],
                    days[(7 * i) + 6]
                )
            )
        }

        mCurPos = weeks.size - diff/7

//        val dayss = Vector<Day>()
//
//        dayss.add(weeks[mCurPos].day1)
//        dayss.add(weeks[mCurPos].day2)
//        dayss.add(weeks[mCurPos].day3)
//        dayss.add(weeks[mCurPos].day4)
//        dayss.add(weeks[mCurPos].day5)
//        dayss.add(weeks[mCurPos].day6)
//        dayss.add(weeks[mCurPos].day7)

        initAdapter(weeks, today)
    }

    private fun initAdapter(weeks: MutableList<Week>, today: String) {
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.daysRecyclerView.layoutManager = linearLayoutManager
        weeklyAdapter = WeeklyAdapter(weeks, today)
        binding.daysRecyclerView.adapter = weeklyAdapter

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.daysRecyclerView)

        binding.daysRecyclerView.scrollToPosition(mCurPos)
        weeklyAdapter.notifyDataSetChanged()
        binding.daysRecyclerView.addOnScrollListener(scrollListener)
    }

    private fun getStrWeek(intWeek: Int): String {
        return when (intWeek) {
            1 -> "첫째주"
            2 -> "둘째주"
            3 -> "셋째주"
            4 -> "넷째주"
            5 -> "다섯째주"
            else -> ""
        }
    }

//    private fun subscribeObserver() {
//        vm.mCalendarList.observe(this, Observer {
//            weeklyAdapter.submitList(it)
//        })
//    }

    private fun initListener() {
        binding.ymwNextImgBtn.setOnClickListener {
        }
    }

    private var scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == 0) {
                mCurPos = linearLayoutManager.findFirstVisibleItemPosition()
                println(mCurPos)
                val curWeek = weeklyAdapter.todays(mCurPos, 0)
                val week = curWeek.split("-")

                mCalendar.set(week[0].toInt(), week[1].toInt(), week[2].toInt())
                mWeek = mCalendar.get(Calendar.WEEK_OF_MONTH)
                mStrWeek = getStrWeek(mWeek)
                val thisWeek = "${week[0].toInt()}년 ${week[1].toInt()}월 $mStrWeek"
                binding.ymwTxtView.text = thisWeek

//                val dayss = mutableListOf<Day>()
//
//                dayss.add(weeks[mCurPos].day1)
//                dayss.add(weeks[mCurPos].day2)
//                dayss.add(weeks[mCurPos].day3)
//                dayss.add(weeks[mCurPos].day4)
//                dayss.add(weeks[mCurPos].day5)
//                dayss.add(weeks[mCurPos].day6)
//                dayss.add(weeks[mCurPos].day7)
            }
        }

//        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//            super.onScrolled(recyclerView, dx, dy)
//        }
    }

}
