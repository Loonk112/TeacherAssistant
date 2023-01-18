package com.example.teacherassist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

class CourseDetailsAdapter(private val studentsList: ArrayList<StudentsData>) : RecyclerView.Adapter<CourseDetailsAdapter.ViewHolder>()  {
    class ViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
        val deleteBtn = view.findViewById<ImageButton>(R.id.mainStudentsItemTemplateRemoveIB)
        val idView = view.findViewById<TextView>(R.id.mainStudentsItemTemplateId)
        val nameView = view.findViewById<TextView>(R.id.mainStudentsItemTemplateName)
        val surnameView = view.findViewById<TextView>(R.id.mainStudentsItemTemplateSurame)
        val gradeView = view.findViewById<TextView>(R.id.mainStudentsItemTemplateGrade)
        val detailBtn = view.findViewById<ImageButton>(R.id.mainStudentsItemTemplateDetailsIB)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseDetailsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_students_item_template, parent, false)
        return CourseDetailsAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseDetailsAdapter.ViewHolder, position: Int) {

        holder.idView.text = studentsList[position].id.toString()
        holder.nameView.text = studentsList[position].name
        holder.surnameView.text = studentsList[position].surname
        holder.gradeView.text = studentsList[position].grade.toString()

        holder.deleteBtn.setOnClickListener {
            val db = DBHelper(holder.view.context, null)
            db.deleteConnection(studentsList[position].id)
            studentsList.removeAt(position)
            notifyDataSetChanged()
        }

        holder.detailBtn.setOnClickListener {
            val assistant = Assistant()
            assistant.setConnectionId(studentsList[position].id)
            assistant.setoriginalPage(0)
            Navigation.findNavController(holder.view).navigate(R.id.action_courseDetails_to_gradesView)
        }
    }

    override fun getItemCount(): Int {
        return studentsList.size
    }

}