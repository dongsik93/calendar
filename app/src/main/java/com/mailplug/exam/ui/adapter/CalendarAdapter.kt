package com.mailplug.exam.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.gson.Gson
import com.mailplug.exam.R
import com.mailplug.exam.bindingAdapter.TextBindingAdapter
import com.mailplug.exam.databinding.ItemCalendarHeaderBinding
import com.mailplug.exam.databinding.ItemDayBinding
import com.mailplug.exam.databinding.ItemDayEmptyBinding
import com.mailplug.exam.ui.viewmodel.CalendarHeaderViewModel
import com.mailplug.exam.ui.viewmodel.CalendarViewModel
import com.mailplug.exam.ui.viewmodel.EmptyViewModel
import com.mailplug.exam.utils.DateFormat
import java.util.*


class CalendarAdapter :
    ListAdapter<Any, ViewHolder>(object : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            val gson = Gson()
            return gson.toJson(oldItem) == gson.toJson(newItem)
        }
    }) {
    private val HEADER_TYPE = 0
    private val EMPTY_TYPE = 1
    private val DAY_TYPE = 2

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Long -> {
                HEADER_TYPE
            }
            is String -> {
                EMPTY_TYPE
            }
            else -> {
                DAY_TYPE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // 날짜타입
        if (viewType == HEADER_TYPE) {
            val binding = DataBindingUtil.inflate<ItemCalendarHeaderBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_calendar_header,
                parent,
                false
            )
            val params = binding.root.layoutParams as StaggeredGridLayoutManager.LayoutParams
            params.isFullSpan = true //Span을 하나로 통합하기
            binding.root.layoutParams = params
            return HeaderViewHolder(binding)
        } else if (viewType == EMPTY_TYPE) {
            val binding = DataBindingUtil.inflate<ItemDayEmptyBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_day_empty,
                parent,
                false
            )
            return EmptyViewHolder(binding)
        }
        val binding = DataBindingUtil.inflate<ItemDayBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_day,
            parent,
            false
        )
        return DayViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            //날짜 타입 꾸미기
            HEADER_TYPE -> {
                val holder: HeaderViewHolder = viewHolder as HeaderViewHolder
                val item = getItem(position)
                val model = CalendarHeaderViewModel()
                if (item is Long) {
                    model.setHeaderDate(item)
                }
                holder.setViewModel(model)
            }
            //비어있는 날짜 타입 꾸미기
            EMPTY_TYPE -> {
                val holder: EmptyViewHolder = viewHolder as EmptyViewHolder
                val model = EmptyViewModel()
                holder.setViewModel(model)
            }
            // 일자 타입 꾸미기
            DAY_TYPE -> {
                val holder: DayViewHolder = viewHolder as DayViewHolder
                val item = getItem(position)
                val model = CalendarViewModel()
                if (item is Calendar) {
                    model.setCalendar(item)
                }
                holder.setViewModel(model)
            }
        }
    }

    //날짜 타입 ViewHolder
    private class HeaderViewHolder(private val binding: ItemCalendarHeaderBinding) : ViewHolder(binding.root) {
        fun setViewModel(model: CalendarHeaderViewModel) {
            TextBindingAdapter.setCalendarHeaderText(binding.headerText, model.mHeaderDate.value)
            binding.executePendingBindings()
        }
    }

    // 비어있는 요일 타입 ViewHolder
    private class EmptyViewHolder(private val binding: ItemDayEmptyBinding) : ViewHolder(binding.root) {
        fun setViewModel(model: EmptyViewModel) {
            println("-------")
            println(model)
            println("-------")
            binding.executePendingBindings()
        }
    }

    // 요일 타입 ViewHolder
    private class DayViewHolder(private val binding: ItemDayBinding) : ViewHolder(binding.root) {
        fun setViewModel(model: CalendarViewModel) {
            TextBindingAdapter.setDayText(binding.txDay, model.mCalendar.value)
            binding.executePendingBindings()
        }
    }
}
