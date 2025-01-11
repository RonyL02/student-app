package com.col.studentapp.StudentList

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.col.studentapp.R
import com.col.studentapp.databinding.ActivityStudentListBinding

class StudentListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentListBinding
    private lateinit var studentRecyclerAdapter: StudentRecyclerAdapter
    private var students: List<Student> = listOf(
        Student(1, "aaaa", false),
        Student(2, "bbbb", false),
        Student(3, "cccc", false),
        Student(4, "dddd", false),
        Student(4, "dddd", false),
        Student(4, "dddd", false),
        Student(4, "dddd", false),
        Student(4, "dddd", false),
        Student(4, "dddd", false),
        Student(4, "dddd", false),
        Student(4, "dddd", false),
        Student(4, "dddd", false),
        Student(4, "dddd", false),
        Student(4, "dddd", false),
        Student(4, "dddd", false),
        Student(4, "dddd", false),
        Student(4, "dddd", false),
        Student(4, "dddd", false),
        Student(4, "dddd", false),
        Student(4, "dddd", false),
        Student(7, "dddd", false)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityStudentListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initToolbar()
        initStudentList()
    }

    private fun initToolbar() {
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun initStudentList() {
        binding.studentsRecyclerView.apply {
            setHasFixedSize(true)

            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager

            studentRecyclerAdapter = StudentRecyclerAdapter(students)
            studentRecyclerAdapter.listener = object : OnItemClickListener {
                override fun onItemClick(student: Student) {
                    println(student.name)
                }
            }

            adapter = studentRecyclerAdapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            Log.i("dsfsdfs", "dsfsfdsfsdfsdfds")
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}