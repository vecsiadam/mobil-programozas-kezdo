package com.example.mobileprograming

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileprograming.adapter.ListTaskAdapter
import com.example.mobileprograming.db.DBHelper
import com.example.mobileprograming.model.Task
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    internal lateinit var db: DBHelper
    internal var listTasks: List<Task> = ArrayList<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DBHelper(this)

        refreshData()

        btn_add.setOnClickListener{
            val task = Task(
                edt_name.text.toString(),
                edt_desc.text.toString(),
                edt_date.text.toString()
            )
            db.addTask(task)
            refreshData()
            closeKeyBoard()
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
            closeKeyBoard()
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
            closeKeyBoard()
        }

    }

    fun openPickerDate(view : View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            edt_date.setText("" + year + "/" + monthOfYear + "/" + dayOfMonth)
        }, year, month, day)
        dpd.show()
    }

    private fun refreshData() {
        listTasks = db.getAllTasks
        val adapter = ListTaskAdapter(this@MainActivity, listTasks, edt_id, edt_name,
            edt_desc, edt_date)
        list_task.adapter = adapter
    }

    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}