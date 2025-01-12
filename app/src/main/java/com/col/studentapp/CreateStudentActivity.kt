package com.col.studentapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.col.studentapp.databinding.ActivityCreateStudentBinding
import com.col.studentapp.model.Model
import com.col.studentapp.model.Student

class CreateStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using ViewBinding
        binding = ActivityCreateStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set listeners for Save and Cancel buttons
        binding.saveButton.setOnClickListener(::onSaveClicked)
        binding.cancelButton.setOnClickListener(::onCancelClicked)
    }

    private fun onSaveClicked(view: View) {
        // Retrieve input data from user
        val name = binding.nameEditText.text.toString()
        val id = binding.idEditText.text.toString()
        val phone = binding.phoneEditText.text.toString()
        val address = binding.addressEditText.text.toString()
        val isChecked = binding.checkedCheckBox.isChecked

        // Validate input fields
        if (name.isEmpty() || id.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            binding.saveMessageTextView.text = "Please fill out all fields."
            binding.saveMessageTextView.visibility = View.VISIBLE // Show the message
            return
        }

        // Create a new Student object
        val student = Student(
            name = name,
            id = id,
            phone=phone,
            address=address,
            avatarUrl = "", // Assuming avatarUrl is not collected in this form
            isChecked = isChecked
        )

        // Hide error message and show progress bar
        binding.saveMessageTextView.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE

        // Add the student to the model
        Model.shared.add(student)

        // Hide progress bar and close the activity
        binding.progressBar.visibility = View.GONE
        finish() // Close the activity
    }

    private fun onCancelClicked(view: View) {
        // Close the activity
        finish()
    }
}
