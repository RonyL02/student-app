package com.col.studentapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.col.studentapp.databinding.ActivityStudentDetailsBinding
import com.col.studentapp.model.Model

class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentDetailsBinding
    private var studentIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityStudentDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        studentIndex = intent.getIntExtra("studentIndex", 0)
        binding.editButton.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("studentIndex", studentIndex)
            startActivity(intent)
        }

        initToolbar()
        injectStudentDataIntoView()
    }

    override fun onResume() {
        super.onResume()
        injectStudentDataIntoView()
    }

    private fun injectStudentDataIntoView() {
        val student = Model.shared.getByIndex(studentIndex)

        binding.apply {
            idValue.text = student.id.toString()
            nameValue.text = student.name
            phoneValue.text = student.phone
            addressValue.text = student.address
            checkBox.apply {
                isChecked = student.isChecked
                tag = student.id
                isEnabled = false
            }
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}