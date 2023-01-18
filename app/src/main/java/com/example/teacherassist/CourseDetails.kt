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

class CourseDetails : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_course_details, container, false)
        val assistant = Assistant()
        assistant.setoriginalPage(0)
        val recycler = view.findViewById<RecyclerView>(R.id.courseDetailsRV)
        recycler.layoutManager = LinearLayoutManager(activity)

        val db = DBHelper(requireContext(), null)

        val data = db.getConnectionsStudents(assistant.getCourseId())

        recycler.adapter = CourseDetailsAdapter(data)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val returnButton = view.findViewById<ImageButton>(R.id.courseDetailsReturn)
        returnButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_courseDetails_to_mainSelection)
        }
        val addStudentFAB = view.findViewById<FloatingActionButton>(R.id.courseDetailsFAB)
        addStudentFAB.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_courseDetails_to_courseDetailsAddNew)
        }
    }
}