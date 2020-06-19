package com.mailplug.exam

import com.mailplug.exam.ui.viewmodel.CalendarHeaderViewModel
import com.mailplug.exam.ui.viewmodel.CalendarListViewModel
import com.mailplug.exam.ui.viewmodel.CalendarViewModel
import com.mailplug.exam.ui.viewmodel.EmptyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CalendarHeaderViewModel() }
    viewModel { CalendarListViewModel() }
    viewModel { CalendarViewModel() }
    viewModel { EmptyViewModel() }
}

val appModules = listOf(viewModelModule)
