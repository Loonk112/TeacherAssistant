package com.example.teacherassist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

class GradesViewAdapter(private val gradesList: ArrayList<GradesData>) : RecyclerView.Adapter<GradesViewAdapter.ViewHolder>() {
    class ViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
        val deleteBtn = view.findViewById<ImageButton>(R.id.gradesViewItemTemplateRemove)
        val commentView = view.findViewById<TextView>(R.id.gradesViewItemTemplateComment)
        val gradeView = view.findViewById<TextView>(R.id.gradesViewItemTemplateGrade)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradesViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grades_view_item_template, parent, false)
        return GradesViewAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: GradesViewAdapter.ViewHolder, position: Int) {

        holder.commentView.text = gradesList[position].comment
        holder.gradeView.text = gradesList[position].grade.toString()

        holder.deleteBtn.setOnClickListener {
            val db = DBHelper(holder.view.context, null)
            db.deleteGrade(gradesList[position].id)
            gradesList.removeAt(position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return gradesList.size
    }
}