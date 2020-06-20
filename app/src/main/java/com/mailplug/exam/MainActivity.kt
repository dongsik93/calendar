package com.mailplug.exam

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mailplug.exam.databinding.ActivityMainBinding
import com.mailplug.exam.ui.adapter.CalendarAdapter
import com.mailplug.exam.ui.viewmodel.CalendarListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var calendarAdapter: CalendarAdapter
    private val vm: CalendarListViewModel by viewModel()
    private var cal = Calendar.getInstance()
    private var year = cal.get(Calendar.YEAR)
    private var month = cal.get(Calendar.MONTH)
    private var day = cal.get(Calendar.DAY_OF_MONTH)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initAdapter()
        subscribeObserve()
        setClickListener()

    }

    private fun initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setVariable(BR._all, CalendarListViewModel())
        vm.initCalendarList(year, month)
        binding.lifecycleOwner = this
    }

    private fun initAdapter() {
        val manager = StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL)
        calendarAdapter = CalendarAdapter()
        binding.calendar.layoutManager = manager
        binding.calendar.adapter = calendarAdapter
    }

    private fun subscribeObserve() {
        vm.mCalendarList.observe(this, Observer {
            calendarAdapter.submitList(it)
        })
    }

    private fun setClickListener() {
        binding.btnDate.setOnClickListener {
            DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { _, y, m, d ->
                    if (y != year || m != month) {
                        vm.initCalendarList(y, m)
                        year = y
                        month = m
                        day = d
                    }
                }, year, month, day
            ).show()
        }
    }
}
