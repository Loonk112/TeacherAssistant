package com.example.teacherassist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainCourses : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_main_courses, container, false)

        val db = DBHelper(requireContext(), null)
        val data = db.getCourses()

        val recycler = view.findViewById<RecyclerView>(R.id.mainCoursesRV)
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.adapter = MainCoursesAdapter(data)

        val assistant = Assistant()
        assistant.setoriginalPage(0)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val addNewBtn = view.findViewById<FloatingActionButton>(R.id.mainCoursesFAB)
        addNewBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainSelection_to_coursesAddNew)
        }
    }
}