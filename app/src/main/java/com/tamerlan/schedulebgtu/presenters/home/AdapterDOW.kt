package com.tamerlan.schedulebgtu.presenters.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.tamerlan.schedulebgtu.R
import com.tamerlan.schedulebgtu.data.TimetableDayOfWeek

class AdapterDOW(var context: Context, var list: List<TimetableDayOfWeek>) :
    BaseAdapter() {

    override fun getCount(): Int {
        return list.size;
    }

    override fun getItem(i: Int): Any {
        return list[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View?
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        if (convertView == null) {
            view = inflater.inflate(R.layout.card_day_item, null)

        } else {
            view = convertView
        }

        view?.findViewById<TextView>(R.id.title)?.text = list[position].title

        val linearLayout = view?.findViewById<LinearLayout>(R.id.ll_timetable)

        for (i in list[position].lessons) {
            val v = inflater.inflate(R.layout.card_timetable, linearLayout,false)

            v?.findViewById<TextView>(R.id.txtName)?.text = i.name
            v?.findViewById<TextView>(R.id.txtTime)?.text = i.time
            v?.findViewById<TextView>(R.id.teacher)?.text = i.teacher
            v?.findViewById<TextView>(R.id.position)?.text = i.position
            linearLayout?.addView(v)

        }
        return view as View
    }
}