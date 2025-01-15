package com.col.studentapp

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.col.studentapp.databinding.ActivityEditStudentBinding
import com.col.studentapp.model.Model
import com.col.studentapp.model.Student

class EditStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditStudentBinding
    private lateinit var student: Student
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()

        val studentId = intent.getIntExtra("studentId", 0)
        student = Model.shared.getStudentById(studentId) ?: return


        populateFields()


        binding.saveButton.setOnClickListener(::onSaveClicked)
        binding.cancelButton.setOnClickListener(::onCancelClicked)
        binding.deleteButton.setOnClickListener(::onDeleteClicked)
    }

    private fun populateFields() {
        binding.studentFrom.nameEditText.setText(student.name)
        binding.studentFrom.idEditText.setText(student.id.toString())
        binding.studentFrom.phoneEditText.setText(student.phone)
        binding.studentFrom.addressEditText.setText(student.address)
        binding.studentFrom.checkedCheckBox.isChecked = student.isChecked
    }

    private fun onSaveClicked(view: View) {
        val name = binding.studentFrom.nameEditText.text.toString()
        val id = binding.studentFrom.idEditText.text.toString().toIntOrNull()
        val phone = binding.studentFrom.phoneEditText.text.toString()
        val address = binding.studentFrom.addressEditText.text.toString()
        val isChecked = binding.studentFrom.checkedCheckBox.isChecked

        if (name.isEmpty() || id == null || phone.isEmpty() || address.isEmpty()) {
            binding.studentFrom.saveMessageTextView.text = "Please fill out all fields."
            binding.studentFrom.saveMessageTextView.visibility = View.VISIBLE
            return
        }

        student.name = name
        student.id = id
        student.phone = phone
        student.address = address
        student.isChecked = isChecked

        binding.progressBar.visibility = View.VISIBLE
        Model.shared.update(student)


        binding.progressBar.visibility = View.GONE
        finish()
    }

    private fun onDeleteClicked(view: View) {

        binding.progressBar.visibility = View.VISIBLE
        Model.shared.delete(student)

        binding.progressBar.visibility = View.GONE
        finish()
    }

    private fun onCancelClicked(view: View) {

        finish()
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
