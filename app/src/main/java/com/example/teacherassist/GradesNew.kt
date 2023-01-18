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


class gradesNew : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grades_new, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cancelBtn = view.findViewById<Button>(R.id.gradeNewCancel)
        cancelBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_gradesNew_to_gradesView)
        }

        val commentInput = view.findViewById<EditText>(R.id.gradeNewCommentInput)
        val gradeInput = view.findViewById<EditText>(R.id.gradeNewGradeInput)
        val confirmBtn = view.findViewById<Button>(R.id.gradeNewConfirm)
        confirmBtn.setOnClickListener {
            if (commentInput.text.isEmpty() or gradeInput.text.isEmpty()) {
                Toast.makeText(context, "All fields must be filled", Toast.LENGTH_SHORT).show()
            } else {
                val db = DBHelper(requireContext(), null)
                val assist = Assistant()
                db.addGrade(assist.getConnectionId(), commentInput.text.toString(), gradeInput.text.toString().toInt())
                Navigation.findNavController(view).navigate(R.id.action_gradesNew_to_gradesView)
            }
        }
    }

}