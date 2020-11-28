package com.example.mobileprograming.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import com.example.mobileprograming.R
import com.example.mobileprograming.model.Task
import kotlinx.android.synthetic.main.row_layout.view.*

class ListTaskAdapter(
    internal var activity: Activity,
    internal var listTasks: List<Task>,
    internal var edt_id: EditText,
    internal var edt_name: EditText,
    internal var edt_desc: EditText,
    internal var edt_date: EditText
    ) : BaseAdapter() {

    internal var inflater: LayoutInflater

    init {
        inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView: View
        rowView = inflater.inflate(R.layout.row_layout, null)

        rowView.txt_row_id.text = listTasks[position].id.toString()
        rowView.txt_name.text = listTasks[position].name
        rowView.txt_desc.text = listTasks[position].desc
        rowView.txt_date.text = listTasks[position].date


        rowView.setOnClickListener {
            edt_id.setText(rowView.txt_row_id.text.toString())
            edt_name.setText(rowView.txt_name.text.toString())
            edt_desc.setText(rowView.txt_desc.text.toString())
            edt_date.setText(rowView.txt_date.text.toString())

        }
        return rowView
    }

    override fun getItem(position: Int): Any {
        return listTasks[position]
    }

    override fun getItemId(position: Int): Long {
        return listTasks[position].id.toLong()
    }

    override fun getCount(): Int {
        return listTasks.size
    }
}