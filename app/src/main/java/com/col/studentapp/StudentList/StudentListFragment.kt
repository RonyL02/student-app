package com.col.studentapp.StudentList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.col.studentapp.R

class StudentListFragment : Fragment() {
    lateinit var adapter: StudentRecyclerAdapter
    private val studentList: List<Student> = listOf(
        Student(1, "sdfsd", false),
        Student(2, "asdaads", false),
        Student(3, "sfsdf", false),
        Student(4, "ffffff", false)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_list, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.studentsRecyclerView)
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        adapter = StudentRecyclerAdapter(studentList)

        adapter.listener = object : OnItemClickListener {
            override fun onItemClick(student: Student) {
                student.apply {
                    val action =
                        StudentListFragmentDirections.actionStudentListFragmentToStudentDetailsFragment(
                            id,
                            name,
                            isChecked
                        )
                    findNavController().navigate(action)
                }
            }
        }

        recyclerView.adapter = adapter
        return view
    }
}