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

class StudentsAddNew : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_students_add_new, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newStudentNameInput = view.findViewById<EditText>(R.id.StudentsAddNewNameInput)
        val newStudentSurnameInput = view.findViewById<EditText>(R.id.StudentsAddNewSurnameInput)
        val newStudentGradeInput = view.findViewById<EditText>(R.id.StudentsAddNewGradeInput)
        val cancelBtn = view.findViewById<Button>(R.id.StudentsAddNewCancelBtn)
        val confirmBtn = view.findViewById<Button>(R.id.StudentsAddNewConfirmBtn)

        cancelBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_studentsAddNew_to_mainSelection)
        }
        confirmBtn.setOnClickListener {
            if (newStudentNameInput.text.isEmpty() or newStudentSurnameInput.text.isEmpty() or newStudentGradeInput.text.isEmpty()) {
                Toast.makeText(view.context, "All fields must be filled", Toast.LENGTH_LONG).show()
            } else {
                val db = DBHelper(requireContext(), null)
                db.addStudent(newStudentNameInput.text.toString(), newStudentSurnameInput.text.toString(), newStudentGradeInput.text.toString().toInt())
                Navigation.findNavController(view).navigate(R.id.action_studentsAddNew_to_mainSelection)
            }
        }
    }

}