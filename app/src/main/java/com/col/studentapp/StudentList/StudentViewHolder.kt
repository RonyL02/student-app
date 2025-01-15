package com.col.studentapp.StudentList

import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.col.studentapp.databinding.StudentRowBinding
import com.col.studentapp.model.Student

class StudentViewHolder(
    itemView: StudentRowBinding,
    listener: OnItemClickListener
) : RecyclerView.ViewHolder(itemView.root) {
    private var nameTextView: TextView
    private var idTextView: TextView
    private var checkBox: CheckBox
    private lateinit var student: Student
    private var position: Int = 0

    init {
        nameTextView = itemView.studentRowNameTextView
        idTextView = itemView.studentRowIdTextView
        checkBox = itemView.studentRowCheckBox
        checkBox.apply {
            setOnClickListener { view ->
                (tag as? Int)?.let {
                    student.isChecked = (view as? CheckBox)?.isChecked ?: false
                }
            }
        }

        itemView.root.setOnClickListener {
            listener.onItemClick(position)
        }
    }

    fun bind(student: Student, position: Int) {
        this.student = student
        this.position = position
        nameTextView.text = student.name
        student.id.toString().also { idTextView.text = it }
        checkBox.apply {
            isChecked = student.isChecked
            tag = position
        }
    }
}