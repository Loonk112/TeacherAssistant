package com.example.teacherassist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class gradesView : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_grades_view, container, false)

        val assistant = Assistant()

        val db = DBHelper(requireContext(), null)
        val data = db.getGrades(assistant.getConnectionId())

        val recycler = view.findViewById<RecyclerView>(R.id.gradesViewRV)
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.adapter = GradesViewAdapter(data)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val assist = Assistant()
        val returnButton = view.findViewById<ImageButton>(R.id.gradesViewReturn)
        returnButton.setOnClickListener {
            if (assist.getOriginalPage() == 0) {
                Navigation.findNavController(view).navigate(R.id.action_gradesView_to_courseDetails)
            } else if (assist.getOriginalPage() == 1) {
                Navigation.findNavController(view).navigate(R.id.action_gradesView_to_studentDetails)
            }
        }

        val newFAB = view.findViewById<FloatingActionButton>(R.id.gradesViewFAB)
        newFAB.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_gradesView_to_gradesNew)
        }
    }

}