package com.mailplug.exam

import com.mailplug.exam.ui.daily.viewmodel.CalendarHeaderViewModel
import com.mailplug.exam.ui.daily.viewmodel.CalendarListViewModel
import com.mailplug.exam.ui.daily.viewmodel.CalendarViewModel
import com.mailplug.exam.ui.daily.viewmodel.EmptyViewModel
import com.mailplug.exam.ui.weekly.viewmodel.WeeklyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CalendarHeaderViewModel() }
    viewModel { CalendarListViewModel() }
    viewModel { CalendarViewModel() }
    viewModel { EmptyViewModel() }
    viewModel { WeeklyViewModel()}
}

val appModules = listOf(viewModelModule)
