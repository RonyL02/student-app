package com.col.studentapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.col.studentapp.databinding.ActivityCreateStudentBinding
import com.col.studentapp.model.Model
import com.col.studentapp.model.Student

class EditStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateStudentBinding
    private lateinit var student: Student
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using ViewBinding
        binding = ActivityCreateStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val studentId = intent.getStringExtra("studentId") ?: ""
        student = Model.shared.getStudentById(studentId) ?: return


        populateFields()


        binding.saveButton.setOnClickListener(::onSaveClicked)
        binding.cancelButton.setOnClickListener(::onCancelClicked)
        binding.deleteButton.setOnClickListener(::onDeleteClicked)
    }

    private fun populateFields() {
        binding.nameEditText.setText(student.name)
        binding.idEditText.setText(student.id)
      
        binding.checkedCheckBox.isChecked = student.isChecked
    }

    private fun onSaveClicked(view: View) {

        val name = binding.nameEditText.text.toString()
        val id = binding.idEditText.text.toString()
        val phone = binding.phoneEditText.text.toString()
        val address = binding.addressEditText.text.toString()
        val isChecked = binding.checkedCheckBox.isChecked


        if (name.isEmpty() || id.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            binding.saveMessageTextView.text = "Please fill out all fields."
            binding.saveMessageTextView.visibility = View.VISIBLE
            return
        }


        student.name = name
        student.id = id

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
}
