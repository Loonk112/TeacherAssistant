package com.example.teacherassist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CourseDetailsAddNewSpinnerAdapter(ctx: Context, studentList : ArrayList<StudentsData>) : ArrayAdapter<StudentsData>(ctx, 0, studentList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return myView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return myView(position, convertView, parent)
    }

    private fun myView(position: Int, convertView: View?, parent: ViewGroup): View {
        val list = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.course_details_spinner_item_template, parent, false)

        list?.let {
            val idTV = view.findViewById<TextView>(R.id.courseDetailsSpinnerTemplateId)
            val gradeTV = view.findViewById<TextView>(R.id.courseDetailsSpinnerTemplateGrade)
            val nameTV = view.findViewById<TextView>(R.id.courseDetailsSpinnerTemplateName)
            val surnameTV = view.findViewById<TextView>(R.id.courseDetailsSpinnerTemplateSurname)

            idTV.text = list.id.toString()
            gradeTV.text = list.grade.toString()
            nameTV.text = list.name
            surnameTV.text = list.surname
        }

        return view
    }
}