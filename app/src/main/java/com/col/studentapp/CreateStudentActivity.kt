package com.col.studentapp

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.col.studentapp.databinding.ActivityCreateStudentBinding
import com.col.studentapp.model.Model
import com.col.studentapp.model.Student

class CreateStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()

        binding.apply {
            saveButton.setOnClickListener(::onSaveClicked)
            cancelButton.setOnClickListener(::onCancelClicked)
        }
    }

    private fun onSaveClicked(view: View) {
        val name = binding.studentFrom.nameEditText.text.toString()
        val id = binding.studentFrom.idEditText.text.toString().toIntOrNull()
        val phone = binding.studentFrom.phoneEditText.text.toString()
        val address = binding.studentFrom.addressEditText.text.toString()
        val isChecked = binding.studentFrom.checkedCheckBox.isChecked

        if (name.isEmpty() || id == null || phone.isEmpty() || address.isEmpty()) {
            binding.studentFrom.saveMessageTextView.text = "Please fill out all fields."
            binding.studentFrom.saveMessageTextView.visibility = View.VISIBLE // Show the message
            return
        }

        val student = Student(
            name = name,
            id = id,
            phone = phone,
            address = address,
            avatarUrl = "",
            isChecked = isChecked
        )

        binding.studentFrom.saveMessageTextView.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE

        Model.shared.add(student)

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
