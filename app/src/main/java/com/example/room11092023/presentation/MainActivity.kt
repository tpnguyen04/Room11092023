package com.example.room11092023.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.room11092023.R
import com.example.room11092023.data.database.AppDatabase
import com.example.room11092023.data.database.Entity.Status
import com.example.room11092023.data.database.Entity.StatusConverter
import com.example.room11092023.data.database.Entity.StatusEnum
import com.example.room11092023.data.database.Entity.Task
import com.example.room11092023.presentation.dialog.AddDataDialog
import com.example.room11092023.presentation.dialog.OnListenAddTask
import com.example.room11092023.utils.TaskAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var btnAddTask: FloatingActionButton
    private lateinit var recyclerViewTask: RecyclerView
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAddTask = findViewById(R.id.floating_button_add_task)
        recyclerViewTask = findViewById(R.id.recycler_view_task)


        // set up dbDAO
        val db = AppDatabase.getInstance(this@MainActivity)
        val dbDAO = db.appDao()

        CoroutineScope(Dispatchers.IO).launch {
            val listTask = dbDAO.getListTask()
            Log.d("pphat", listTask.toString())

//            val listRowId = dbDAO.addStatus(listOf(
//                Status(0, StatusEnum.INVESTIGATE),
//                Status(0, StatusEnum.PROGRESS),
//                Status(0, StatusEnum.DONE)
//            ))
//            Log.d("pphat", listRowId.toString())

            val listStatus = dbDAO.getListStatus()
            Log.d("pphat", listStatus.toString())
        }

        adapter = TaskAdapter()

        CoroutineScope(Dispatchers.IO).launch {
            adapter.updateData(dbDAO.getListTask())
        }
        recyclerViewTask.adapter = adapter

        btnAddTask.setOnClickListener {
            AddDataDialog.createNewTaskDialog(this@MainActivity, object : OnListenAddTask {

                override fun addTaskSuccess(id: Int, name: String, startTime: Long, endTime: Long) {
                    CoroutineScope(Dispatchers.IO).launch {
                        val newTask = Task(id, 1, name, startTime, endTime)
                        dbDAO.addTask(newTask)
                        adapter.updateData(dbDAO.getListTask())
                    }
                    recyclerViewTask.adapter = adapter
                }
            })
        }
    }

}