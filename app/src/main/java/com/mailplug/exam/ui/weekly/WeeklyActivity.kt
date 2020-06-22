package com.mailplug.exam.ui.weekly

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mailplug.exam.BR
import com.mailplug.exam.R
import com.mailplug.exam.databinding.ActivityMainBinding
import com.mailplug.exam.ui.daily.viewmodel.CalendarListViewModel

class WeeklyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initAdapter()
        subscribeObserver()
    }

    private fun initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_weekly)
        binding.setVariable(BR._all,
            CalendarListViewModel()
        )
        binding.lifecycleOwner = this
    }

    private fun initAdapter() {

    }

    private fun subscribeObserver() {

    }
}