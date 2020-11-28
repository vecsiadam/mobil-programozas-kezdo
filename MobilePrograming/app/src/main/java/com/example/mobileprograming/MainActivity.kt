package com.example.mobileprograming

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileprograming.adapter.ListTaskAdapter
import com.example.mobileprograming.dbhelper.DBHelper
import com.example.mobileprograming.model.Task
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    internal lateinit var db: DBHelper
    internal var listTasks: List<Task> = ArrayList<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DBHelper(this)

        refreshData()

        //Event
        btn_add.setOnClickListener{
            val task = Task(
                //Integer.parseInt(edt_id.text.toString()),
                edt_name.text.toString(),
                edt_desc.text.toString(),
                edt_date.text.toString()
            )
            db.addTask(task)
            refreshData()
        }

        btn_update.setOnClickListener{
            val person = Task(
               Integer.parseInt(edt_id.text.toString()),
                edt_name.text.toString(),
                edt_desc.text.toString(),
                edt_date.text.toString()
            )
            db.updateTask(person)
            refreshData()
        }

        btn_delete.setOnClickListener{
            val person = Task(
                Integer.parseInt(edt_id.text.toString()),
                edt_name.text.toString(),
                edt_desc.text.toString(),
                edt_date.text.toString()
            )
            db.deleteTask(person)
            refreshData()
        }

    }

    private fun refreshData() {
        listTasks = db.getAllTasks
        val adapter = ListTaskAdapter(this@MainActivity, listTasks, edt_id, edt_name,
            edt_desc, edt_date)
        list_person.adapter = adapter
    }
}