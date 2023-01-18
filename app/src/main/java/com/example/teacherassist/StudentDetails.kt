package com.example.teacherassist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class StudentDetails : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_details, container, false)
        val assistant = Assistant()
        val recycler = view.findViewById<RecyclerView>(R.id.studentDetailsRV)
        recycler.layoutManager = LinearLayoutManager(activity)

        val db = DBHelper(requireContext(), null)

        val data = db.getConnectionsCourses(assistant.getStudentId())

        recycler.adapter = StudentDetailsAdapter(data)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val returnButton = view.findViewById<ImageButton>(R.id.studentDetailsReturn)

        returnButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_studentDetails_to_mainSelection)
        }
        val addCourseFAB = view.findViewById<FloatingActionButton>(R.id.studentDetailsFAB)
        addCourseFAB.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_studentDetails_to_studentDetailsAddNew)
        }
    }
}