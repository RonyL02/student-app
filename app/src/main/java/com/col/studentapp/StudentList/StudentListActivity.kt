package com.col.studentapp.StudentList

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.col.studentapp.CreateStudentActivity
import com.col.studentapp.R
import com.col.studentapp.StudentDetailsActivity
import com.col.studentapp.databinding.ActivityStudentListBinding
import com.col.studentapp.model.Model
import kotlin.system.exitProcess

class StudentListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentListBinding
    private lateinit var studentRecyclerAdapter: StudentRecyclerAdapter

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

        binding.addStudentButton.setOnClickListener {
            val intent = Intent(this, CreateStudentActivity::class.java)
            startActivity(intent)
        }
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
            studentRecyclerAdapter = StudentRecyclerAdapter(Model.shared.getAllStudents())
            studentRecyclerAdapter.listener = object : OnItemClickListener {
                override fun onItemClick(position: Int) {
                    val intent =
                        Intent(this@StudentListActivity, StudentDetailsActivity::class.java)
                    intent.putExtra("studentIndex", position)
                    startActivity(intent)
                }
            }

            adapter = studentRecyclerAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        getAllStudents()
    }

    private fun getAllStudents() {
        Model.shared.getAllStudents().let {
            studentRecyclerAdapter.students = it
            studentRecyclerAdapter.notifyDataSetChanged()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            finishAffinity()
            exitProcess(0)
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}