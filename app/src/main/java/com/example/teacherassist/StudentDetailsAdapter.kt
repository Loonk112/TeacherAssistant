package com.example.teacherassist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

class StudentDetailsAdapter(private val coursesList: ArrayList<CoursesData>) : RecyclerView.Adapter<StudentDetailsAdapter.ViewHolder>() {
    class ViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
        val deleteBtn = view.findViewById<ImageButton>(R.id.mainCoursesItemTemplateRemoveIB)
        val idView = view.findViewById<TextView>(R.id.mainCoursesItemTemplateId)
        val nameView = view.findViewById<TextView>(R.id.mainCoursesItemTemplateName)
        val yearView = view.findViewById<TextView>(R.id.mainCoursesItemTemplateYear)
        val detailBtn = view.findViewById<ImageButton>(R.id.mainCoursesItemTemplateDetailsIB)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentDetailsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_courses_item_template, parent, false)
        return StudentDetailsAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentDetailsAdapter.ViewHolder, position: Int) {

        holder.idView.text = coursesList[position].id.toString()
        holder.nameView.text = coursesList[position].name
        holder.yearView.text = coursesList[position].year.toString()

        holder.deleteBtn.setOnClickListener {
            val db = DBHelper(holder.view.context, null)
            db.deleteConnection(coursesList[position].id)
            coursesList.removeAt(position)
            notifyDataSetChanged()
        }

        holder.detailBtn.setOnClickListener {
            val assistant = Assistant()
            assistant.setConnectionId(coursesList[position].id)
            assistant.setoriginalPage(1)
            Navigation.findNavController(holder.view).navigate(R.id.action_studentDetails_to_gradesView)
        }
    }

    override fun getItemCount(): Int {
        return coursesList.size
    }
}