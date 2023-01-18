package com.example.teacherassist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.navigation.Navigation


class courseDetailsAddNew : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course_details_add_new, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cancelBtn = view.findViewById<Button>(R.id.addStudentToCourseCancel)
        val confirmBtn = view.findViewById<Button>(R.id.addStudentToCourseConfirm)
        val spinner = view.findViewById<Spinner>(R.id.addStudentToCourseSpinner)

        val db = DBHelper(requireContext(), null)
        val assist = Assistant()
        val data = db.getUnconnectedStudents(assist.getCourseId())

        val adapter = CourseDetailsAddNewSpinnerAdapter(requireContext(), data)

        spinner.adapter = adapter

        if (data.size < 1){
            confirmBtn.isEnabled = false;
            confirmBtn.isClickable = false;
        }

        cancelBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_courseDetailsAddNew_to_courseDetails)
        }
        confirmBtn.setOnClickListener {
            db.addConnection(assist.getCourseId(),data[spinner.selectedItemId.toInt()].id)
            Navigation.findNavController(view).navigate(R.id.action_courseDetailsAddNew_to_courseDetails)
        }
    }

}