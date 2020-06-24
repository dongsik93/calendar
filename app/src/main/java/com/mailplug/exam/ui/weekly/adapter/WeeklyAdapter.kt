package com.mailplug.exam.ui.weekly.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mailplug.exam.Appbase.Companion.appContext
import com.mailplug.exam.databinding.ItemWeeklyBinding
import com.mailplug.exam.ui.weekly.Day
import com.mailplug.exam.ui.weekly.Week

class WeeklyAdapter(private val weeks: MutableList<Week>, private val today: String) : RecyclerView.Adapter<WeeklyAdapter.WeeklyViewHolder>() {

    private val context = appContext
//    private var iToday: Int = today.replace("-", "").toInt()
//    private var iStartDay: Int = startDay.replace("-", "").toInt()

    private lateinit var txtViews: Array<TextView>

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): WeeklyViewHolder {
        val binding = ItemWeeklyBinding.inflate(LayoutInflater.from(context), viewGroup, false)

        return WeeklyViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: WeeklyViewHolder, position: Int) {
        val binding = viewHolder.binding
        val week = weeks[position]
        txtViews = arrayOf(
            binding.sunTxtView,
            binding.monTxtView,
            binding.tueTxtView,
            binding.wedTxtView,
            binding.thuTxtView,
            binding.friTxtView,
            binding.sunTxtView
        )

        binding.sunTxtView.text = week.day1.day
        binding.monTxtView.text = week.day2.day
        binding.tueTxtView.text = week.day3.day
        binding.wedTxtView.text = week.day4.day
        binding.thuTxtView.text = week.day5.day
        binding.friTxtView.text = week.day6.day
        binding.satTxtView.text = week.day7.day

        //오늘 날짜를 크기를 크게
        for (i in txtViews.indices) {
            val today = todays(position, i)
            if (today == this.today) {
                txtViews[i].scaleX = 1.75f
                txtViews[i].scaleY = 1.75f
            } else {
                txtViews[i].scaleX = 1.0f
                txtViews[i].scaleY = 1.0f
            }

        }
    }

    override fun getItemCount(): Int {
        return weeks.size
    }

    class WeeklyViewHolder(var binding: ItemWeeklyBinding) : RecyclerView.ViewHolder(binding.root)

    fun todays(pos1: Int, pos2: Int): String {
        val week = weeks[pos1]
        var day: Day? = null
        when (pos2) {
            0 -> day = week.day1
            1 -> day = week.day2
            2 -> day = week.day3
            3 -> day = week.day4
            4 -> day = week.day5
            5 -> day = week.day6
            6 -> day = week.day7
        }
        return "${day!!.year}-${day.month}-${day.day}"
    }
}
