package com.example.room11092023.utils

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.room11092023.R
import com.example.room11092023.data.database.Entity.Status
import com.example.room11092023.data.database.Entity.StatusConverter
import com.example.room11092023.data.database.Entity.StatusEnum
import com.example.room11092023.data.database.Entity.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class TaskAdapter: RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    var taskList = listOf<Task>()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.task_row_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTask = taskList[position]
        holder.itemView.apply {
            findViewById<TextView>(R.id.text_view_id).text = currentTask.id.toString()
            findViewById<TextView>(R.id.text_view_task_name).text = currentTask.name
            findViewById<TextView>(R.id.text_view_task_status).text = StatusEnum.INVESTIGATE.name
            findViewById<TextView>(R.id.text_view_start_time).text = holder.itemView.context.getString(R.string.start_time, convertMillisecondsToDateTime(currentTask.startDate))
            findViewById<TextView>(R.id.text_view_end_time).text = holder.itemView.context.getString(R.string.end_time, convertMillisecondsToDateTime(currentTask.endDate))
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun updateData(newDataList: List<Task>) {
        taskList = newDataList
        notifyDataSetChanged()
    }

    fun convertMillisecondsToDateTime(milliseconds: Long): String {
        val sdf = SimpleDateFormat("dd/MM/yy")
        val date = Date(milliseconds)
        return sdf.format(date)
    }
}


