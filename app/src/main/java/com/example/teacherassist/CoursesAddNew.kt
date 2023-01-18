package com.example.teacherassist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation


class CoursesAddNew : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_courses_add_new, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newCourseNameInput = view.findViewById<EditText>(R.id.CoursesAddNewNameInput)
        val newCourseYearInput = view.findViewById<EditText>(R.id.CoursesAddNewYearInput)
        val cancelBtn = view.findViewById<Button>(R.id.CoursesAddNewCancelBtn)
        val confirmBtn = view.findViewById<Button>(R.id.CoursesAddNewConfirmBtn)

        cancelBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_coursesAddNew_to_mainSelection)
        }
        confirmBtn.setOnClickListener {
            if (newCourseNameInput.text.isEmpty() or newCourseYearInput.text.isEmpty()) {
                Toast.makeText(view.context, "All fields must be filled", Toast.LENGTH_LONG).show()
            } else {
                val db = DBHelper(requireContext(), null)
                db.addCourse(newCourseNameInput.text.toString(), newCourseYearInput.text.toString().toInt())
                Navigation.findNavController(view).navigate(R.id.action_coursesAddNew_to_mainSelection)
            }
        }
    }
}