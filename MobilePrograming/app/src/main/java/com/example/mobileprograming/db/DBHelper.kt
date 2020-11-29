package com.example.mobileprograming.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.mobileprograming.model.Task

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {

    companion object {
        private val DATABASE_VER = 1
        private val DATABASE_NAME = "Task.db"

        private val TABLE_NAME = "Task"
        private val COL_ID = "Id"
        private val COL_NAME = "Name"
        private val COL_DESC = "Description"
        private val COL_DATE = "Date"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery: String =
            ("CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, $COL_NAME TEXT, $COL_DESC TEXT, $COL_DATE DATE)")
        db!!.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }

    //CRUD
    val getAllTasks: List<Task>
        get() {
            val listTask = ArrayList<Task>()
            val selectQuery = "SELECT * FROM $TABLE_NAME"
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst()) {
                do {
                    val task = Task()
                    task.id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                    task.name = cursor.getString(cursor.getColumnIndex(COL_NAME))
                    task.desc = cursor.getString(cursor.getColumnIndex(COL_DESC))
                    task.date = cursor.getString(cursor.getColumnIndex(COL_DATE))

                    listTask.add(task)
                } while (cursor.moveToNext())
            }
            db.close()
            return listTask
        }

    fun addTask(task: Task) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_NAME, task.name)
        values.put(COL_DESC, task.desc)
        values.put(COL_DATE, task.date)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun updateTask(task: Task): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID, task.id)
        values.put(COL_NAME, task.name)
        values.put(COL_DESC, task.desc)
        values.put(COL_DATE, task.date)
        return db.update(TABLE_NAME, values, "$COL_ID=?", arrayOf(task.id.toString()))
    }

    fun deleteTask(task: Task) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "$COL_ID=?", arrayOf(task.id.toString()))
        db.close()
    }

}