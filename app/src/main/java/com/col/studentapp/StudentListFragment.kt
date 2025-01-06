package com.col.studentapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class StudentListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_list, container, false)

        val button = view.findViewById<Button>(R.id.goToStudentDetails)
        button.setOnClickListener {
            findNavController().navigate(R.id.action_student_list_fragment_to_student_details_fragment)
        }

        return view
    }
}