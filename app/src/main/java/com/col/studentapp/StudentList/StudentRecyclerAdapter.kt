package com.col.studentapp.StudentList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.col.studentapp.databinding.StudentRowBinding

data class Student(
    val id: Int,
    val name: String,
    var isChecked: Boolean
)

interface OnItemClickListener {
    fun onItemClick(student: Student)
}

class StudentRecyclerAdapter(private var students: List<Student>) :
    RecyclerView.Adapter<StudentViewHolder>() {

    lateinit var listener: OnItemClickListener

    override fun getItemCount(): Int = students.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflation = LayoutInflater.from(parent.context)
        val view = StudentRowBinding.inflate(inflation, parent, false)
        return StudentViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(students[position], position)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun onClick(position: Int) {
        listener.onItemClick(students[position])
    }
}
