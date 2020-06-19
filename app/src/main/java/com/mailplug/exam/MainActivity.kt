package com.mailplug.exam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mailplug.exam.databinding.ActivityMainBinding
import com.mailplug.exam.ui.adapter.CalendarAdapter
import com.mailplug.exam.ui.viewmodel.CalendarListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var calendarAdapter: CalendarAdapter
    private val vm: CalendarListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setVariable(BR._all, CalendarListViewModel())
        vm.initCalendarList()
        binding.lifecycleOwner = this
        val manager = StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL)
        calendarAdapter = CalendarAdapter()
        binding.calendar.layoutManager = manager
        binding.calendar.adapter = calendarAdapter
        observe()
    }

    private fun observe() {
        vm.mCalendarList.observe(this, Observer {
            calendarAdapter.submitList(it)
            if (vm.mCenterPosition > 0) {
                binding.calendar.scrollToPosition(vm.mCenterPosition)
            }
        })
    }
}
