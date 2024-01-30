package com.example.room11092023.presentation.dialog

import android.app.Dialog
import android.content.Context
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.room11092023.R
import com.example.room11092023.data.database.AppDatabase
import com.example.room11092023.data.database.Entity.Task

class AddDataDialog {

    companion object {
        fun createNewTaskDialog(context: Context, onListenAddTask: OnListenAddTask) {
            Dialog(context).apply {
                setContentView(R.layout.add_task_layout)
                setCancelable(true)
                window?.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)

                val editTextAddTask = findViewById<EditText>(R.id.edt_task_description)
                val buttonSave = findViewById<Button>(R.id.btn_save)
                val buttonCancel = findViewById<Button>(R.id.btn_cancel)

                buttonSave.setOnClickListener {
                    if (editTextAddTask.text.isNotBlank()) {
                        val db = AppDatabase.getInstance(context)
                        val dbDAO = db.appDao()
                        onListenAddTask.addTaskSuccess(
                            0,
                            editTextAddTask.text.toString(),
                            System.currentTimeMillis(),
                            System.currentTimeMillis()
                        )
                        dismiss()
                    } else {
                        editTextAddTask.error = "Please fill in Task description"
                    }
                }
                buttonCancel.setOnClickListener {
                    dismiss()
                }
                show()
            }
        }
    }
}

interface OnListenAddTask {
    fun addTaskSuccess(
        id: Int,
        name: String,
        startTime: Long,
        endTime: Long
    )
}